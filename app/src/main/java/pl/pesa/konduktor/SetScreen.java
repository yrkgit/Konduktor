/** Class that enable fullscreen / immersive mode in activities*/
package pl.pesa.konduktor;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SetScreen  {

    public void screenSetUp(AppCompatActivity screen) {
        screen.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}