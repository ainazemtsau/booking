package validation;

import model.BookingData;
import model.PairRowSeat;
import model.ValidateResult;
import repository.BookedRepository;

import java.util.List;

public class RangeNamberValidate extends ValidationStep {

    @Override
    public ValidateResult validate(BookingData data) {
        if (validateNumber(data.getRangeSeat().getFrom()) && validateNumber(data.getRangeSeat().getTo())) {
            return checkNext(data);
        } else {
            return ValidateResult.FAILED_RANGE_OF_NUMBER;
        }
    }

    private boolean validateNumber(PairRowSeat pairRowSeat){
        int row = pairRowSeat.getRow();
        int seat = pairRowSeat.getSeat();
        return row >= 0 && row <= 100 && seat >= 0 && seat <= 50;
    }

}
