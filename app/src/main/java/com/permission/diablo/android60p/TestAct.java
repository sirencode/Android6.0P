package com.permission.diablo.android60p;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Diablo on 16/6/8.
 */
public class TestAct extends BaseActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
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


    @Override
    public void onClick(View v) {
        int temp = 0;
        switch (v.getId()) {
            case R.id.btn_calendar:
                temp = REQUEST_PERMISSION_CALENDAR_CODE;
                break;
            case R.id.btn_camera:
                temp = REQUEST_PERMISSION_CAMERA_CODE;
                break;
            case R.id.btn_Contacts:
                temp = REQUEST_PERMISSION_CONTACTS_CODE;
                break;
            case R.id.btn_Location:
                temp = REQUEST_PERMISSION_LOCATION_CODE;
                break;
            case R.id.btn_MicroPhone:
                temp = REQUEST_PERMISSION_MICROPHONE_CODE;
                break;
            case R.id.btn_phone:
                temp = REQUEST_PERMISSION_PHONE_CODE;
                break;
            case R.id.btn_Sensors:
                temp = REQUEST_PERMISSION_SENSORS_CODE;
                break;
            case R.id.btn_sms:
                temp = REQUEST_PERMISSION_SMS_CODE;
                break;
            case R.id.btn_Storage:
                temp = REQUEST_PERMISSION_STORAGE_CODE;
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
        if (temp>=REQUEST_PERMISSION_CALENDAR_CODE && temp<=REQUEST_PERMISSION_STORAGE_CODE) {
            requestRunTimePermissons(temp);
        }
    }
}
