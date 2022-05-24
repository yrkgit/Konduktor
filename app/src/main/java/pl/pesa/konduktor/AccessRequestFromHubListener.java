/**
 * Runnable class that opening socket (by invoking SocketListener) and starting listening to LogResponseFrame from ConductorHub and logon when receive permission
 */

package pl.pesa.konduktor;


import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonDeserializer;
import pl.pesa.konduktor.frames.LogResponseTypes;
import pl.pesa.konduktor.frames.LogResponseFrame;

public class AccessRequestFromHubListener extends SocketListener implements Runnable {
    private String content;
    private LogonActivity logonActivity;
    private JsonDeserializer deserializer;
    private Frame frame;
    private int portToOpenNumber;


    public AccessRequestFromHubListener(LogonActivity logonActivity) {
        this.logonActivity = logonActivity;
        //TODO - move port number to config
        portToOpenNumber = 7801;
    }

    @Override
    public void run() {
        System.out.println("Start listening for LogResponse");
        deserializer = new JsonDeserializer();
        try {
            content = startSocketListener(portToOpenNumber);
            System.out.println("Received frame : " + content);
            frame = deserializer.deserializeJsonToFrameObject(content);
            if (frame!=null && frame.getFrameType().equals(FrameTypes.LOGRESPONSE)) {
                LogResponseFrame logResponseFrame = (LogResponseFrame) frame;
                System.out.println(logResponseFrame.getPermission().toString());
                if (logResponseFrame.getPermission().equals(LogResponseTypes.GRANTED)) {
                    System.out.println("Access GRANTED to user: "+logonActivity.getUserName());
                    logonActivity.log();
                } else if (logResponseFrame.getPermission().equals(LogResponseTypes.DENIED)) {
                    logonActivity.showToast(logonActivity.getString(R.string.accessDenied));
                    System.out.println("Access DENIED");
                    run();
                }
            } else {
                logonActivity.showToast(logonActivity.getString(R.string.noResponse));
                System.out.println("No permission received from HUB");
                run();
            }

        } catch (Exception e) {
            logonActivity.showToast(logonActivity.getString(R.string.noResponse));
            System.out.println("ERROR during analyzing LogResponse");
        }

    }
}
