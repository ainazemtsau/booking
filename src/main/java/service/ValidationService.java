package service;

import model.BookingData;
import model.ValidateResult;

public interface ValidationService {
    ValidateResult validate(BookingData bookingData);
}
