package validation;

import model.BookingData;
import model.RangeSeat;
import model.ValidateResult;

public class SeatOnTheSameRowValidate extends ValidationStep {

    @Override
    public ValidateResult validate(BookingData data) {
        RangeSeat rangeSeat = data.getRangeSeat();
        if (rangeSeat.getFrom().getRow() != rangeSeat.getTo().getRow()) {
            return ValidateResult.FAILED_SEATS_SHOULD_BE_ON_THE_SAME_ROW;
        } else {
            return checkNext(data);
        }
    }
}
