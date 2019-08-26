package com.hyy.app.permissionhelper;

import android.app.Activity;
import android.os.Build;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description:
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 5:10 PM
 */
public abstract class PermissionHelper {
    private Activity activity;


    PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public static PermissionHelper newInstance(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new LowPermissionHelper(activity);
        }
        return new HeightPermissionHelper(activity);
    }

    Activity getHost() {
        return activity;
    }

    /**
     * 申请权限
     *
     * @param
     * @param requestCode
     * @param permissions
     */
    public abstract void requestPermission(int requestCode, String... permissions);

    /**
     * 判断申请权限时，是否勾选了，不再提示。
     *
     * @param
     * @param
     * @param permissions
     */
    public abstract boolean shouldShowRequestPermissionRationale(String permissions);

    /**
     * 全选组
     * <p>
     * 全部没勾选，不再提示--->true
     * 勾选了某一个，不再提示-->false
     *
     * @param permissions
     * @return
     */
    public boolean allShouldShowRequestPermissionRationale(String... permissions) {
        for (String permission : permissions) {
            if (!shouldShowRequestPermissionRationale(permission)) {
                return false;
            }
        }
        return true;
    }
}
