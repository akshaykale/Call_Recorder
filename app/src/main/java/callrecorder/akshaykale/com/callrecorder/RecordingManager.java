package callrecorder.akshaykale.com.callrecorder;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;
import java.io.IOException;

/**
 * Created by Akshay on 7/30/2016.
 */
public class RecordingManager {

    Context context;

    public RecordingManager(Context context) {
        this.context = context;
    }




    public void startRecording(Context _conContext, Intent _intent) throws IOException {
        /*try {
            String state = android.os.Environment.getExternalStorageState();
            if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
                throw new IOException("SD Card is not mounted.  It is " + state
                        + ".");
            }

            // make sure the directory we plan to store the recording in exists
            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/"+System.currentTimeMillis()+".wav").getParentFile();
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("Path to file could not be created.");
            }

            MediaRecorder myAudioRecorder = new MediaRecorder();
            myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.DEFAULT);
            myAudioRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/test.wav");
            myAudioRecorder.prepare();
            myAudioRecorder.start();

*//*            MediaRecorder _recorder = new MediaRecorder();
            _recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL );
            _recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            _recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            _recorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/test.wav");
            _recorder.prepare();
            _recorder.start();*//*

        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

}
