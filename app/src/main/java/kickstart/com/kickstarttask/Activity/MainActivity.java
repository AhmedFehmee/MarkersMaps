package kickstart.com.kickstarttask.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kickstart.com.kickstarttask.Service.GeocodingLocation;
import kickstart.com.kickstarttask.MarkerUtility;
import kickstart.com.kickstarttask.Model.EntryItem;
import kickstart.com.kickstarttask.R;
import kickstart.com.kickstarttask.Service.SpreadsheetsService;
import kickstart.com.kickstarttask.Utility;

public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Toolbar toolbar;
    ArrayList<EntryItem> messagesList = new ArrayList<>();
    int messagesCount = 0;
    int loopOnMessageWords = 1;
    Boolean isMarkedPlaced = true;

    @BindView(R.id.btn_marker)
    Button btnMarker;
    @BindView(R.id.card_reload_data)
    CardView cardReloadData;
    @BindView(R.id.avi_progress)
    com.wang.avi.AVLoadingIndicatorView aviProgress;
    @BindView(R.id.progress_frame)
    FrameLayout progressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (Utility.isNetworkAvailable(getApplicationContext())) {
            progressFrame.setVisibility(View.VISIBLE);
            SpreadsheetsService spreadsheetsService = new SpreadsheetsService();
            spreadsheetsService.callSpreadsheetsService(getApplicationContext(), messagesList);
        } else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    //Menu Work
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(Html.fromHtml(MainActivity.this.getString(R.string.info_text) +
                    " <a href='https://github.com/AhmedFehoO/KickStart'>GitHub.</a>"));
            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        } else if (id == R.id.action_service) {
            startActivity(new Intent(MainActivity.this, GeocodingActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);
        buildGoogleApiClient();

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("Map", "Connected");
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        setMarker();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    //Start Handle Message
    private void setMarker() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        Thread.sleep(200);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {
                            @Override
                            public void run() {
                                if (isMarkedPlaced && messagesList.size() != 0 && messagesList.size() > messagesCount) {
                                    progressFrame.setVisibility(View.GONE);
                                    isMarkedPlaced = false;
                                    getAddress(messagesList.get(messagesCount), loopOnMessageWords);
                                    messagesCount++;
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        // ooops
                    }
            }
        })).start();
    }

    //Divide Message to ID , Address , Sentiment
    private void getAddress(EntryItem entryItem, int loop) {
        String[] userMessage = (entryItem.getContent().getT().split(":"));
        String messageID = userMessage[0];
        String address = userMessage[2];
        String sentiment = userMessage[3];

        String[] addressesDivider = (address.split(","));
        String subAddress = addressesDivider[0];
        for (int i = 1; i < addressesDivider.length; i++) {
            if (!addressesDivider[i].contains("sentiment")) {
                subAddress += addressesDivider[i];
            }
        }
        getLocation(subAddress, sentiment, loop);
    }

    //call GeoCoder with one Word every time
    private void getLocation(String message, String sentiment, int loop) {
        String[] subAddresses = (message.split(" "));
        if (subAddresses.length - 1 >= loop) {
            GeocodingLocation.getAddressFromLocation(subAddresses[subAddresses.length - loop], sentiment, message,
                    getApplicationContext(), new GeocoderHandler());
            Log.i("City ", subAddresses[subAddresses.length - loop]);
        } else {
            isMarkedPlaced = true;
            loopOnMessageWords = 1;
        }
    }

    @OnClick({R.id.btn_marker, R.id.card_reload_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_reload_data:
                if (Utility.isNetworkAvailable(getApplicationContext())) {
                    progressFrame.setVisibility(View.VISIBLE);
                    mMap.clear();
                    messagesCount = 0;
                    isMarkedPlaced = true;
                    loopOnMessageWords = 1;
                    messagesList.clear();
                    SpreadsheetsService spreadsheetsService = new SpreadsheetsService();
                    spreadsheetsService.callSpreadsheetsService(getApplicationContext(), messagesList);
                    setMarker();
                } else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            String[] latLng = (locationAddress.split("\n"));
            if (!latLng[1].contains("Unable")) {
                //parse lat , lon
                double latitude = Double.parseDouble(latLng[1]);
                double longitude = Double.parseDouble(latLng[2]);
                String sentiment = latLng[3];
                String messageText = latLng[4];

                //set Initial
                isMarkedPlaced = true;
                loopOnMessageWords = 1;

                //set Marker
                LatLng marker = new LatLng(latitude, longitude);
                MarkerUtility markerUtility = new MarkerUtility();
                markerUtility.placeMarker(MainActivity.this, messageText,
                        latLng[1] + "," + latLng[2] + "," + latLng[0], sentiment, mMap, marker);
            } else {
                //failed , call with pre word of message
                loopOnMessageWords++;
                getAddress(messagesList.get(messagesCount - 1), loopOnMessageWords);
            }
        }
    }
}
