package model;

import java.util.Objects;

public class BookingResult {
    private String id;
    private String rangeSeats;
    private BookStatus status;
    private String comment;

    public BookingResult(String id, String rangeSeats, BookStatus status, String comment) {
        this.id = id;
        this.rangeSeats = rangeSeats;
        this.status = status;
        this.comment = comment;
    }

    public BookingResult(String id, String rangeSeats, BookStatus status) {
        this.id = id;
        this.rangeSeats = rangeSeats;
        this.status = status;
    }

    public String getRangeSeats() {
        return rangeSeats;
    }

    public void setRangeSeats(String rangeSeats) {
        this.rangeSeats = rangeSeats;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingResult that = (BookingResult) o;

        if (!id.equals(that.id)) return false;
        if (!rangeSeats.equals(that.rangeSeats)) return false;
        if (status != that.status) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rangeSeats.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if(status == BookStatus.SUCCESS) {
            return "Booking for order #" + id + " was " + status;
        } else {
            return "Booking for order #" + id + " was " + status + " comment: " + comment;
        }
    }
}
