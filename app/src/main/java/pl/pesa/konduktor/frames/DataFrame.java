package pl.pesa.konduktor.frames;

public class DataFrame extends Frame implements Serializable{
    private String currentStop;
    private String nextStop;
    private String ipAddress;

    public DataFrame(Builder builder) {
        super(builder);
        this.currentStop = builder.currentStop;
        this.nextStop = builder.nextStop;
        this.ipAddress = builder.ipAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private String currentStop;
        private String nextStop;
        private String ipAddress;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder currentStop(String user) {
            this.currentStop = user;
            return this;
        }

        public Builder nextStop(String pass) {
            this.nextStop = pass;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return ipAddress;
    }
    @Override
    public String getFrameTypeString() {
        return getFrameType().toString();
    }

}
