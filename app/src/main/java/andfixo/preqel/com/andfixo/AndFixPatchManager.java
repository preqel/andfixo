package andfixo.preqel.com.andfixo;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by akang on 2017/10/4.
 * @function 初始化andfx操作和打印异常
 */

public class AndFixPatchManager {


    private static AndFixPatchManager mInstance = null;

    private PatchManager patchmanager = null;



    private AndFixPatchManager(){

    }
    public static AndFixPatchManager getinstance(){

   if(mInstance == null){
//       synchronized (mInstance){     这里注意一下，是对整个类进行加锁

           synchronized (AndFixPatchManager.class){
               if(mInstance== null){
                   mInstance  = new AndFixPatchManager();
               }
       }
   }
   return mInstance;
    }

    //初始化andfix方法
    public void init(Context context){
        this.patchmanager = new PatchManager(context);
        patchmanager.init(Utils.getVersionCode(context));
        patchmanager.loadPatch();
    }

    //加载我们的path文件
    public void addPatch(String path) {
        try {
            if (patchmanager != null) {
                patchmanager.addPatch(path);
            }


        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

}
