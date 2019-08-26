package com.hyy.app.permissionhelper;

import android.app.Activity;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description: 6.0一下权限申请帮助类
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 5:11 PM
 */
public class LowPermissionHelper extends PermissionHelper {


    LowPermissionHelper(Activity activity) {
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
        throw new RuntimeException("6.0以下版本不需要申请权限");
    }

    /**
     * 判断申请权限时，是否勾选了，不再提示。
     *
     * @param permissions false 勾选了，
     */
    @Override
    public boolean shouldShowRequestPermissionRationale(String permissions) {
        return false;
    }
}
