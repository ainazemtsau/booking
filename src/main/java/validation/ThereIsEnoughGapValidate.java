package validation;

import model.BookingData;
import model.ValidateResult;
import repository.BookedRepository;

import java.util.List;

public class ThereIsEnoughGapValidate extends ValidationStep {

    private final BookedRepository repository;

    public ThereIsEnoughGapValidate(BookedRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValidateResult validate(BookingData data) {
        Integer from = data.getRangeSeat().getFrom().getSeat();
        Integer to = data.getRangeSeat().getTo().getSeat();
        List<Integer> bookedSets = repository.allSetsByRow(data.getRangeSeat().getFrom().getRow());
        if (isOnePlaceGapAfter(to, bookedSets) || isOnePlaceGapBefore(from, bookedSets)) {
            if(from == 0 && to == 50){

            }
            return ValidateResult.FAILED_NEED_GAP_BETWEEN_SEATS;

        } else {
            return checkNext(data);
        }
    }

    private boolean isOnePlaceGapAfter(Integer index, List<Integer> bookedSets) {
        if (index == 50) {
            return false;
        } else if (index == 49) {
            return bookedSets.get(index + 1) != null;
        }
        Integer next = bookedSets.get(index + 1);
        if (next == null) {
            return bookedSets.get(index + 2) != null;
        } else {
            return false;
        }
    }

    private boolean isOnePlaceGapBefore(Integer index, List<Integer> bookedSets) {
        if (index == 0) {
            return false;
        } else if (index == 1) {
            return bookedSets.get(0) != null;
        }
        Integer prev = bookedSets.get(index - 1);
        if (prev == null) {
            return bookedSets.get(index - 2) != null;
        } else {
            return false;
        }
    }

}
