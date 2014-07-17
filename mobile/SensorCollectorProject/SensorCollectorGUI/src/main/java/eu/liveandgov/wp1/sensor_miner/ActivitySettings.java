package eu.liveandgov.wp1.sensor_miner;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import eu.liveandgov.wp1.sensor_collector.configuration.SensorCollectionOptions;

import static junit.framework.Assert.assertNotNull;

public class ActivitySettings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get target textview for streaming address settings
        final TextView uploadAddress = (TextView) findViewById(R.id.upload_address);
        final TextView streamingAddress = (TextView) findViewById(R.id.streaming_address);

        // Open settings
        SharedPreferences settings = getSharedPreferences(getString(R.string.spn), 0);

        // Get configured value
        final String uploadAddressValue = settings.getString(getString(R.string.prf_upload_address), SensorCollectionOptions.DEFAULT_UPLOAD);
        final String streamingAddressValue = settings.getString(getString(R.string.prf_streaming_address), SensorCollectionOptions.DEFAULT_STREAMING);

        // Assign into view
        uploadAddress.setText(uploadAddressValue);
        streamingAddress.setText(streamingAddressValue);
    }

    @Override
    protected void onPause() {

        // Open settings for editing
        SharedPreferences settings = getSharedPreferences(getString(R.string.spn), 0);
        SharedPreferences.Editor edit = settings.edit();

        // Get source textview for streaming address settings
        final TextView uploadAddress = (TextView) findViewById(R.id.upload_address);
        final TextView streamingAddress = (TextView) findViewById(R.id.streaming_address);

        // Calculate new configuration value
        final String uploadAddressValue = uploadAddress.getText().length() == 0 ? null : uploadAddress.getText().toString();
        final String streamingAddressValue = streamingAddress.getText().length() == 0 ? null : streamingAddress.getText().toString();

        // Store in settings and commit
        edit.putString(getString(R.string.prf_upload_address), uploadAddressValue);
        edit.putString(getString(R.string.prf_streaming_address), streamingAddressValue);
        edit.apply();

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}