package pl.pesa.konduktor.frames;

public abstract class Frame {
    protected String appVersion;
    protected FrameTypes typeOfFrame;

    protected long utc;

    public Frame(String appVersion, FrameTypes typeOfFrame, long utc) {
        setAppVersion(appVersion);
        setTypeOfFrame(typeOfFrame);
        setUtc(utc);
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public FrameTypes getTypeOfFrame() {
        return typeOfFrame;
    }

    public void setTypeOfFrame(FrameTypes typeOfFrame) {
        this.typeOfFrame = typeOfFrame;
    }

    public long getUtc() {
        return utc;
    }

    public void setUtc(long utc) {
        this.utc = utc;
    }

}
