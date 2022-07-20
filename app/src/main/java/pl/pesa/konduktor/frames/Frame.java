package pl.pesa.konduktor.frames;

public class Frame {
    private String appVersion;
    private FrameTypes frameType;

    private long utc;

    protected Frame(Builder<?> builder) {
        this.appVersion = builder.appVersion;
        this.frameType = builder.frameType;
        this.utc = builder.utc;
    }

    public static Builder builder() {
        return new Builder() {
            public Builder getThis() {
                return this;
            }
        };
    }

    public FrameTypes getFrameType() {
        return frameType;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String appVersion;
        private FrameTypes frameType;

        private long utc;

        public abstract T getThis();

        public T appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this.getThis();
        }

        public T frameType(FrameTypes frameType) {
            this.frameType = frameType;
            return this.getThis();
        }

        public T utc(long utc) {
            this.utc = utc;
            return this.getThis();
        }

        public Frame build() {
            return new Frame(this);
        }
    }

    @Override
    public String toString() {
        return "Frame{" +
                "appVersion='" + appVersion + '\'' +
                ", frameType=" + frameType +
                ", utc=" + utc +
                '}';
    }
}
