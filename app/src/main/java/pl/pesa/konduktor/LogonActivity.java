package pl.pesa.konduktor;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonSerializer;
import pl.pesa.konduktor.frames.LogRequestFrame;

public class LogonActivity extends AppCompatActivity implements Screen {

    private EditText userName;
    private EditText password;
    private String deviceIpAddress;

    public String getUserName() {
        return userName.toString();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenSetUp();
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        userName = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTexPassword);

        Thread thread = new Thread(new AccessRequestFromHubListener(this));
        thread.start();
    }

    //TODO WYRZUCI SCREENSETUP DO KLASY SCREEN
    @Override
    public void screenSetUp() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void onClickButton(View view) {

        //service mode
        //TODO change to service mode log and password
        if (userName.getText().toString().equals("asd")) {
            log();
        } else {
            deviceIpAddress = getDeviceIpAddress();

            JsonSerializer serializedFrame = new JsonSerializer();
            String content = serializedFrame.crateJson(LogRequestFrame.builder()
                    .appVersion("1.0")
                    .frameType(FrameTypes.LOGREQUEST)
                    .utc(Calendar.getInstance().getTimeInMillis())
                    .user(userName.getText().toString())
                    .pass(password.getText().toString())
                    .ipAddress(deviceIpAddress)
                    .build());

            System.out.println("Trying to send: " + content + " to server");
            StringToServerSender stringToServerSender = new StringToServerSender(content);
            stringToServerSender.execute();
        }


    }

    private String getDeviceIpAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String deviceIpAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        return deviceIpAddress;
    }

    public void log() {
        Intent intent = new Intent(LogonActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    public void showToast(final String toast)
    {
        runOnUiThread(() -> Toast.makeText(LogonActivity.this, toast, Toast.LENGTH_LONG).show());
    }
}