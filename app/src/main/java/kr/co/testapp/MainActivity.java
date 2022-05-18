package kr.co.testapp;

import static android.location.LocationManager.GPS_PROVIDER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.GnssClock;
import android.location.GnssMeasurement;
import android.location.GnssMeasurementsEvent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ppsoln.obslib.ObsCalculater;
import com.ppsoln.ssrgdecoder.CustomSSRClient;
import com.ppsoln.ssrgdecoder.dataType.Ssr;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CustomSSRClient ssrClient;
    Thread tcpClient;
    ObsCalculater obslib;

    LocationManager locationManager;
    Location curLoc;

    GnssMeasurementsEvent.Callback onGnssMeasurementsReceived;

    private TextView txtType;
    private TextView txtGs;
    private TextView txtPseudoRange;
    private TextView txtWl;
    private TextView txtDoppler;
    private TextView txtCarrierPhase;

    private int type;
    private double gs;
    private double pseudoRange;
    private double wl;
    private double doppler;
    private double carrierPhase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        initValues();
        initUtils();
        start();
    }

    private void initValues() {
        onGnssMeasurementsReceived = new GnssMeasurementsEvent.Callback() {
            @Override
            public void onGnssMeasurementsReceived(GnssMeasurementsEvent event) {
                GnssClock gnssClock = event.getClock();

                for (GnssMeasurement measurement : event.getMeasurements()) {
                    type = obslib.staType(measurement);
                    gs = obslib.calGS(measurement, gnssClock);
                    pseudoRange = obslib.calPseudoRange(measurement, gnssClock);
                    wl = obslib.calWL(measurement);
                    doppler = obslib.calDoppler(measurement);
                    carrierPhase = obslib.calCarrierPhase(measurement);

                    setTextViews();
                }
            }
        };
    }

    @SuppressLint("MissingPermission")
    private void initUtils() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.registerGnssMeasurementsCallback(onGnssMeasurementsReceived);


        ssrClient = new CustomSSRClient();
        tcpClient = new Thread(ssrClient);

        obslib = new ObsCalculater();
    }

    private void initWidgets() {
        txtType = findViewById(R.id.txt_type);
        txtGs = findViewById(R.id.txt_gs);
        txtPseudoRange = findViewById(R.id.txt_pseudo_range);
        txtWl = findViewById(R.id.txt_wl);
        txtDoppler = findViewById(R.id.txt_doppler);
        txtCarrierPhase = findViewById(R.id.txt_carrier_phase);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    private void setTextViews() {
        txtType.setText(String.valueOf(type));
        txtGs.setText(String.valueOf(gs));
        txtPseudoRange.setText(String.valueOf(pseudoRange));
        txtWl.setText(String.valueOf(wl));
        txtDoppler.setText(String.valueOf(doppler));
        txtCarrierPhase.setText(String.valueOf(carrierPhase));
    }

    @SuppressLint("MissingPermission")
    private void start() {
        curLoc = locationManager.getLastKnownLocation(GPS_PROVIDER);
        tcpClient.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                Toast.makeText(this, "clicked.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}