package com.permission.diablo.android60p;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Diablo on 16/6/8.
 */
public class FragementAct extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentDemo fragmentDemo = new FragmentDemo();
        transaction.replace(R.id.fragment, fragmentDemo);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
