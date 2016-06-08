package com.permission.diablo.android60p;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by Diablo on 16/6/8.
 */
public class BaseActivity extends Activity {

    public static final int REQUEST_PERMISSION_CALENDAR_CODE = 0x1;
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 0x2;
    public static final int REQUEST_PERMISSION_CONTACTS_CODE = 0x3;
    public static final int REQUEST_PERMISSION_LOCATION_CODE = 0x4;
    public static final int REQUEST_PERMISSION_MICROPHONE_CODE = 0x5;
    public static final int REQUEST_PERMISSION_PHONE_CODE = 0x6;
    public static final int REQUEST_PERMISSION_SENSORS_CODE = 0x7;
    public static final int REQUEST_PERMISSION_SMS_CODE = 0x8;
    public static final int REQUEST_PERMISSION_STORAGE_CODE = 0x9;
    public static final int REQUEST_PERMISSION_SAW_CODE = 0x10;
    public static final int REQUEST_PERMISSION_WRITE_SETTINGS_CODE = 0x11;

    //6.0以下系统自动授权
    @TargetApi(Build.VERSION_CODES.M)
    /**
     * 运行时权限请求
     */
    public void requestRunTimePermissons(int type) {
        switch (type) {
            case REQUEST_PERMISSION_CALENDAR_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CALENDAR}, REQUEST_PERMISSION_CALENDAR_CODE);
                    return;
                } else {
                    System.out.println("日历组权限已经授权:>>>DoNext!");
                    readCalenda();
                }
                break;
            case REQUEST_PERMISSION_CAMERA_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA_CODE);
                    return;
                } else {
                    System.out.println("相机组权限已经授权:>>>DoNext!");
                    openCamera();
                }
                break;
            case REQUEST_PERMISSION_CONTACTS_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_CONTACTS_CODE);
                    return;
                } else {
                    System.out.println("通讯录组权限已经授权:>>>DoNext!");
                    readContacts();
                }
                break;
            case REQUEST_PERMISSION_LOCATION_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION_CODE);
                    return;
                } else {
                    System.out.println("通讯录组权限已经授权:>>>DoNext!");
                    location();
                }
                break;
            case REQUEST_PERMISSION_MICROPHONE_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_MICROPHONE_CODE);
                    return;
                } else {
                    System.out.println("麦克风组权限已经授权:>>>DoNext!");
                    useMicrophone();
                }
                break;
            case REQUEST_PERMISSION_PHONE_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_PHONE_CODE);
                    return;
                } else {
                    System.out.println("电话组权限已经授权:>>>DoNext!");
                    usePhone();
                }
                break;
            case REQUEST_PERMISSION_SENSORS_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.BODY_SENSORS}, REQUEST_PERMISSION_SENSORS_CODE);
                    return;
                } else {
                    System.out.println("人体传感器组权限已经授权:>>>DoNext!");
                    useSensors();
                }
                break;
            case REQUEST_PERMISSION_SMS_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_PERMISSION_SMS_CODE);
                    return;
                } else {
                    System.out.println("短信组权限已经授权:>>>DoNext!");
                    useSMS();
                }
                break;
            case REQUEST_PERMISSION_STORAGE_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE_CODE);
                    return;
                } else {
                    System.out.println("存储组权限已经授权:>>>DoNext!");
                    useSdCard();
                }
                break;
            default:
                System.out.println("未定义运行时权限请求码！");
                break;
        }
    }

    public void requestWriteSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_PERMISSION_WRITE_SETTINGS_CODE);
    }

    public void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_PERMISSION_SAW_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode >= REQUEST_PERMISSION_CALENDAR_CODE && requestCode <= REQUEST_PERMISSION_SMS_CODE) {
            int grantResult = grantResults[0];
            boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
            if (granted) {
                System.out.println("权限授权成功:>>>DoNext!");
                switch (requestCode) {
                    case REQUEST_PERMISSION_CALENDAR_CODE:
                        readCalenda();
                        break;
                    case REQUEST_PERMISSION_CAMERA_CODE:
                        openCamera();
                        break;
                    case REQUEST_PERMISSION_CONTACTS_CODE:
                        readContacts();
                        break;
                    case REQUEST_PERMISSION_LOCATION_CODE:
                        location();
                        break;
                    case REQUEST_PERMISSION_MICROPHONE_CODE:
                        useMicrophone();
                        break;
                    case REQUEST_PERMISSION_PHONE_CODE:
                        usePhone();
                        break;
                    case REQUEST_PERMISSION_SENSORS_CODE:
                        useSensors();
                        break;
                    case REQUEST_PERMISSION_SMS_CODE:
                        useSMS();
                        break;
                    case REQUEST_PERMISSION_STORAGE_CODE:
                        useSdCard();
                        break;
                    default:
                        System.out.println("未定义运行时权限请求码！");
                        break;
                }
            } else {
                System.out.println("权限授权失败!");
            }
        } else {
            System.out.println("运行时权限请求码不对！");
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SAW_CODE) {
            if (Settings.canDrawOverlays(this)) {
                System.out.println("权限SYSTEM_ALERT_WINDOW授权成功:==>DoNext！");
            } else {
                System.out.println("权限SYSTEM_ALERT_WINDOW失败！");
            }
        } else if (requestCode == REQUEST_PERMISSION_WRITE_SETTINGS_CODE) {
            if (Settings.System.canWrite(this)) {
                System.out.println("权限WRITE_SETTINGS授权成功:==>DoNext！");
            } else {
                System.out.println("权限SYSTEM_ALERT_WINDOW失败！");
            }
        }
    }


    private void readCalenda() {

    }

    private void openCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(intent, 300);
    }

    private void readContacts() {
        ContentResolver cr = getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null, null, null);
        int count = cur.getCount();
        cur.close();
        Toast.makeText(this, String.format("发现%s条", count), Toast.LENGTH_SHORT).show();
    }

    private void location() {

    }

    private void useMicrophone() {

    }

    private void usePhone() {

    }

    private void useSensors() {

    }

    private void useSMS() {

    }

    private void useSdCard() {

    }

}
