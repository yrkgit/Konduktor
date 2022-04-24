package pl.pesa.konduktor;
/**
 * Class containing device parameters for Supervisor module
 * Those parameters are send to Supervisor module during the logon procedure*/
import java.util.Date;

public class DeviceStatus {

    private Date date;
    private String version;
    private String ip;
    private String userName;
    private String password;

}
