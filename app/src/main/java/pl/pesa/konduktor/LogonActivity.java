package pl.pesa.konduktor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogonActivity extends AppCompatActivity implements Screen {

    private SetScreen setScreen;
    private LogRequestFrameCreator logRequestFrameCreator;
    private EditText userName;
    private EditText password;
    private DeviceIp deviceIp;
    private String frameContent;
    private Thread thread;

    public String getUserName() {
        return userName.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setScreen = new SetScreen();
        logRequestFrameCreator = new LogRequestFrameCreator();
        deviceIp = new DeviceIp();

        screenSetUp();
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        userName = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTexPassword);

        thread = new Thread(new LogResponseFromHubListener(this));
        thread.start();
    }

    @Override
    public void screenSetUp() {
        setScreen.screenSetUp(this);
    }

    public void onClickButton(View view) {
        //TODO change to service mode log and password
        if (userName.getText().toString().equals("asd")) {
            log();
        }else if(userName.getText().toString().equals("")){
            showToast("Podaj nazwę użytkownika");
        }else if(password.getText().toString().equals("")){
            showToast("Podaj hasło");
        }
        else {
            frameContent=logRequestFrameCreator.crateLogRequestFrame(
                    userName.getText().toString(), password.getText().toString(), deviceIp.getDeviceIpAddress(this));
            System.out.println("Trying to send: " + frameContent + " to server");
            StringToServerSender stringToServerSender = new StringToServerSender(frameContent);
            stringToServerSender.execute();
        }
    }

    public void log() {
        thread.interrupt();
        System.out.println("Wątek serwera" + thread.isAlive());
        Intent intent = new Intent(LogonActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    public void showToast(final String toast) {
        runOnUiThread(() -> Toast.makeText(LogonActivity.this, toast, Toast.LENGTH_LONG).show());
    }
}