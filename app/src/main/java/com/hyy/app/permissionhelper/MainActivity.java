package com.hyy.app.permissionhelper;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    Button bt_get;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_get = findViewById(R.id.bt_get);

        final String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionManager.requestPermission(MainActivity.this, CAMERA_CODE, permissions);
            }
        });


    }

    /**
     * 授权失败了
     *
     * @param permissions
     * @param grantResults
     */
    @Override
    public void deniedPermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        String str = "";
        for (String permission : permissions) {
            str = str + "," + permission;
        }

        Log.i(TAG, "禁止了：" + str);

        if (CAMERA_CODE == requestCode) {
            if (PermissionManager.allShouldShowRequestPermissionRationale(this, permissions)) {
                //    Log.i(TAG, "全部没有勾选不再提示，可以继续申请权限弹框");
                PermissionManager.requestPermission(MainActivity.this, CAMERA_CODE, permissions);

            } else {
                Log.i(TAG, "勾选了，不再提示。继续弹框");
            }
        }

    }

}
