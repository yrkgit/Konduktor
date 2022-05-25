package pl.pesa.konduktor.frames;

public class DataFrame extends Frame implements Serializable{
    private String currentStop;
    private String nextStop;
    private String currentSpeed;

    private int passengerStats;
    private int boardingStats;
    private int unBoardingStats;


    public DataFrame(Builder builder) {
        super(builder);
        this.currentStop = builder.currentStop;
        this.nextStop = builder.nextStop;
        this.currentSpeed = builder.currentSpeed;
        this.passengerStats = builder.passengerStats;
        this.boardingStats = builder.boardingStats;
        this.unBoardingStats = builder.unBoardingStats;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String currentStop;
        private String nextStop;
        private String currentSpeed;

        private int passengerStats;
        private int boardingStats;
        private int unBoardingStats;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder currentStop(String currentStop) {
            this.currentStop = currentStop;
            return this;
        }

        public Builder nextStop(String nextStop) {
            this.nextStop = nextStop;
            return this;
        }

        public Builder currentSpeed(String currentSpeed) {
            this.currentSpeed = currentSpeed;
            return this;
        }
        public Builder passengerStats(int passengerStats) {
            this.passengerStats = passengerStats;
            return this;
        }
        public Builder boardingStats(int boardingStats) {
            this.boardingStats = boardingStats;
            return this;
        }
        public Builder unBoardingStats(int unBoardingStats) {
            this.unBoardingStats = unBoardingStats;
            return this;
        }

        public DataFrame build() {
            return new DataFrame(this);
        }
    }

    public String getCurrentStop() {
        return currentStop;
    }

    public String getNextStop() {
        return nextStop;
    }

    public String getCurrentSpeed() {
        return currentSpeed;
    }

    public int getPassengerStats() {
        return passengerStats;
    }

    public int getBoardingStats() {
        return boardingStats;
    }

    public int getUnBoardingStats() {
        return unBoardingStats;
    }
}
