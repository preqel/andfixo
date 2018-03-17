package andfixo.preqel.com.andfixo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by preqel on 2017/10/5.
 * @funcions 1.检查patch文件下载并且加载
 */

public class AndFixService extends Service {
   private static final String TAG = AndFixService.class.getName();
    private String mPatchFileDir;
    private String mPatchFile;
    private static final int UPDATE_PATCH = 0x02;
    private static final int DOWNLOAD_PATCH = 0x01;
    private static final String FILE_END = ".apatch";

    private Handler mhandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch(msg.what){
                case UPDATE_PATCH:
                    checkPatchUpdate();

                    break;
                case DOWNLOAD_PATCH:

                    break;
            }



            super.handleMessage(msg);
        }
    };



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //检查服务器有否有patch文件
    private void checkPatchUpdate() {
        //RequestCenter.request
        String url = "http://192.168.23.1:7111/web02/base";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String  error = e.getMessage();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.isSuccessful()){

                    String json = response.body().toString();

                    InputStream is =      response.body().byteStream();

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int readed = 0;

                    byte buf[] = new byte[1024];

                    while ((readed = is.read(buf, 0, buf.length)) != -1) {

                        bos.write(buf, 0, readed);

                    }

                    byte[] result = bos.toByteArray();
                    Log.d("result","result:"+ new String(result));
                    is.close();
                    bos.close();
                    buf=null;

                }

            }
        });


    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mhandler.sendEmptyMessage(UPDATE_PATCH);
        return   START_NOT_STICKY;
    }

    public void init(){
     mPatchFileDir = getExternalCacheDir().getAbsolutePath()+"/apatch/";
        File patchDir = new File(mPatchFileDir);
        try{
       if(patchDir == null || !patchDir.exists()){
           patchDir.mkdir();

       }
        }catch(Exception e ){
            e.printStackTrace();
            stopSelf();
        }
    }



}
