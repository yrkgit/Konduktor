package pl.pesa.konduktor;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TopBarFragment extends Fragment {

   static TextView messageBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        messageBox = getView().findViewById(R.id.labelMessage);
        messageBox.setVisibility(View.INVISIBLE);
    }
    public static void displayMessage(int priority, String message) {
        Handler handler = new Handler();
        messageBox.setVisibility(View.VISIBLE);
        messageBox.getBackground().setColorFilter(Color.parseColor("#FF8000"), PorterDuff.Mode.DARKEN);
        messageBox.setText(message);
        handler.postDelayed(new Runnable() {
            public void run() {
                messageBox.setVisibility(View.INVISIBLE);
            }
        }, 6000);
    }



}