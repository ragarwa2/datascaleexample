package mimove.inria.fr.datascaleexample;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import mimove.inria.fr.datascaleexample.crowdsourcing.SendDataHelper;
import mimove.inria.fr.datascaleexample.models.Station;
import mimove.inria.fr.datascaleexample.models.Mood;

public class MainActivity extends Activity {

    private static String Tag = "Home";

    private Button _BT_Submit;
    private String currentMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        //Initialize crowdsourcing
        //important
        SendDataHelper.getInstance().initCrowdsourcing(getApplicationContext());

    }

    public void initView() {
        setUpRadios();
        _BT_Submit = (Button) findViewById(R.id.submitButton);
        _BT_Submit.setEnabled(true);
        _BT_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMoodToServer();
                Toast.makeText(MainActivity.this.getApplicationContext(), "Mood sent", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void sendMoodToServer() {
        SendDataHelper.getInstance().connectCrowdsourcing();

        try {
            Mood mood = new Mood();
            Log.d(Tag, "hhere");
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            mood.setTime(Calendar.getInstance());
            mood.setMood(currentMood);
            if (location != null) {
                if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
                    Station station = new Station("uniqueID", location.getLatitude(), location.getLongitude());
                    //Log.d(Tag, String.valueOf(location.getLatitude()));
                    SendDataHelper.getInstance().sendMoodToServer(mood, station);
                }
            } else {
                Log.d(Tag, "h2");
                Station station = new Station("uniqueID", 10.0, 2.0);
                //Log.d(Tag, String.valueOf(location.getLatitude()));
                SendDataHelper.getInstance().sendMoodToServer(mood, station);
            }
            Log.d(Tag, "h3");
            SendDataHelper.getInstance().disconnectCrowdsourcing();
        } catch (Exception e) {
            Log.d(Tag, "error here" + e.getMessage());
            return;
        }
    }

    private void setUpRadios() {
        RadioButton sleepStartSelected = (RadioButton) findViewById(R.id.state_1);

        sleepStartSelected.setChecked(true);
        currentMood = "happy";

        RadioGroup radioGroupMood = (RadioGroup) findViewById(R.id.feeling);
        radioGroupMood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.state_1: {
                        currentMood = "happy";
                        break;
                    }
                    case R.id.state_2: {
                        currentMood = "sad";
                        break;
                    }
                    case R.id.state_3: {
                        currentMood = "not bad";
                        break;
                    }

                }
            }
        });
    }
}
