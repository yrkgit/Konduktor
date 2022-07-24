package pl.pesa.konduktor.packet;

public class Packet {
    private String appVersion;
    private PacketTypes packetType;

    private long utc;

    protected Packet(Builder<?> builder) {
        this.appVersion = builder.appVersion;
        this.packetType = builder.packetType;
        this.utc = builder.utc;
    }

    public static Builder builder() {
        return new Builder() {
            public Builder getThis() {
                return this;
            }
        };
    }

    public PacketTypes getPacketType() {
        return packetType;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String appVersion;
        private PacketTypes packetType;

        private long utc;

        public abstract T getThis();

        public T appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this.getThis();
        }

        public T packetType(PacketTypes packetType) {
            this.packetType = packetType;
            return this.getThis();
        }

        public T utc(long utc) {
            this.utc = utc;
            return this.getThis();
        }

        public Packet build() {
            return new Packet(this);
        }
    }

    @Override
    public String toString() {
        return "Packet{" +
                "appVersion='" + appVersion + '\'' +
                ", packetType=" + packetType +
                ", utc=" + utc +
                '}';
    }
}
