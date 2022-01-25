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
        //Hide AcctionBar
//        try {
//            this.getSupportActionBar().hide();
//        } catch (NullPointerException e) {
//        }
        // Hide the status bar.
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

// Enable fullscreen / immersive mode
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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