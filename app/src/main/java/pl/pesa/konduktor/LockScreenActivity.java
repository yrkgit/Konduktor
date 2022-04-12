package pl.pesa.konduktor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {

    View logOnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        screenSetUp();
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);



    }
//TO DO WYRZUCI SCREENSETUP DO KLASY SCREEN
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
        Intent intent = new Intent(LockScreenActivity.this, MainActivity.class );
        startActivity(intent);
    }
}