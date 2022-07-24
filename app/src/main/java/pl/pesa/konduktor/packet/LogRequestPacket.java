package pl.pesa.konduktor.packet;

public class LogRequestPacket extends Packet implements Serializable{
    private String user;
    private String pass;
    private String ipAddress;

    public LogRequestPacket(Builder builder) {
        super(builder);
        this.user = builder.user;
        this.pass = builder.pass;
        this.ipAddress = builder.ipAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Packet.Builder<Builder> {
        private String user;
        private String pass;
        private String ipAddress;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder pass(String pass) {
            this.pass = pass;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public LogRequestPacket build() {
            return new LogRequestPacket(this);
        }
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getIpAddress() {
        return ipAddress;
    }

}
