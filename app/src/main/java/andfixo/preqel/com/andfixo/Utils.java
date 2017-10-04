package andfixo.preqel.com.andfixo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by akang on 2017/10/4.
 * @function  utils class
 */

class Utils {



    public static final String getVersionCode(Context context)   {
        String versioname = "1.0.0";
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versioname = pi.versionName;
        return versioname ;
    }
    //模拟产生bug
    public static void printLog() {

        String error = null;
        Log.d("preqel",error);

    }
}
