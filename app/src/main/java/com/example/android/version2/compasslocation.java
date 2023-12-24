package com.example.android.version2;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class compasslocation extends BaseActivity implements SensorEventListener {

    private ImageView compassImage,arrowView;
    private TextView addressText,jiho;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    private float[] lastAccelerometer = new float[3];
    private float[] lastMagnetometer = new float[3];
    private boolean haveSensorReadings = false;
    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];
    private float currentBearing = 0f;
    private float targetBearing = 0f;

    private Geocoder geocoder;
    private double targetLatitude = 0.0;
    private double targetLongitude = 0.0;
    private SOTWFormatter sotwFormatter;
    private float currentAzimuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compasslocation);
        sotwFormatter = new SOTWFormatter(this);
        compassImage = findViewById(R.id.compass_image);
        addressText = findViewById(R.id.address_text);
        arrowView = findViewById(R.id.arrowView);
        jiho = findViewById(R.id.jiho);

        // Get the target address coordinates
        targetLatitude = 21.4225; // Example latitude
        targetLongitude = 39.8262; // Example longitude

        // Initialize the sensor manager and sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Initialize the Geocoder
        geocoder = new Geocoder(this, Locale.getDefault());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void adjustArrow(float azimuth) {
//        Log.d(TAG, "will set rotation from " + currentAzimuth + " to "
//                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.length);
            haveSensorReadings = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.length);
            haveSensorReadings = true;
        }

        if (haveSensorReadings) {
            SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometer, lastMagnetometer);
            SensorManager.getOrientation(rotationMatrix, orientation);

            // Calculate the device's bearing in degrees
            currentBearing = (float) Math.toDegrees(orientation[0]);
            if (currentBearing < 0) {
                currentBearing += 360;
            }

            // Calculate the bearing to the target address
            Location currentLocation = getCurrentLocation();
            if (currentLocation != null) {
                targetBearing = currentLocation.bearingTo(getTargetLocation());
            }

            // Update the compass image with a rotation animation
            RotateAnimation rotateAnimation = new RotateAnimation(-targetBearing, -currentBearing,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);
            compassImage.startAnimation(rotateAnimation);

            // Update the target address text
            String address = getAddressFromLocation(getTargetLocation());
            addressText.setText(address);
            Log.e("eeerereer", String.valueOf(currentBearing));
            jiho.setText(sotwFormatter.format(currentBearing));
            adjustArrow(currentBearing);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    private Location getCurrentLocation() {
        // Retrieve the current location using the Location API
        // Implement your code here to get the current location
        // Return a Location object representing the current location
        return null;
    }

    private Location getTargetLocation() {
        // Create a Location object for the target address coordinates
        Location targetLocation = new Location("Target");
        targetLocation.setLatitude(targetLatitude);
        targetLocation.setLongitude(targetLongitude);
        return targetLocation;
    }

    private String getAddressFromLocation(Location location) {
        // Reverse geocode the location to get the address
        String address = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                Address currentAddress = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i <= currentAddress.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(currentAddress.getAddressLine(i)).append("\n");
                }
                address = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }
}