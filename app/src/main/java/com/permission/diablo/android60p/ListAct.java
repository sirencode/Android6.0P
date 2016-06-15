package com.permission.diablo.android60p;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diablo on 16/6/15.
 */
public class ListAct extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initViews();
    }

    private void initViews(){
        ListView listView = (ListView) findViewById(R.id.listView);
        MyAdapter myAdapter = new MyAdapter(this,getDatas(),R.layout.soundrank_item);
        listView.setAdapter(myAdapter);
    }

    private List<String> getDatas(){
        List<String> datas = new ArrayList<String>();
        for (int i=0;i<10;i++){
            datas.add("syh"+i);
        }
        return datas;
    }
}
