package pl.pesa.konduktor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    boolean isPermissionGranted;
    MapView mapView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapView);
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        }

//TO DO - Zweryfikować ustawienia ekranu dla onResume i reszty
// Enable fullscreen / immersive mode

        screenSetUp();

        //RUN IN PRESENTATION/DEMO MODE
        demoMode();

    }

    public void screenSetUp() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        screenSetUp();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        screenSetUp();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        screenSetUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        screenSetUp();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        screenSetUp();
        mapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        screenSetUp();
        mapView.onLowMemory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        screenSetUp();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenSetUp();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        screenSetUp();
    }


    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        super.onPanelClosed(featureId, menu);
        screenSetUp();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        screenSetUp();
        mapView.onSaveInstanceState(outState);
    }

    //PERMISSION VERIFICATION USING EXTERNAL LIBRARY "DEXTER"
    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(MainActivity.this, "Permision Granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    //Mode to present application with some example values
    public void demoMode() {
        TextView nextStop, speed, passengerStats, boardingStats, unboardingStats;

        nextStop = findViewById(R.id.valueNextStop);
        nextStop.setText("Bydgoszcz Główna");

        speed = findViewById(R.id.valueSpeed);
        speed.setText("70 km/h");

        passengerStats = findViewById(R.id.valuePassengeStats);
        passengerStats.setText("67");

        boardingStats = findViewById(R.id.valueBoardingStats);
        boardingStats.setText("40");

        unboardingStats = findViewById(R.id.valueUnboardingStats);
        unboardingStats.setText("12");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.1, -87.9)));

    }
}