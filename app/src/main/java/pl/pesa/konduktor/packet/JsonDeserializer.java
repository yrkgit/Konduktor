package pl.pesa.konduktor.packet;

import com.google.gson.Gson;

public class JsonDeserializer {
    public Packet deserializeJsonToPacket(String content) {
        System.out.println("Start deserializing");
        //TODO add try and not null
        Gson gson = new Gson();
        Packet packet = gson.fromJson(content, Packet.class);
        if (packet.getPacketType().equals(PacketTypes.LOGRESPONSE)) {
            LogResponsePacket logResponsePacket = gson.fromJson(content, LogResponsePacket.class);
            return logResponsePacket;
        }else if (packet.getPacketType().equals(PacketTypes.DATA)) {
            System.out.println(packet);
            DataPacket dataPacket = gson.fromJson(content, DataPacket.class);
            System.out.println(packet.getPacketType());
            System.out.println(dataPacket.getNextStopName());
            return dataPacket;
        }
        else {
            return packet;
        }


    }
}

