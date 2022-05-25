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
    private static boolean isValueChanged;
    private static String nextStopValue;
    private static String speedValue;
    private static String passengerStatsValue;
    private static String boardingStatsValue;
    private static String unBoardingStatsValue;

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
        unBoardingStats = getView().findViewById(R.id.valueUnboardingStats);
        nextStop.setText("------");
        speed.setText("-");
        passengerStats.setText("-");
        boardingStats.setText("-");
        unBoardingStats.setText("-");
        valuesUpdate();
    }

    private void valuesUpdate() {
        while (isValueChanged) {
            System.out.println("Aktualizuje wartości");
            nextStop.setText(nextStopValue);
            speed.setText(speedValue);
            isValueChanged=false;
        }
    }

    public static void setData(DataFrame dataFrame) {
        nextStopValue=dataFrame.getNextStop();
        speedValue=dataFrame.getCurrentSpeed();
        isValueChanged=true;
    }

}