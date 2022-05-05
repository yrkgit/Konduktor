package pl.pesa.konduktor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ComfortFragment extends Fragment {
    private FrameLayout saveButton;
    private FrameLayout cancelButton;
    //TODO - bound in one button
    private TextView tempEdit;
    private ImageView tempEditIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comfort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = getView().findViewById(R.id.saveButton);
        cancelButton = getView().findViewById(R.id.cancelButton);
        tempEdit = getView().findViewById(R.id.tempEditlabel);
        tempEditIcon = getView().findViewById(R.id.tempEditIcon);

        saveButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);

        tempEdit.setOnClickListener(view1 -> enterTempEditMode());
        cancelButton.setOnClickListener(view1 -> closeTempEditMode());
    }

    public void enterTempEditMode() {
        tempEdit.setVisibility(View.INVISIBLE);
        tempEditIcon.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
    }

    public void closeTempEditMode() {
        tempEdit.setVisibility(View.VISIBLE);
        tempEditIcon.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
    }
}