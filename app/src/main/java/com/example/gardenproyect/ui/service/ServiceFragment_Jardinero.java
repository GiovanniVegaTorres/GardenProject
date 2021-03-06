package com.example.gardenproyect.ui.service;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.gardenproyect.R;
import com.example.gardenproyect.ui.home.HomeViewModel_Cliente;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class ServiceFragment_Jardinero extends Fragment implements OnMapReadyCallback {

    private ServiceViewModel_Jardinero ServiceViewModelJardinero;

    GoogleMap mGoogleMap;
    MapView mMapView;
    View root;
    Button btnComenzar;
    Button btnTerminar;

    private static final int LOCATION_REQUEST = 500;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ServiceViewModelJardinero =
                ViewModelProviders.of(this).get(ServiceViewModel_Jardinero.class);
        root = inflater.inflate(R.layout.fragment_service_jardinero, container, false);



        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.map);
        btnComenzar = (Button) view.findViewById(R.id.comenzarjardinero);
        btnTerminar = (Button) view.findViewById(R.id.terminarjardinero);

        btnTerminar.setVisibility(View.GONE);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnComenzar.setVisibility(View.GONE);
                btnTerminar.setVisibility(View.VISIBLE);
            }
        });

        btnTerminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_service_JardineroTwo);
            }
        });

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        marcadores(googleMap);
    }

    private void marcadores(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(20.6705541,-103.2175046)).title("Ubicaci??n del servicio"));

        CameraPosition Ubicacion = CameraPosition.builder().target(new LatLng(20.6705541,-103.2175046)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Ubicacion));
    }

}