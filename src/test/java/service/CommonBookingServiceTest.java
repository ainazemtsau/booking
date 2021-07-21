package service;

import model.BookStatus;
import model.BookingData;
import model.BookingResult;
import model.PairRowSeat;
import model.RangeSeat;
import model.ValidateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookedRepository;
import repository.BookedRepositoryInMemory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommonBookingServiceTest {

    private final ValidationService validationService = mock(ValidationService.class);
    private BookedRepository bookedRepository;
    private BookingService service;

    private final BookingData bookingData = new BookingData("1", new RangeSeat(new PairRowSeat(1, 10),
            new PairRowSeat(1, 14)));

    @BeforeEach
    public void clean() {
        bookedRepository = new BookedRepositoryInMemory();
        service = new CommonBookingService(validationService, bookedRepository);
    }

    @Test
    void shouldReturnSuccessResultWhenBookStringIsValid() {
        when(validationService.validate(bookingData)).thenReturn(ValidateResult.PASSED);
        BookingResult expectedResult = new BookingResult("1", bookingData.toString(), BookStatus.SUCCESS);
        assertEquals(expectedResult, service.book(bookingData));
    }

    @Test
    void shouldReturnFailedWhenValidationDoesNotPass() {
        when(validationService.validate(bookingData)).thenReturn(ValidateResult.FAILED);
        BookingResult expectedResult = new BookingResult("1", bookingData.toString(), BookStatus.FAILED, ValidateResult.FAILED.getMessage());
        assertEquals(expectedResult, service.book(bookingData));
    }

    @Test
    void shouldAddSeatsToListBookedSeatsWhenSave() {
        when(validationService.validate(any())).thenReturn(ValidateResult.PASSED);
        BookingData bookingDataNext = new BookingData("1", new RangeSeat(new PairRowSeat(1, 17),
                new PairRowSeat(1, 19)));
        service.book(bookingData);
        service.book(bookingDataNext);
        List<Integer> bookedTickets = bookedRepository.allSetsByRow(1);
        assertNull(bookedTickets.get(16));
        assertEquals(bookedTickets.get(17), 1);
        assertEquals(bookedTickets.get(18), 1);
        assertEquals(bookedTickets.get(19), 1);
        assertNull(bookedTickets.get(20));
    }
}
