package andfixo.preqel.com.andfixo.application;

import android.app.Application;

import com.alipay.euler.andfix.AndFixManager;

import andfixo.preqel.com.andfixo.AndFixPatchManager;

/**
 * Created by preqel on 2017/10/4.
 */

public class MyApplication extends Application {
@Override
public void onCreate(){
    super.onCreate();
    iniAndFix();
}

    private void iniAndFix() {
           AndFixPatchManager.getinstance().init(this.getApplicationContext());
    }
}
