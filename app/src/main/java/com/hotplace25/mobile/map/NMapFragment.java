package com.hotplace25.mobile.map;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hotplace25.mobile.R;
import com.hotplace25.mobile.utils.Log;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

/**
 * Created by khnam on 2018-01-29.
 */

public class NMapFragment extends  MapFragment implements NMapView.OnMapStateChangeListener {

    private final static String TAG = NMapFragment.class.getName();
    private NMapView mMapView;
    private NMapController mMapController;
    private NMapViewerResourceProvider mMapViewerResourceProvider;
    private NMapOverlayManager mMapOverlayManager;

    public static NMapFragment getInstance() {
        NMapFragment fragment = new NMapFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_map, container, false);
        mMapView = (NMapView)v.findViewById(R.id.nmapContainer);
        mMapView.setClientId(getString(R.string.nmap_client_id));
        mMapView.setClickable(true);

        Button btn = (Button)v.findViewById(R.id.btnMapClose);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.setBuiltInZoomControls(true, null);
        mMapView.setOnMapStateChangeListener(this);
        mMapController = mMapView.getMapController();
        mMapViewerResourceProvider = new NMapViewerResourceProvider(getActivity());
        mMapOverlayManager = new NMapOverlayManager(getActivity(), mMapView, mMapViewerResourceProvider);
    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if(nMapError == null) {
            moveMapCenter();
        }
        else {
            Log.e(TAG,  nMapError.message);
        }
    }

    private void moveMapCenter() {
       NGeoPoint currentPoint = new NGeoPoint(127.9204629, 36.0207091);
       mMapController.setMapCenter(currentPoint);
       mMapController.setZoomLevel(3);
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }
}
