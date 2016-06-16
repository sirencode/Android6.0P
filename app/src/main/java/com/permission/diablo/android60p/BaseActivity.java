package com.permission.diablo.android60p;

import android.app.Activity;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

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
        if (requestCode >= PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE && requestCode <= PermissionUtils.REQUEST_MULTIPLE_PERMISSIONS_CODE) {
            Map<String, Integer> perms = new HashMap<String, Integer>();
            boolean[] granted = new boolean[permissions.length];
            // Initial
            for (int j=0;j < permissions.length;j++){
                perms.put(permissions[j], PackageManager.PERMISSION_GRANTED);
                perms.put(permissions[j], grantResults[j]);
                granted[j] = grantResults[j]== PackageManager.PERMISSION_GRANTED;
                if (!granted[j]){
                    permissionGrantListener.onPermissionGrantedFailed();
                    return;
                }
            }
            permissionGrantListener.onPermissionGranted();
        }
    }
}
