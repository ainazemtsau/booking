package service;

import model.BookingData;
import model.ValidateResult;
import repository.BookedRepository;
import validation.AllSeatsFreeValidate;
import validation.MaxBookSeatsValidate;
import validation.RangeNamberValidate;
import validation.SeatOnTheSameRowValidate;
import validation.ThereIsEnoughGapValidate;
import validation.ValidationStep;

public class CommonValidationServiceImpl implements ValidationService {

    private final ValidationStep validationStep;

    public CommonValidationServiceImpl(BookedRepository bookedRepository) {
        validationStep = new RangeNamberValidate();
        validationStep.setNextStep(new SeatOnTheSameRowValidate()).setNextStep(new MaxBookSeatsValidate()).setNextStep(new AllSeatsFreeValidate(bookedRepository))
                .setNextStep(new ThereIsEnoughGapValidate(bookedRepository));
    }

    @Override
    public ValidateResult validate(BookingData bookingData) {
        return validationStep.validate(bookingData);
    }
}
