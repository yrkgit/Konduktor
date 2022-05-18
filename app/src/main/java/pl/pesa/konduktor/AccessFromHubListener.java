package pl.pesa.konduktor;

import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.LogResponseFrame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;
import pl.pesa.konduktor.frames.LogResponseTypes;

public class AccessFromHubListener extends SocketListener implements Runnable {
    private String content;
    private LogonActivity logonActivity;
    private JsonDeserializer deserializer;
    private Frame frame;


    public AccessFromHubListener(LogonActivity logonActivity) {
        this.logonActivity = logonActivity;
    }
//TODO fix log on problem after first received DENIED
    @Override
    public void run() {
        deserializer = new JsonDeserializer();
        try {
            content = startSocketListener();
            System.out.println("Received frame : " +content);
            frame = deserializer.deserializeJsonToFrameObject(content);
            if (frame.getFrameType().equals(FrameTypes.LOGRESPONSE)){
                LogResponseFrame logResponseFrame = (LogResponseFrame) frame;
                System.out.println(logResponseFrame.getPermission().toString());
                if(logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)){
                    logonActivity.log();
                }else{
                    //TODO - add access denied message
                }

            }

        } catch (Exception e) {

        }

    }
}
