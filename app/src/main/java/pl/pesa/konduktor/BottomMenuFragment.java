package pl.pesa.konduktor;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BottomMenuFragment extends Fragment {

    private TextView comfortButton, sipButton, cctvButton, szpButton;
    MainActivity mainActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bottom_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        comfortButton = getView().findViewById(R.id.comfortButtonLabel);
        sipButton = getView().findViewById(R.id.sipButtonLabel);
        cctvButton = getView().findViewById(R.id.cctvButtonLabel);
        szpButton = getView().findViewById(R.id.szpButtonLabel);

    }

    public void onClickComfortButton() {
    comfortButton.setText("TEST");
    }
    public void onClickSipButton() {

    }
    public void onClickCctvButton() {

    }
    public void onClickSzpButton() {

    }
}