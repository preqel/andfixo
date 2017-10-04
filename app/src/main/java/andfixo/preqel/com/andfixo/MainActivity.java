package andfixo.preqel.com.andfixo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

private static final String FILED_END = ".apatch";
    private String mPathDir;   ///外部储存路径
    private Button btn1,btn2;

    PatchManager patchManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = MainActivity.this;
        mPathDir =getExternalCacheDir().getAbsolutePath()+ "/apatch/";
        File file = new File(mPathDir);
         if(file == null || file.exists()){
                file.mkdir();

         }
         btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  createBug(v);
            }
        });
        btn2 .setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         fixBug(v);
                                     }
                                 }
        );
    }

    public void createBug(View view){
        Utils.printLog();
    }
    public void fixBug(View view ){
        AndFixPatchManager.getinstance().addPatch(getPathName());
    }
   private  String getPathName(){
        return this.mPathDir.concat("preqel").concat(FILED_END);
    }

}
