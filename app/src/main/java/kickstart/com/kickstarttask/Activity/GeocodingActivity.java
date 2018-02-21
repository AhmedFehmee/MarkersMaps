package kickstart.com.kickstarttask.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kickstart.com.kickstarttask.R;
import kickstart.com.kickstarttask.Service.GeocodingLocation;


public class GeocodingActivity extends Activity {

    @BindView(R.id.addressTV)
    TextView addressTV;
    @BindView(R.id.addressET)
    EditText addressET;
    @BindView(R.id.addressButton)
    Button addressButton;
    @BindView(R.id.latLongTV)
    TextView latLongTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoding);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.addressButton)
    public void onViewClicked() {
        String address = addressET.getText().toString();
        GeocodingLocation locationAddress = new GeocodingLocation();
        locationAddress.getAddressFromLocation(address, "", "",
                getApplicationContext(), new GeocoderHandler());
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
            latLongTV.setText(locationAddress);
        }
    }
}