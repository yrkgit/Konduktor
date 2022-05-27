package pl.pesa.konduktor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.pesa.konduktor.frames.DataFrame;


public class MainFragment extends Fragment {
    private TextView nextStop, speed, passengerStats, boardingStats, unBoardingStats;
    private static String nextStopValue;
    private static String speedValue;
    private static int passengerStatsValue;
    private static int boardingStatsValue;
    private static int unBoardingStatsValue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Start DATA SERVER!!!!!!");
        Thread thread = new Thread(new DataFromHubListener(this));
        thread.start();
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
        unBoardingStats = getView().findViewById(R.id.valueUnboardingStats);
        nextStop.setText("-");
        speed.setText("-");
        passengerStats.setText("-");
        boardingStats.setText("-");
        unBoardingStats.setText("-");


    }

    private void valuesUpdate() {
            nextStop.setText(nextStopValue);
            speed.setText(speedValue+" km/h");
            passengerStats.setText(String.valueOf(passengerStatsValue));
            boardingStats.setText(String.valueOf(boardingStatsValue));
            unBoardingStats.setText(String.valueOf(unBoardingStatsValue));
    }

    public void setData(DataFrame dataFrame) {
        System.out.println("SET DATA");
        nextStopValue=dataFrame.getNextStop();
        speedValue=dataFrame.getCurrentSpeed();
        passengerStatsValue=dataFrame.getPassengerStats();
        boardingStatsValue=dataFrame.getBoardingStats();
        unBoardingStatsValue= dataFrame.getUnBoardingStats();
        valuesUpdate();

    }

}