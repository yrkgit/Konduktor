package pl.pesa.konduktor.packet;

public class LogResponsePacket extends Packet implements Serializable{
    private LogResponseTypes permission;


    public LogResponsePacket(Builder builder) {
        super(builder);
        this.permission = builder.permission;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Packet.Builder<Builder> {
        private LogResponseTypes permission;


        @Override
        public Builder getThis() {
            return this;
        }

        public Builder permission(LogResponseTypes permission) {
            this.permission = permission;
            return this;
        }


        public LogResponsePacket build() {
            return new LogResponsePacket(this);
        }
    }

    public LogResponseTypes getPermission() {
        return permission;
    }
}

