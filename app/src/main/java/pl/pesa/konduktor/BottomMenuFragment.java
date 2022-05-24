package pl.pesa.konduktor;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Class describing application bottom menu.
 */

public class BottomMenuFragment extends Fragment {
    private TextView comfortButton, sipButton, cctvButton, szpButton;


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


    }


    public void onClickComfortButton(View v) {
        unClickOtherButtons();
        comfortButton = v.findViewById(R.id.comfortButtonLabel);
        comfortButton.setTextColor(Color.parseColor("#1AFFFFFF"));
    }

    public void onClickSipButton(View v) {
        unClickOtherButtons();
        sipButton = v.findViewById(R.id.sipButtonLabel);
        sipButton.setTextColor(Color.parseColor("#1AFFFFFF"));
    }

    public void onClickCctvButton(View v) {
        unClickOtherButtons();
        cctvButton = v.findViewById(R.id.cctvButtonLabel);
        cctvButton.setTextColor(Color.parseColor("#1AFFFFFF"));
    }

    public void onClickSzpButton(View v) {
        unClickOtherButtons();
        szpButton = v.findViewById(R.id.szpButtonLabel);
        szpButton.setTextColor(Color.parseColor("#1AFFFFFF"));
    }

    public void unClickOtherButtons() {
        if (comfortButton != null) {
            comfortButton.setTextColor(Color.WHITE);
        }
        if (sipButton != null) {
            sipButton.setTextColor(Color.WHITE);
        }
        if (cctvButton != null) {
            cctvButton.setTextColor(Color.WHITE);
        }
        if (szpButton != null) {
            szpButton.setTextColor(Color.WHITE);
        }

    }
}