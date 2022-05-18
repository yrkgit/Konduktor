package pl.pesa.konduktor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

import pl.pesa.konduktor.frames.FrameTypes;
import pl.pesa.konduktor.frames.JsonSerializer;
import pl.pesa.konduktor.frames.LogRequestFrame;

public class LogonActivity extends AppCompatActivity implements Screen {

    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenSetUp();
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        userName = findViewById(R.id.editTextUserName);
        password = findViewById(R.id.editTexPassword);
        //TODO create clean socket listener

        Thread thread = new Thread(new AccessFromHubListener(this));
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

        JsonSerializer serializedFrame = new JsonSerializer();
//        String content = serializedFrame.crateJson(new LogRequestFrame("1.0",
//                FrameTypes.LOGREQUEST,
//                Calendar.getInstance().getTimeInMillis(),
//                userName.getText().toString(),
//                password.getText().toString(),
//                "10.1.1.1"));
        String content = serializedFrame.crateJson(LogRequestFrame.builder()
                .appVersion("1.0")
                .frameType(FrameTypes.LOGREQUEST)
                .utc(Calendar.getInstance().getTimeInMillis())
                .user(userName.getText().toString())
                .pass(password.getText().toString())
                .ipAddress("10.1.1.1")
                .build());

        StringToServerSender stringToServerSender = new StringToServerSender(content);
        System.out.println(" send to server");
        stringToServerSender.execute();


    }

    public void log() {
        Intent intent = new Intent(LogonActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

}