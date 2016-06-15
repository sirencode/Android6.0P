package com.permission.diablo.android60p;

import android.app.Activity;
import android.content.pm.PackageManager;

/**
 * Created by Diablo on 16/6/8.
 */
public class BaseActivity extends Activity {

    private static PermissionUtils.IPermissionGrantListener permissionGrantListener;

    public static void setIPermissionGrantListener(PermissionUtils.IPermissionGrantListener iPermissionGrantListener){
        permissionGrantListener = iPermissionGrantListener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode >= PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE && requestCode <= PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE) {
            int grantResult = grantResults[0];
            boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
            if (granted) {
                permissionGrantListener.onPermissionGranted();
            }else {
                permissionGrantListener.onPermissionGrantedFailed();
            }
        }
    }
}
