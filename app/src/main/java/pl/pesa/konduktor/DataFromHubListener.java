package pl.pesa.konduktor;

import pl.pesa.konduktor.frames.DataFrame;
import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;


public class DataFromHubListener extends SocketListener implements Runnable {
    private String content;
    private static boolean isServerAlreadyRunning;
    private static boolean isServerPaused;
    private MainFragment mainFragment;
    private JsonDeserializer deserializer;
    private Frame frame;
    private final int portToOpenNumber;


    public DataFromHubListener(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
        //TODO - move port number to config
        portToOpenNumber = 7802;
    }

    public static void stopServer() {
        isServerPaused = false;
    }

    public static void startServer() {
        isServerPaused = true;
    }

    @Override
    public void run() {
        deserializer = new JsonDeserializer();
        if (!isServerAlreadyRunning) {
            System.out.println("Start listening for data");
            isServerAlreadyRunning =true;
            while (!isServerPaused) {
                try {
                    content = startSocketListener(portToOpenNumber);
                    System.out.println("Received frame : " + content);
                    frame = deserializer.deserializeJsonToFrameObject(content);
                    if (frame != null && frame.getFrameType().equals(FrameTypes.DATA)) {
                        DataFrame dataFrame = (DataFrame) frame;
                        mainFragment.setData(dataFrame);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
