package pl.pesa.konduktor;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import androidx.appcompat.app.AppCompatActivity;

public class DeviceIp extends AppCompatActivity{
    public String getDeviceIpAddress(LogonActivity logonActivity) {
        WifiManager wifiManager = (WifiManager) logonActivity.getApplicationContext().getSystemService(WIFI_SERVICE);
        String deviceIpAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        return deviceIpAddress;
    }
}
