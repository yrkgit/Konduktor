package pl.pesa.konduktor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
        setContentView(R.layout.activity_main);
        //RUN IN PRESENTATION/DEMO MODE
        demoMode();
    }

    //Mode to present application with some example values
    public void demoMode() {
        TextView nextStop, speed, passengerStats, boardingStats, unboardingStats;

        nextStop =  findViewById(R.id.valueNextStop);
        nextStop.setText("Bydgoszcz Główna");

        speed= findViewById(R.id.valueSpeed);
        speed.setText("70 km/h");

        passengerStats= findViewById(R.id.valuePassengeStats);
        passengerStats.setText("67");

        boardingStats= findViewById(R.id.valueBoardingStats);
        boardingStats.setText("40");

        unboardingStats= findViewById(R.id.valueUnboardingStats);
        unboardingStats.setText("12");
    }

}