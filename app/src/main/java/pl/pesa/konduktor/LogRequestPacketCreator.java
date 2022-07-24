package pl.pesa.konduktor;

import java.util.Calendar;

import pl.pesa.konduktor.packet.PacketTypes;
import pl.pesa.konduktor.packet.JsonSerializer;
import pl.pesa.konduktor.packet.LogRequestPacket;

public class LogRequestPacketCreator {
    private DeviceIp deviceIp = new DeviceIp();
    private String deviceIpAddress;

    public String crateLogRequestPacket(String userName, String password, String deviceIpAddress) {

        this.deviceIpAddress = deviceIpAddress;

        JsonSerializer serializedFrame = new JsonSerializer();
        String content = serializedFrame.crateJson(LogRequestPacket.builder()
                .appVersion("1.0")
                .packetType(PacketTypes.LOGREQUEST)
                .utc(Calendar.getInstance().getTimeInMillis())
                .user(userName)
                .pass(password)
                .ipAddress(deviceIpAddress)
                .build());

        return content;
    }
}
