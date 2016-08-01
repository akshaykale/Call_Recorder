package callrecorder.akshaykale.com.callrecorder;

import android.app.Application;
import android.content.Context;

/**
 * Created by Akshay on 7/30/2016.
 */

public class CallRecorderApplication extends Application {

    static CallRecorderApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static CallRecorderApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
