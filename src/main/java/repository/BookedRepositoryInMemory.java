package repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookedRepositoryInMemory implements BookedRepository {
    private final Map<Integer, List<Integer>> bookedSets;

    public BookedRepositoryInMemory() {
        bookedSets = new HashMap<>(101);
        for (int i = 0; i <= 100; i++) {
            List<Integer> sets = Arrays.asList(new Integer[51]);
            bookedSets.put(i, sets);
        }
    }

    @Override
    public List<Integer> updateSetsForRow(Integer row, List<Integer> bookedSeats) {
        bookedSeats.forEach(s -> this.bookedSets.get(row).set(s, 1));
        return bookedSets.get(row);
    }

    @Override
    public List<Integer> allSetsByRow(Integer row) {
        return bookedSets.get(row);
    }
}
