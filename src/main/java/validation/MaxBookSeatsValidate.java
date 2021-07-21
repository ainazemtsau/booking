package validation;

import model.BookingData;
import model.RangeSeat;
import model.ValidateResult;

public class MaxBookSeatsValidate extends ValidationStep {

    @Override
    public ValidateResult validate(BookingData data) {
        if (getSeatNumber(data) > 5) {
            return ValidateResult.FAILED_TRY_BOOK_TOO_MANY_SEATS;
        } else {
            return checkNext(data);
        }
    }

    private int getSeatNumber(BookingData data) {
        RangeSeat rangeSeat = data.getRangeSeat();
        return Math.abs(rangeSeat.getTo().getSeat() - rangeSeat.getFrom().getSeat())+1;
    }
}
