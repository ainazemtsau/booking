package service;

import model.BookStatus;
import model.BookingData;
import model.BookingResult;
import model.ValidateResult;
import repository.BookedRepository;

import java.util.Collections;
import java.util.List;

public class CommonBookingService implements BookingService {

    private final ValidationService validationService;
    private final BookedRepository bookedRepository;

    public CommonBookingService(ValidationService validationService, BookedRepository bookedRepository) {
        this.validationService = validationService;
        this.bookedRepository = bookedRepository;
    }

    @Override
    public BookingResult book(BookingData bookingData) {
        ValidateResult validateResult = validationService.validate(bookingData);
        return getBookingResult(bookingData, validateResult);
    }

    private BookingResult getBookingResult(BookingData bookingData, ValidateResult validateResult) {
        if (validateResult == ValidateResult.PASSED) {
            return save(bookingData);
        } else {
            return getFailedNullStringBookingResult(bookingData, validateResult.getMessage());
        }
    }

    private BookingResult save(BookingData bookingData) {
        int row = bookingData.getRangeSeat().getFrom().getRow();
        List<Integer> bookedTicket = bookingData.getRangeSeat().getSetsListNumber();
        bookedRepository.updateSetsForRow(row, bookedTicket);
        return new BookingResult(bookingData.getId(), bookingData.toString(), BookStatus.SUCCESS);
    }

    private BookingResult getFailedNullStringBookingResult(BookingData bookingData, String message) {
        return new BookingResult(bookingData.getId(), bookingData.toString(), BookStatus.FAILED, message);
    }
}
