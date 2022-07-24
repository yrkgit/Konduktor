/**
 * Runnable class that opening socket (by invoking SocketListener) and starting listening to LogResponsePacket from ConductorHub and logon when receive permission
 */

package pl.pesa.konduktor;


import pl.pesa.konduktor.packet.LogResponsePacket;
import pl.pesa.konduktor.packet.Packet;
import pl.pesa.konduktor.packet.PacketTypes;
import pl.pesa.konduktor.packet.JsonDeserializer;
import pl.pesa.konduktor.packet.LogResponseTypes;

public class LogResponseFromHubListener extends SocketListener implements Runnable {
    private String content;
    private LogonActivity logonActivity;
    private JsonDeserializer deserializer;
    private Packet packet;
    private int portToOpenNumber;


    public LogResponseFromHubListener(LogonActivity logonActivity) {
        this.logonActivity = logonActivity;
        //TODO - move port number to config
        portToOpenNumber = 7803;
    }

    @Override
    public void run() {
        System.out.println("Start listening for LogResponse");
        deserializer = new JsonDeserializer();
        try {
            content = startSocketListener(portToOpenNumber);
            System.out.println("Received packet : " + content);
            packet = deserializer.deserializeJsonToPacket(content);
            if (packet !=null && packet.getPacketType().equals(PacketTypes.LOGRESPONSE)) {
                LogResponsePacket logResponsePacket = (LogResponsePacket) packet;
                System.out.println(logResponsePacket.getPermission().toString());
                if (logResponsePacket.getPermission().equals(LogResponseTypes.GRANTED)) {
                    System.out.println("Access GRANTED to user: "+logonActivity.getUserName());
                    logonActivity.log();
                } else if (logResponsePacket.getPermission().equals(LogResponseTypes.DENIED)) {
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
            e.printStackTrace();
        }

    }
}
