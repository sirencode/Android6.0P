package com.permission.diablo.android60p;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Diablo on 16/6/12.
 */
public class GroupPer extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        ininViews();
    }

    private void ininViews() {
        Button group = (Button) findViewById(R.id.btn_Group);
        group.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Group:
                String[] permissions = new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_CONTACTS,Manifest.permission.CAMERA};
                PermissionUtils.requestMultiplePermissions(GroupPer.this, permissions, PermissionUtils.REQUEST_MULTIPLE_PERMISSIONS_CODE, new PermissionUtils.IPermissionGrantListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(GroupPer.this, "授权成功！", Toast.LENGTH_SHORT)
                                .show();;
                    }

                    @Override
                    public void onPermissionGrantedFailed() {
                        Toast.makeText(GroupPer.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
                break;
            default:
                break;
        }

    }

}
