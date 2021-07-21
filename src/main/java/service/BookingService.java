package service;

import model.BookingData;
import model.BookingResult;

public interface BookingService {

    BookingResult book(BookingData bookingData);
}
