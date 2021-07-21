package model;

public enum ValidateResult {
    PASSED {
        @Override
        public String getMessage() {
            return "success";
        }
    }, FAILED {
        @Override
        public String getMessage() {
            return "failed";
        }
    }, FAILED_TRY_BOOK_TOO_MANY_SEATS{
        @Override
        public String getMessage() {
            return "You cannot book more than 5 seats";
        }
    }, FAILED_SEATS_SHOULD_BE_ON_THE_SAME_ROW {
        @Override
        public String getMessage() {
            return "Seats have to be on the some row";
        }
    }, FAILED_SEATS_ALREADY_BOOKED{
        @Override
        public String getMessage() {
            return "Seats already booked";
        }
    }, FAILED_NEED_GAP_BETWEEN_SEATS {
        @Override
        public String getMessage() {
            return "Need gap between seats";
        }
    }, FAILED_RANGE_OF_NUMBER {
        @Override
        public String getMessage() {
            return "Wrong number of row or seat";
        }
    };


    public abstract String getMessage();
}
