package pl.pesa.konduktor;

import java.util.Calendar;

import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonSerializer;
import pl.pesa.konduktor.frames.LogRequestFrame;

public class LogRequestFrameCreator {
    private DeviceIp deviceIp = new DeviceIp();
    private String deviceIpAddress;
    public String crateLogRequestFrame(String userName, String password, String deviceIpAddress) {
        //service mode
        //TODO change to service mode log and password

        this.deviceIpAddress = deviceIpAddress;

        JsonSerializer serializedFrame = new JsonSerializer();
        String content = serializedFrame.crateJson(LogRequestFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.LOGREQUEST)
                .utc(Calendar.getInstance().getTimeInMillis())
                .user(userName)
                .pass(password)
                .ipAddress(deviceIpAddress)
                .build());


        return content;
    }
}
