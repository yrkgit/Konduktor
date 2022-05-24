package pl.pesa.konduktor;

import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;


public class DataFromHubListener extends SocketListener implements Runnable {
    private String content;
    private boolean isServerRunning;
    private MainActivity mainActivity;
    private JsonDeserializer deserializer;
    private Frame frame;


    public DataFromHubListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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
        System.out.println("Start listening for data");
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
