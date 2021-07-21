import model.BookingData;
import model.PairRowSeat;
import model.RangeSeat;
import repository.BookedRepository;
import repository.BookedRepositoryInMemory;
import service.BookingService;
import service.CommonBookingService;
import service.CommonValidationServiceImpl;
import service.ValidationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        BookedRepository repository = new BookedRepositoryInMemory();
        ValidationService validationSe = new CommonValidationServiceImpl(repository);
        BookingService bookingService = new CommonBookingService(validationSe, repository);
        try (Stream<String> lineStream = Files.lines(Paths.get("./booking_requests"))) {

            lineStream.forEachOrdered(l -> {
                        System.out.println(bookingService.book(parseLineToData(l)));
                    }
            );

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static BookingData parseLineToData(String line) {
        String[] arr = line.split(",");
        String id = arr[0].replace("(", "");
        PairRowSeat from = getSetCoordinate(arr[1]);
        PairRowSeat to = getSetCoordinate(arr[2].replace(")", ""));
        return new BookingData(id, new RangeSeat(from, to));
    }

    private static PairRowSeat getSetCoordinate(String s) {
        String[] rofFrom = s.split(":");
        int row = Integer.parseInt(rofFrom[0]);
        int seat = Integer.parseInt(rofFrom[1]);
        return new PairRowSeat(row, seat);
    }
}
