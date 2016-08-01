package callrecorder.akshaykale.com.callrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private TextView tv_status;
    private SwitchCompat sw_switchCompat;

    private DataAccessStaticHelper mDataAccessStaticHelper;

    private boolean currentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataAccessStaticHelper = DataAccessStaticHelper.getInstance();

        //  tv_status = (TextView) findViewById(R.id.tv_status);
        sw_switchCompat = (SwitchCompat) findViewById(R.id.sw_switchcompact);

        currentStatus = mDataAccessStaticHelper.isEnable();
        sw_switchCompat.setChecked(currentStatus);

        sw_switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                setDetectEnabled(isChecked);

            }
        });


    }

    private void setDetectEnabled(boolean detectEnabled) {

        Intent intent = new Intent(this, CallService.class);
        if (detectEnabled) {
            // start detect service
            startService(intent);
            sw_switchCompat.setText("Call Recording is Enabled");
        } else {
            // stop detect service
            stopService(intent);
            sw_switchCompat.setText("Call Recording is Disabled");
        }
    }
}
