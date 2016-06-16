package com.permission.diablo.android60p;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diablo on 16/6/15.
 */
public class PermissionUtils {

    public static final int REQUEST_PERMISSION_CALENDAR_CODE = 101;
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 102;
    public static final int REQUEST_PERMISSION_CONTACTS_CODE = 103;
    public static final int REQUEST_PERMISSION_LOCATION_CODE = 104;
    public static final int REQUEST_PERMISSION_MICROPHONE_CODE = 105;
    public static final int REQUEST_PERMISSION_PHONE_CODE = 106;
    public static final int REQUEST_PERMISSION_SENSORS_CODE = 107;
    public static final int REQUEST_PERMISSION_SMS_CODE = 108;
    public static final int REQUEST_PERMISSION_STORAGE_CODE = 109;
    public static final int REQUEST_PERMISSION_SAW_CODE = 110;
    public static final int REQUEST_PERMISSION_WRITE_SETTINGS_CODE = 111;
    public static final int REQUEST_MULTIPLE_PERMISSIONS_CODE = 112;

    @TargetApi(Build.VERSION_CODES.M)
    public static void checkPermission(Activity activity, String permission, int code,IPermissionGrantListener permissionGrantListener){
        BaseActivity.setIPermissionGrantListener(permissionGrantListener);
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{permission}, code);
            return;
        } else {
            permissionGrantListener.onPermissionGranted();
        }
    }

    public static interface IPermissionGrantListener{
        void onPermissionGranted();
        void onPermissionGrantedFailed();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestMultiplePermissions(Activity activity, String[] permissions, int code, IPermissionGrantListener permissionGrantListener) {
        BaseActivity.setIPermissionGrantListener(permissionGrantListener);
        final List<String> permissionsList = new ArrayList<String>();
        for (int i=0;i<permissions.length;i++){
            if (!addPermission(activity,permissionsList, permissions[i]));
        }
        if (permissionsList.size() > 0) {
            activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_MULTIPLE_PERMISSIONS_CODE);
            return;
        }
        System.out.println("授权成功！");
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static boolean addPermission(Activity activity,List<String> permissionsList, String permission) {
        if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!activity.shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }
}
