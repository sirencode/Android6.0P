package com.permission.diablo.android60p;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MyAdapter extends CommonAdapter<String> {

    private Activity activity;

    public MyAdapter(Activity context, List<String> datas, int id) {
        super(context, datas, id);
        this.activity = context;
    }

    public void convert(View convertView, ViewGroup parent,
                        final String persion) {
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_sounItemName);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_soundItemRankNum);
        tv_name.setText(persion);
        tv_num.setText("1000");
        tv_name.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PermissionUtils.checkPermission(activity, Manifest.permission.READ_CONTACTS, PermissionUtils.REQUEST_PERMISSION_CONTACTS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        readContacts();
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(activity, "获取权限失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void readContacts() {
        ContentResolver cr = activity.getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null, null, null);
        int count = cur.getCount();
        cur.close();
        Toast.makeText(activity, String.format("发现%s条", count), Toast.LENGTH_SHORT).show();
    }
}
