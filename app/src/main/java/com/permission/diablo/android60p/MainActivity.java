package com.permission.diablo.android60p;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    boolean phone_grated = false;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ininViews();
    }

    private void ininViews() {
        Button calendar = (Button) findViewById(R.id.btn_calendar);
        calendar.setOnClickListener(this);
        Button carmera = (Button) findViewById(R.id.btn_camera);
        carmera.setOnClickListener(this);
        Button contants = (Button) findViewById(R.id.btn_Contacts);
        contants.setOnClickListener(this);
        Button location = (Button) findViewById(R.id.btn_Location);
        location.setOnClickListener(this);
        Button microPhone = (Button) findViewById(R.id.btn_MicroPhone);
        microPhone.setOnClickListener(this);
        Button phone = (Button) findViewById(R.id.btn_phone);
        phone.setOnClickListener(this);
        Button sensord = (Button) findViewById(R.id.btn_Sensors);
        sensord.setOnClickListener(this);
        Button sms = (Button) findViewById(R.id.btn_sms);
        sms.setOnClickListener(this);
        Button storage = (Button) findViewById(R.id.btn_Storage);
        storage.setOnClickListener(this);
        Button saw = (Button) findViewById(R.id.btn_saw);
        saw.setOnClickListener(this);
        Button writeSet = (Button) findViewById(R.id.btn_writeSettings);
        writeSet.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        int temp = 0;
        switch (v.getId()) {
            case R.id.btn_calendar:
                temp = PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE;
                break;
            case R.id.btn_camera:
                temp = PermissionUtils.REQUEST_PERMISSION_CAMERA_CODE;
                break;
            case R.id.btn_Contacts:
                temp = PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE;
                break;
            case R.id.btn_Location:
                temp = PermissionUtils.REQUEST_PERMISSION_LOCATION_CODE;
                break;
            case R.id.btn_MicroPhone:
                temp = PermissionUtils.REQUEST_PERMISSION_MICROPHONE_CODE;
                break;
            case R.id.btn_phone:
                temp = PermissionUtils.REQUEST_PERMISSION_PHONE_CODE;
                break;
            case R.id.btn_Sensors:
                temp = PermissionUtils.REQUEST_PERMISSION_SENSORS_CODE;
                break;
            case R.id.btn_sms:
                temp = PermissionUtils.REQUEST_PERMISSION_SMS_CODE;
                break;
            case R.id.btn_Storage:
                temp = PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE;
                break;
            case R.id.btn_saw:
                requestAlertWindowPermission();
                break;
            case R.id.btn_writeSettings:
                requestWriteSettings();
                break;
            default:
                break;
        }
        if (temp >= PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE && temp <= PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE) {
            requestRunTimePermissons(temp);
        }
    }

    private void requestWriteSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, PermissionUtils.REQUEST_PERMISSION_WRITE_SETTINGS_CODE);
    }

    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, PermissionUtils.REQUEST_PERMISSION_SAW_CODE);
    }

    //6.0以下系统自动授权
    @TargetApi(Build.VERSION_CODES.M)
    /**
     * 运行时权限请求
     */
    private void requestRunTimePermissons(int type) {
        switch (type) {
            case PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.READ_CALENDAR, PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        readCalenda();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取日历权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_CAMERA_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.CAMERA, PermissionUtils.REQUEST_PERMISSION_CAMERA_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        openCamera();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取相机权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.READ_CONTACTS, PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        phone_grated = true;
                        readContacts();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取通讯录权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_LOCATION_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION, PermissionUtils.REQUEST_PERMISSION_LOCATION_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        location();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取定位权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_MICROPHONE_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.RECORD_AUDIO, PermissionUtils.REQUEST_PERMISSION_MICROPHONE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useMicrophone();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取麦克风权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_PHONE_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.CALL_PHONE, PermissionUtils.REQUEST_PERMISSION_PHONE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        usePhone();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取电话权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_SENSORS_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.BODY_SENSORS, PermissionUtils.REQUEST_PERMISSION_SENSORS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSensors();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取身体传感器权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_SMS_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.SEND_SMS, PermissionUtils.REQUEST_PERMISSION_SMS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSMS();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取短信权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE:
                PermissionUtils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSdCard();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(MainActivity.this, "获取存储权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                System.out.println("未定义运行时权限请求码！");
                break;
        }
    }


    private void readCalenda() {
    }

    private void openCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        MainActivity.this.startActivityForResult(intent, 300);
    }

    private void readContacts() {
        ContentResolver cr = getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null, null, null);
        int count = cur.getCount();
        cur.close();
        Toast.makeText(MainActivity.this, String.format("发现%s条", count), Toast.LENGTH_SHORT).show();
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

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.REQUEST_PERMISSION_SAW_CODE) {
            if (Settings.canDrawOverlays(this)) {
                System.out.println("权限SYSTEM_ALERT_WINDOW授权成功:==>DoNext！");
            } else {
                System.out.println("权限SYSTEM_ALERT_WINDOW失败！");
            }
        } else if (requestCode == PermissionUtils.REQUEST_PERMISSION_WRITE_SETTINGS_CODE) {
            if (Settings.System.canWrite(this)) {
                System.out.println("权限WRITE_SETTINGS授权成功:==>DoNext！");
            } else {
                System.out.println("权限SYSTEM_ALERT_WINDOW失败！");
            }
        }
    }

}
