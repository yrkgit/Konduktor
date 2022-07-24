package pl.pesa.konduktor;

import pl.pesa.konduktor.packet.DataPacket;
import pl.pesa.konduktor.packet.Packet;
import pl.pesa.konduktor.packet.PacketTypes;
import pl.pesa.konduktor.packet.JsonDeserializer;


public class DataFromHubListener extends SocketListener implements Runnable {
    private String content;
    private static boolean isServerAlreadyRunning;
    private static boolean isServerPaused;
    private MainFragment mainFragment;
    private JsonDeserializer deserializer;
    private Packet packet;
    private final int portToOpenNumber;


    public DataFromHubListener(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
        //TODO - move port number to config
        portToOpenNumber = 7802;
    }

    public static void stopServer() {
        isServerPaused = true;
    }

    public static void startServer() {
        isServerPaused = false;
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
                    System.out.println("Received packet : " + content);
                    packet = deserializer.deserializeJsonToPacket(content);
                    if (packet != null && packet.getPacketType().equals(PacketTypes.DATA)) {
                        DataPacket dataPacket = (DataPacket) packet;
                        mainFragment.setData(dataPacket);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
