package com.hyy.app.permissionhelper;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description:
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 4:04 PM
 */
public abstract class BaseActivity extends AppCompatActivity implements PermissionCallBack {
    public static final String TAG = "TAGTAGTAG";

    public int CAMERA_CODE = 1001;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults);

    }


    /**
     * 授权成功的，权限
     *
     * @param permissions
     * @param
     */
    @Override
    public void grantedPermission(int requestCode, @NonNull String[] permissions) {

        String str = "";
        for (String permission : permissions) {
            str = str + "," + permission;
        }
        Log.i(TAG, "授权成功" + str);
    }

}
