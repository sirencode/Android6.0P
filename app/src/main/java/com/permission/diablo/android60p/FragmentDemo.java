package com.permission.diablo.android60p;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Diablo on 16/6/8.
 */
public class FragmentDemo extends Fragment implements View.OnClickListener{

    private static final int REQUEST_PERMISSION_CALENDAR_CODE = 0x101;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 0x102;
    private static final int REQUEST_PERMISSION_CONTACTS_CODE = 0x103;
    private static final int REQUEST_PERMISSION_LOCATION_CODE = 0x104;
    private static final int REQUEST_PERMISSION_MICROPHONE_CODE = 0x105;
    private static final int REQUEST_PERMISSION_PHONE_CODE = 0x106;
    private static final int REQUEST_PERMISSION_SENSORS_CODE = 0x107;
    private static final int REQUEST_PERMISSION_SMS_CODE = 0x108;
    private static final int REQUEST_PERMISSION_STORAGE_CODE = 0x109;
    private static final int REQUEST_PERMISSION_SAW_CODE = 0x110;
    private static final int REQUEST_PERMISSION_WRITE_SETTINGS_CODE = 0x111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.activity_main, container, false);
        initViews(baseView);
        return baseView;
    }

    private void initViews(View base){
            Button calendar = (Button) base.findViewById(R.id.btn_calendar);
            calendar.setOnClickListener(this);
            Button carmera = (Button) base.findViewById(R.id.btn_camera);
            carmera.setOnClickListener(this);
            Button contants = (Button) base.findViewById(R.id.btn_Contacts);
            contants.setOnClickListener(this);
            Button location = (Button) base.findViewById(R.id.btn_Location);
            location.setOnClickListener(this);
            Button microPhone = (Button) base.findViewById(R.id.btn_MicroPhone);
            microPhone.setOnClickListener(this);
            Button phone = (Button) base.findViewById(R.id.btn_phone);
            phone.setOnClickListener(this);
            Button sensord = (Button) base.findViewById(R.id.btn_Sensors);
            sensord.setOnClickListener(this);
            Button sms = (Button) base.findViewById(R.id.btn_sms);
            sms.setOnClickListener(this);
            Button storage = (Button) base.findViewById(R.id.btn_Storage);
            storage.setOnClickListener(this);
            Button saw = (Button) base.findViewById(R.id.btn_saw);
            saw.setOnClickListener(this);
            Button writeSet = (Button) base.findViewById(R.id.btn_writeSettings);
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
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivityForResult(intent, PermissionUtils.REQUEST_PERMISSION_WRITE_SETTINGS_CODE);
    }

    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
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
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.READ_CALENDAR, PermissionUtils.REQUEST_PERMISSION_CALENDAR_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        readCalenda();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取日历权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_CAMERA_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.CAMERA, PermissionUtils.REQUEST_PERMISSION_CAMERA_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        openCamera();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取相机权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.READ_CONTACTS, PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        readContacts();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取通讯录权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_LOCATION_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION, PermissionUtils.REQUEST_PERMISSION_LOCATION_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        location();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取定位权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_MICROPHONE_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.RECORD_AUDIO, PermissionUtils.REQUEST_PERMISSION_MICROPHONE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useMicrophone();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取麦克风权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_PHONE_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.CALL_PHONE, PermissionUtils.REQUEST_PERMISSION_PHONE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        usePhone();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取电话权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_SENSORS_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.BODY_SENSORS, PermissionUtils.REQUEST_PERMISSION_SENSORS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSensors();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取身体传感器权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_SMS_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.SEND_SMS, PermissionUtils.REQUEST_PERMISSION_SMS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSMS();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取短信权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE:
                PermissionUtils.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, PermissionUtils.REQUEST_PERMISSION_STORAGE_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        useSdCard();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(getActivity(), "获取存储权限失败", Toast.LENGTH_SHORT).show();
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
        getActivity().startActivityForResult(intent, 300);
    }

    private void readContacts() {
        ContentResolver cr = getActivity().getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null, null, null);
        int count = cur.getCount();
        cur.close();
        Toast.makeText(getActivity(), String.format("发现%s条", count), Toast.LENGTH_SHORT).show();
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
