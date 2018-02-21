package kickstart.com.kickstarttask.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import kickstart.com.kickstarttask.R;

/**
 * Created by Fehoo on 2/20/2018.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public CustomInfoWindowAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.customwindow, null);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvSentiment = view.findViewById(R.id.tv_sentiment);
        TextView tvCityName = view.findViewById(R.id.tv_city_name);
        TextView tvLocation = view.findViewById(R.id.tv_location);
        String[] subTitle = (marker.getTitle().split(","));
        tvTitle.setText(subTitle[0]);
        tvSentiment.setText(subTitle[1]);
        if (subTitle[1].contains("Neutral")) {
            tvSentiment.setTextColor(context.getResources().getColor(R.color.blue));
        } else if (subTitle[1].contains("Negative")) {
            tvSentiment.setTextColor(context.getResources().getColor(R.color.red));
        } else if (subTitle[1].contains("Positive")) {
            tvSentiment.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            tvSentiment.setTextColor(context.getResources().getColor(R.color.blue));
        }

        String[] subSnippet = (marker.getSnippet().split(","));
        tvCityName.setText(subSnippet[2] + " City");
        tvLocation.setText(subSnippet[0]+","+subSnippet[1]);

        return view;
    }
}
