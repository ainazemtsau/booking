package repository;

import java.util.List;

public interface BookedRepository {

    List<Integer> updateSetsForRow(Integer row, List<Integer> bookedSeats);

    List<Integer> allSetsByRow(Integer row);
}
