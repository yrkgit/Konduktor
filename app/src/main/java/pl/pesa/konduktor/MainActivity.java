package pl.pesa.konduktor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        // Hide the status bar.
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);
        //RUN IN PRESENTATION/DEMO MODE
        demoMode();
    }

    //Mode to present application with some example values
    public void demoMode() {
        TextView nextStop, speed, passengerStats, boardingStats, unboardingStats;

        nextStop = findViewById(R.id.valueNextStop);
        nextStop.setText("Bydgoszcz Główna");

        speed = findViewById(R.id.valueSpeed);
        speed.setText("70 km/h");

        passengerStats = findViewById(R.id.valuePassengeStats);
        passengerStats.setText("67");

        boardingStats = findViewById(R.id.valueBoardingStats);
        boardingStats.setText("40");

        unboardingStats = findViewById(R.id.valueUnboardingStats);
        unboardingStats.setText("12");
    }

}