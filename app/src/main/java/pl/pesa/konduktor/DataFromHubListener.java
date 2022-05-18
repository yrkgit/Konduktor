package pl.pesa.konduktor;

import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;
import pl.pesa.konduktor.frames.LogResponseFrame;
import pl.pesa.konduktor.frames.LogResponseTypes;

public class DataFromHubListener extends SocketListener implements Runnable {
    private String content;
    private boolean isServerRunning;
    private LogonActivity logonActivity;
    private JsonDeserializer deserializer;
    private Frame frame;


    public DataFromHubListener(LogonActivity logonActivity) {
        this.logonActivity = logonActivity;
        isServerRunning = true;
    }

    public void stopServer() {
        isServerRunning = false;
    }

    public void startServer() {
        isServerRunning = true;
    }

    @Override
    public void run() {
        deserializer = new JsonDeserializer();
        while (isServerRunning) {
            try {
                content = startSocketListener();
                System.out.println("Received frame : " + content);
                frame = deserializer.deserializeJsonToFrameObject(content);
                if (frame.getFrameType().equals(FrameTypes.DATA)) {
//                LogResponseFrame logResponseFrame = (LogResponseFrame) frame;
//                System.out.println(logResponseFrame.getPermission().toString());

                }
            } catch (Exception e) {

            }
        }
    }
}
