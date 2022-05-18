package pl.pesa.konduktor.frames;

public class LogResponseFrame extends Frame  implements Serializable{
    private LogResponseTypes permission;


    public LogResponseFrame(Builder builder) {
        super(builder);
        this.permission = builder.permission;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Frame.Builder<Builder> {
        private LogResponseTypes permission;


        @Override
        public Builder getThis() {
            return this;
        }

        public Builder permission(LogResponseTypes permission) {
            this.permission = permission;
            return this;
        }


        public LogResponseFrame build() {
            return new LogResponseFrame(this);
        }
    }

    @Override
    public String getFrameTypeString() {
        return getFrameType().toString();
    }

    public LogResponseTypes getPermission() {
        return permission;
    }
}

