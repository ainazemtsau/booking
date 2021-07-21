package model;

import java.util.List;

public class BookingData {
    private String id;
    private RangeSeat rangeSeat;

    public BookingData(String id, RangeSeat rangeSeat) {
        this.id = id;
        this.rangeSeat = rangeSeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public RangeSeat getRangeSeat() {
        return rangeSeat;
    }

    public void setRangeSeat(RangeSeat rangeSeat) {
        this.rangeSeat = rangeSeat;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingData that = (BookingData) o;

        if (!id.equals(that.id)) return false;
        return rangeSeat.equals(that.rangeSeat);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rangeSeat.hashCode();
        return result;
    }
}

