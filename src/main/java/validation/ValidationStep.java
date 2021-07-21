package validation;

import model.BookingData;
import model.ValidateResult;

public abstract class ValidationStep {

    private ValidationStep nextStep;

    public ValidationStep setNextStep(ValidationStep nextStep) {
        this.nextStep = nextStep;
        return nextStep;
    }

    public abstract ValidateResult validate(BookingData bookingData);

    protected ValidateResult checkNext(BookingData bookingData) {
        if (nextStep == null) {
            return ValidateResult.PASSED;
        }
        return nextStep.validate(bookingData);
    }
}
