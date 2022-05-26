package pl.pesa.konduktor;

import pl.pesa.konduktor.frames.DataFrame;
import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;


public class DataFromHubListener extends SocketListener implements Runnable {
    private String content;
    private static boolean isServerRunning;
    private MainFragment mainFragment;
    private JsonDeserializer deserializer;
    private Frame frame;
    private int portToOpenNumber;


    public DataFromHubListener(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
        isServerRunning = false;
        //TODO - move port number to config
        portToOpenNumber = 7802;
    }

    public static void stopServer() {
        isServerRunning = false;
    }

    public static void startServer() {
        isServerRunning = true;
    }

    @Override
    public void run() {
        System.out.println("Start listening for data");
        deserializer = new JsonDeserializer();
        while (!isServerRunning) {
            try {
                content = startSocketListener(portToOpenNumber);
                System.out.println("Received frame : " + content);
                frame = deserializer.deserializeJsonToFrameObject(content);
                if (frame!= null && frame.getFrameType().equals(FrameTypes.DATA)) {
                    DataFrame dataFrame = (DataFrame) frame;
                    mainFragment.setData(dataFrame);
                }
            } catch (Exception e) {

            }
        }
    }
}
