package com.hyy.app.permissionhelper;

import android.support.annotation.NonNull;

/**
 * @ClassName: com.hyy.app.permissionhelper
 * @Description:
 * @Author: hyy
 * @Date: 2019/8/24
 * @Time: 5:26 PM
 */
public interface PermissionCallBack {

    /**
     * 申请通过的权限
     *
     * @param permissions
     * @param
     */
    void grantedPermission(int requestCode, @NonNull String[] permissions);


    /**
     * 申请被拒绝的权限
     *
     * @param permissions
     * @param grantResults
     */
    void deniedPermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);


}
