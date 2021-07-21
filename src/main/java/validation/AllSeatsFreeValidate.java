package validation;

import model.BookingData;
import model.RangeSeat;
import model.ValidateResult;
import repository.BookedRepository;

import java.util.List;

public class AllSeatsFreeValidate extends ValidationStep {

    private final BookedRepository repository;

    public AllSeatsFreeValidate(BookedRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValidateResult validate(BookingData data) {
        List<Integer> bookedSets = repository.allSetsByRow(data.getRangeSeat().getFrom().getRow());
        if (isAllSetsAvailable(data, bookedSets)) {
            return checkNext(data);
        } else {
            return ValidateResult.FAILED_SEATS_ALREADY_BOOKED;
        }
    }

    private boolean isAllSetsAvailable(BookingData data, List<Integer> bookedSets) {
        return data.getRangeSeat().getSetsListNumber().stream().filter(s -> bookedSets.get(s) != null).findFirst().isEmpty();
    }

}
