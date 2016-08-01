package callrecorder.akshaykale.com.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Akshay on 7/30/2016.
 */
public class CallManager {


    /**
     * Listener to detect incoming calls.
     */
    private class CallStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone

                    Toast.makeText(context,
                            "Incoming: "+incomingNumber,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    /**
     * Broadcast receiver to detect the outgoing calls.
     */
    /*public class OutgoingReceiver extends BroadcastReceiver {
        public OutgoingReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Toast.makeText(context,
                    "Outgoing: "+number,
                    Toast.LENGTH_LONG).show();

            RecordingManager recordingManager = new RecordingManager(context);
            try {
                recordingManager.startRecording(context,intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/
    public class OutgoingReceiver extends BroadcastReceiver {
        Bundle bundle;
        String state;
        String inCall, outCall;
        public boolean wasRinging = false;
        private static final String ACTION_IN = "android.intent.action.PHONE_STATE";
        private static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";

        Boolean isOffHook = false;
        private boolean recordstarted = false;

        MediaRecorder recorder;

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_IN)) {
                if ((bundle = intent.getExtras()) != null) {
                    state = bundle.getString(TelephonyManager.EXTRA_STATE);

                    if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        inCall = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        wasRinging = true;
                        Toast.makeText(context, "IN : " + inCall, Toast.LENGTH_LONG).show();
                    } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                        if (wasRinging == true) {

                            Toast.makeText(context, "ANSWERED", Toast.LENGTH_LONG).show();

                            String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                            File sampleDir = new File(Environment.getExternalStorageDirectory(), "/TestRecordingDasa1");
                            if (!sampleDir.exists()) {
                                sampleDir.mkdirs();
                            }
                            String file_name = "Record";
                            File audiofile = null;
                            try {
                                audiofile = File.createTempFile(file_name, ".amr", sampleDir);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String path = Environment.getExternalStorageDirectory().getAbsolutePath();

                            recorder = new MediaRecorder();
//                          recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);

                            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
                            recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            recorder.setOutputFile(audiofile.getAbsolutePath());
                            try {
                                recorder.prepare();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            recorder.start();
                            recordstarted = true;
                        }
                    } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        wasRinging = false;
                        Toast.makeText(context, "REJECT || DISCO", Toast.LENGTH_LONG).show();
                        if (recordstarted) {
                            recorder.stop();
                            recordstarted = false;
                        }
                    }
                }
            } else if (intent.getAction().equals(ACTION_OUT)) {
                if ((bundle = intent.getExtras()) != null) {
                    outCall = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                    Toast.makeText(context, "OUT : " + outCall, Toast.LENGTH_LONG).show();
                }
            }
        }
    }





    private Context context;
    private TelephonyManager mTelephonyManager;
    private CallStateListener mCallStateListener;

    private OutgoingReceiver mOutgoingReceiver;

    public CallManager(Context ctx) {
        this.context = ctx;

        mCallStateListener = new CallStateListener();
        mOutgoingReceiver = new OutgoingReceiver();
    }
    /**
     * Start calls detection.
     */
    public void start() {
        mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mCallStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        context.registerReceiver(mOutgoingReceiver, intentFilter);
    }
    /**
     * Stop calls detection.
     */
    public void stop() {
        mTelephonyManager.listen(mCallStateListener, PhoneStateListener.LISTEN_NONE);
        context.unregisterReceiver(mOutgoingReceiver);
    }












}
