package com.hyy.app.permissionhelper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description:
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 4:46 PM
 */
public class PermissionManager {


    /**
     * 权限，是否已经全部授权通过
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {

        if (context == null) {
            throw new RuntimeException("context不能为空");
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }


        return true;
    }


    /**
     * 请求权限
     * <p>
     * 申请权限回调，activity 继承 PermissionCallBack。
     * 以及onRequestPermissionsResult方法调用 PermissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
     *
     * @param activity
     * @param requestCode
     * @param permissions
     */
    public static void requestPermission(Activity activity, int requestCode, String... permissions) {


        //是否，所有权限都已经申请通过
        if (hasPermissions(activity, permissions)) {

            if (activity instanceof PermissionCallBack) {
                ((PermissionCallBack) activity).grantedPermission(requestCode, permissions);
            }

            return;
        }


        PermissionHelper.newInstance(activity).requestPermission(requestCode, permissions);


    }


    /**
     * 申请权限回调
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        /**
         * 申请通过的权限组
         */
        List<String> granteds = new ArrayList<>();

        /**
         * 申请被拒绝的权限组
         */
        List<String> denied = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granteds.add(permission);
            } else {
                denied.add(permission);
            }
        }


        if (!granteds.isEmpty()) {
            if (activity instanceof PermissionCallBack) {
                ((PermissionCallBack) activity).grantedPermission(requestCode, granteds.toArray(new String[granteds.size()]));
            }
        }


        if (!denied.isEmpty()) {

            if (activity instanceof PermissionCallBack) {
                ((PermissionCallBack) activity).deniedPermission(requestCode, denied.toArray(new String[denied.size()]), grantResults);
            }
        }

    }


    /**
     * 是否勾选了，不再提示。
     * <p>
     * 权限组，其中一个勾选了不再提示.-->false
     * <p>
     * 权限组，全部没有勾选不再提示----->true
     *
     * @param activity
     * @param
     * @param permissions
     * @return
     */
    public static boolean allShouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        return PermissionHelper.newInstance(activity).allShouldShowRequestPermissionRationale(permissions);
    }

}
