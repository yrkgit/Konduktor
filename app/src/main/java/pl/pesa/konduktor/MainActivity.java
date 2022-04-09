package pl.pesa.konduktor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import android.support.v4.app.*;
import android.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.TextClock;
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


    // TO DO - Przerzucić menu boczne i górny pasek do osobnych klas
    // FRAGMENTS


    boolean isPermissionGranted;
    MapView mapView;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        mapView = findViewById(R.id.mapView);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.topBarLayout,TopBarFragment.class,null)
                .replace(R.id.sideBarLayout,SideBarFragment.class,null)
                .replace(R.id.mainLayout, MainFragment.class,null)
                .commit();



//        btn1.setOnClickListener(view -> fragmentManager.beginTransaction()
//                .replace(R.id.flFragment, FirstFragment.class, null)
//                //TO DO - usunąć problem wielokrotnego wstecz przy kilkurazowym kliknieciu lub przejsciu miedzy fragmentami - wraca całą sieżkę kliknięć
//                .addToBackStack(null)
//                .commit());
//
//        btn2.setOnClickListener(view -> fragmentManager.beginTransaction()
//                .replace(R.id.flFragment, SecondFragment.class, null)
//                .addToBackStack(null)
//                .commit());
//
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        }

//TO DO - Zweryfikować ustawienia ekranu dla onResume i reszty
// Enable fullscreen / immersive mode

        screenSetUp();

        //RUN IN PRESENTATION/DEMO MODE


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
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onStart();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        screenSetUp();
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onResume();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        screenSetUp();
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onLowMemory();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        screenSetUp();
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onStop();
        }
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
        screenSetUp();
        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onPause();
        }
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
                // Toast.makeText(MainActivity.this, "Permision Granted", Toast.LENGTH_SHORT).show();
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



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);

    }
    //DO USUNIECIA - TESTY
    public void onClickButtonTest(View view) {
        System.out.println("KLIK");
        TopBarFragment.displayMessage(5,"Przycisk SOS - toaleta");
        MainFragment.demoMode();
    }
    public void onClickComfort(View view){

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainLayout, ComfortFragment.class,null)
                .commit();
        mapView.setVisibility(View.GONE);
        SideBarFragment.showBackButton();

    }
    public void onClickBack(View view){

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainLayout, MainFragment.class,null)
                .commit();
        mapView.setVisibility(View.VISIBLE);
        SideBarFragment.hideBackButton();
    }

}