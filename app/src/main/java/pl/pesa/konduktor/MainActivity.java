package pl.pesa.konduktor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, Screen {


    private final SetScreen setScreen = new SetScreen();
    private boolean isPermissionGranted;
    private MapView mapView;
    private GoogleMap googleMap;
    private FragmentManager fragmentManager;
    private BottomMenuFragment bottomMenu;

    @Override
    public void onBackPressed() {
        //TODO popup sure to log of?
       // DataFromHubListener.stopServer();
        Intent intent = new Intent(MainActivity.this, LogonActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Konduktor);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomMenu = new BottomMenuFragment();

        mapView = findViewById(R.id.mapView);
        setFragmentContent(R.id.topBarLayout, TopBarFragment.class);
        setFragmentContent(R.id.sideBarLayout, SideBarFragment.class);
        setFragmentContent(R.id.mainLayout, MainFragment.class);
        setFragmentContent(R.id.bottomLayout, BottomMenuFragment.class);

        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        }
        screenSetUp();



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

    // Enable fullscreen / immersive mode
    @Override
    public void screenSetUp() {
        setScreen.screenSetUp(this);
    }

    //PERMISSION VERIFICATION USING EXTERNAL LIBRARY "DEXTER"
    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
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

    //TODO do posprz??tania po metodzie zoomowania
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMyLocationEnabled(true);
        zoomOnMap();
    }

//    //TODO USUNIECIA - TESTY
//    public void onClickButtonTest(View view) {
//        TopBarFragment.displayMessage(MessagePriorities.HIGH, "Przycisk SOS - toaleta");
//        MainFragment.demoMode();
//    }

    public void onClickComfort(View view) {
        setFragmentContent(R.id.mainLayout, ComfortFragment.class);
        mapView.setVisibility(View.GONE);
        SideBarFragment.showBackButton();
        bottomMenu.onClickComfortButton(view);
    }

    public void onClickCctv(View view) {
        setFragmentContent(R.id.mainLayout, CctvFragment.class);
        mapView.setVisibility(View.GONE);
        SideBarFragment.showBackButton();
        bottomMenu.onClickCctvButton(view);

    }

    public void onClickBack(View view) {
        setFragmentContent(R.id.mainLayout, MainFragment.class);
        mapView.setVisibility(View.VISIBLE);
        SideBarFragment.hideBackButton();
        bottomMenu.unClickOtherButtons();
    }

    public void onClickLock(View view) {
        Intent intent = new Intent(MainActivity.this, LockScreenActivity.class);
        startActivity(intent);
    }

    public void setFragmentContent(int fragmentToChange, Class fragmentNewContent) {
        fragmentManager.beginTransaction()
                .replace(fragmentToChange, fragmentNewContent, null)
                .addToBackStack(null)
                .commit();
    }

    //TODO doda?? od??wierzanie mapy na osobnym procesie
    //TODO do posprz??tania po metodzie zoomowania
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void zoomOnMap() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

}