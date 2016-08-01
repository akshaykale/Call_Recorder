package callrecorder.akshaykale.com.callrecorder;

import android.content.SharedPreferences;

/**
 * Created by Akshay on 7/30/2016.
 *
 * SINGLETON class
 *
 * Use for store data and access from a shared preferences files of the app
 *
 */
public class DataAccessStaticHelper {



    public static DataAccessStaticHelper sDataAccessStaticHelperInstance;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private DataAccessStaticHelper(){

        sharedPreferences = CallRecorderApplication.getAppContext().
                getSharedPreferences(Constants.SHAREDPREF_ALL_INFO_FILE, CallRecorderApplication.getAppContext().MODE_PRIVATE);

        editor = sharedPreferences.edit();
    }

    public static DataAccessStaticHelper getInstance(){

        if (sDataAccessStaticHelperInstance == null){
            sDataAccessStaticHelperInstance = new DataAccessStaticHelper();
        }
        return sDataAccessStaticHelperInstance;
    }

    public void setEnabled(boolean x){
        editor.putBoolean("recording_status", x);
        editor.apply();
    }
    public boolean isEnable(){
        return sharedPreferences.getBoolean("recording_status", false);
    }




    /**
     * Clear data file
     * */
    public void clearUserFile(){
        editor.clear();
        editor.apply();
    }




}
