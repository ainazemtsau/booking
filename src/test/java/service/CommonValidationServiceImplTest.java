package service;

import model.BookingData;
import model.PairRowSeat;
import model.RangeSeat;
import model.ValidateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookedRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommonValidationServiceImplTest {

    private final BookedRepository bookedRepository = mock(BookedRepository.class);
    private final ValidationService validationService = new CommonValidationServiceImpl(bookedRepository);
    private List<Integer> bookedSeats;

    @BeforeEach
    public void setUp(){
        bookedSeats= Arrays.asList(new Integer[51]);
    }

    @Test
    void shouldReturnFailedValidateResultWhenSetRangeMoreThanFive() {
        List<Integer> bookedSeats = this.bookedSeats;
        when(bookedRepository.allSetsByRow(1)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(1, 1), new PairRowSeat(1, 6)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_TRY_BOOK_TOO_MANY_SEATS, result);
    }

    @Test
    void shouldReturnFailedValidateResultWhenSetsDoesNotOnTheSameRow() {
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(1, 1), new PairRowSeat(2, 7)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_SEATS_SHOULD_BE_ON_THE_SAME_ROW, result);
    }

    @Test
    void shouldReturnFailedSetsAlreadyBookedWhenSetsAlreadyBooked() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(49, 1);
        when(bookedRepository.allSetsByRow(1)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(1, 48), new PairRowSeat(1, 50)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_SEATS_ALREADY_BOOKED, result);
    }


    @Test
    void shouldReturnFailedNeedGapBetweenSeatsWhenThereIsNotEnoughGap() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(6, 1);
        when(bookedRepository.allSetsByRow(1)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(1, 1), new PairRowSeat(1, 4)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_NEED_GAP_BETWEEN_SEATS, result);
    }


    @Test
    void shouldReturnFailedNeedGapBetweenSeatsWhenThereIsNotEnoughGapAndWhenOrderOnlyOnePlace() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(4, 1);
        when(bookedRepository.allSetsByRow(0)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(0, 0), new PairRowSeat(0, 2)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_NEED_GAP_BETWEEN_SEATS, result);
    }

    @Test
    void shouldReturnFailedNeedGapBetweenSeatsWhenThereIsNotEnoughGapAndWhenOrderOnlyOnePlace2() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(4, 1);
        bookedSeats.set(0, 1);
        when(bookedRepository.allSetsByRow(0)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(0, 2), new PairRowSeat(0, 2)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_NEED_GAP_BETWEEN_SEATS, result);
    }

    @Test
    void shouldReturnFailedNeedGapBetweenSeatsWhenThereIsNotEnoughGapAndWhenOrderOnlyOnePlace3() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(48, 1);
        bookedSeats.set(0, 1);
        when(bookedRepository.allSetsByRow(25)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(25, 50), new PairRowSeat(25, 50)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.FAILED_NEED_GAP_BETWEEN_SEATS, result);
    }

    @Test
    void shouldReturnPassedWhenThereIsEnoughPlace() {
        List<Integer> bookedSeats = this.bookedSeats;
        bookedSeats.set(44, 1);
        when(bookedRepository.allSetsByRow(22)).thenReturn(bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(22, 50), new PairRowSeat(22, 50)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.PASSED, result);
    }

    @Test
    void shouldPassedValidate() {
        when(bookedRepository.allSetsByRow(1)).thenReturn(this.bookedSeats);
        BookingData data = new BookingData("1", new RangeSeat(new PairRowSeat(1, 1), new PairRowSeat(1, 5)));
        ValidateResult result = validationService.validate(data);
        assertEquals(ValidateResult.PASSED, result);
    }

}
