package repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookedRepositoryInMemoryTest {

    private final BookedRepository repository = new BookedRepositoryInMemory();

    @Test
    void save() {
        List<Integer> bookedSets = List.of(40,41,43);
        repository.updateSetsForRow(2, bookedSets);
        repository.allSetsByRow(2);
        bookedSets.forEach(s -> {
            assertEquals(1, repository.allSetsByRow(2).get(s));
        });
    }
}
