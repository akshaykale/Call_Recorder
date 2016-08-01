package callrecorder.akshaykale.com.callrecorder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Akshay on 7/30/2016.
 */
public class CallService extends Service {
    private CallManager callHelper;

    public CallService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        callHelper = new CallManager(this);

        int res = super.onStartCommand(intent, flags, startId);
        callHelper.start();
        return res;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callHelper.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
