package com.hyy.app.permissionhelper;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description:
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 5:17 PM
 */
public class HeightPermissionHelper extends PermissionHelper {

    HeightPermissionHelper(Activity activity) {
        super(activity);
    }

    /**
     * 申请权限
     *
     * @param requestCode
     * @param permissions
     */
    @Override
    public void requestPermission(int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(getHost(), permissions, requestCode);
    }

    /**
     * 判断申请权限时，是否勾选了，不再提示。
     *
     * @param permissions
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean shouldShowRequestPermissionRationale(String permissions) {
        return getHost().shouldShowRequestPermissionRationale(permissions);
    }
}
