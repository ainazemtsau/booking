package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeSeat {
    private PairRowSeat from;
    private PairRowSeat to;

    public RangeSeat(PairRowSeat from, PairRowSeat to) {
        this.from = from;
        this.to = to;
    }

    public PairRowSeat getFrom() {
        return from;
    }

    public void setFrom(PairRowSeat from) {
        this.from = from;
    }

    public PairRowSeat getTo() {
        return to;
    }

    public void setTo(PairRowSeat to) {
        this.to = to;
    }

    public List<Integer> getSetsListNumber(){
        return IntStream.rangeClosed(getFrom().getSeat(), getTo().getSeat()).boxed().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RangeSeat rangeSeat = (RangeSeat) o;

        if (!from.equals(rangeSeat.from)) return false;
        return to.equals(rangeSeat.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
