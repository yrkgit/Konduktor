package pl.pesa.konduktor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {
   private TextView nextStop, speed, passengerStats, boardingStats, unboardingStats;

    public TextView getNextStop() {
        return nextStop;
    }

    public void setNextStop(TextView nextStop) {
        this.nextStop = nextStop;
    }

    public TextView getSpeed() {
        return speed;
    }

    public void setSpeed(TextView speed) {
        this.speed = speed;
    }

    public TextView getPassengerStats() {
        return passengerStats;
    }

    public void setPassengerStats(TextView passengerStats) {
        this.passengerStats = passengerStats;
    }

    public TextView getBoardingStats() {
        return boardingStats;
    }

    public void setBoardingStats(TextView boardingStats) {
        this.boardingStats = boardingStats;
    }

    public TextView getUnboardingStats() {
        return unboardingStats;
    }

    public void setUnboardingStats(TextView unboardingStats) {
        this.unboardingStats = unboardingStats;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        nextStop.setText("------");
        speed.setText("-");
        passengerStats.setText("-");
        boardingStats.setText("-");
        unboardingStats.setText("-");
    }

}