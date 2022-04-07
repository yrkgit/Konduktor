package pl.pesa.konduktor;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {
   static TextView nextStop, speed, passengerStats, boardingStats, unboardingStats;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextStop = getView().findViewById(R.id.valueNextStop);
        speed = getView().findViewById(R.id.valueSpeed);
        passengerStats = getView().findViewById(R.id.valuePassengeStats);
        boardingStats = getView().findViewById(R.id.valueBoardingStats);
        unboardingStats = getView().findViewById(R.id.valueUnboardingStats);
        nextStop.setText("-");
        speed.setText("-");
        passengerStats.setText("-");
        boardingStats.setText("-");
        unboardingStats.setText("-");
    }

    //Mode to present application with some example values
    public static void demoMode() {
        nextStop.setText("Bydgoszcz Główna");
        speed.setText("70 km/h");
        passengerStats.setText("67");
        boardingStats.setText("40");
        unboardingStats.setText("12");
    }

}