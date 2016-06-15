package com.permission.diablo.android60p;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Diablo on 16/6/15.
 */
public class PermissionUtils {

    public static final int REQUEST_PERMISSION_CALENDAR_CODE = 0x101;
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 0x102;
    public static final int REQUEST_PERMISSION_CONTACTS_CODE = 0x103;
    public static final int REQUEST_PERMISSION_LOCATION_CODE = 0x104;
    public static final int REQUEST_PERMISSION_MICROPHONE_CODE = 0x105;
    public static final int REQUEST_PERMISSION_PHONE_CODE = 0x106;
    public static final int REQUEST_PERMISSION_SENSORS_CODE = 0x107;
    public static final int REQUEST_PERMISSION_SMS_CODE = 0x108;
    public static final int REQUEST_PERMISSION_STORAGE_CODE = 0x109;
    public static final int REQUEST_PERMISSION_SAW_CODE = 0x110;
    public static final int REQUEST_PERMISSION_WRITE_SETTINGS_CODE = 0x111;

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
}
