package kickstart.com.kickstarttask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import kickstart.com.kickstarttask.Activity.MainActivity;
import kickstart.com.kickstarttask.Adapter.CustomInfoWindowAdapter;
import kickstart.com.kickstarttask.Model.Content;

/**
 * Created by Fehoo on 2/21/2018.
 */

public class MarkerUtility {
    //Added public method to be called from the Activity
    public void placeMarker(Activity context , String title, String subTitle, String sentiment, GoogleMap mMap, LatLng marker) {
        if (mMap != null) {
            //detect marker color
            float bdf;
            if (sentiment.contains("Neutral")) {
                bdf = BitmapDescriptorFactory.HUE_BLUE;
            } else if (sentiment.contains("Negative")) {
                bdf = BitmapDescriptorFactory.HUE_RED;
            } else if (sentiment.contains("Positive")) {
                bdf = BitmapDescriptorFactory.HUE_GREEN;
            } else {
                bdf = BitmapDescriptorFactory.HUE_BLUE;
            }
            //Marker option
            MarkerOptions markerOpt = new MarkerOptions();
            markerOpt.position(marker)
                    .title(title+","+sentiment)
                    .snippet(subTitle)
                    .draggable(false)
                    .icon(BitmapDescriptorFactory.defaultMarker(bdf));
            //Set Custom InfoWindow Adapter
            CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(context);
            mMap.setInfoWindowAdapter(adapter);
            //marker Animation
            Marker pinnedMarker = mMap.addMarker(markerOpt);
            startDropMarkerAnimation(pinnedMarker , mMap);
        }
    }

    private void startDropMarkerAnimation(final Marker marker , GoogleMap mMap) {
        final LatLng target = marker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point targetPoint = proj.toScreenLocation(target);
        final long duration = (long) (300 + (targetPoint.y * 0.6));
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        startPoint.y = 0;
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final Interpolator interpolator = new LinearOutSlowInInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * target.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * target.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    // Post again 16ms later == 60 frames per second
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}
