package com.cyq.showbottom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {
    private  HeadFloatListView mBottomFloatListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.e("--->", getSDPath());
        mBottomFloatListView = (HeadFloatListView)findViewById(R.id.listView)  ;
        mBottomFloatListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        ViewGroup bottomView = (ViewGroup)findViewById(R.id.bottombar) ;
        mBottomFloatListView.setHeadBar(bottomView);
    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();

        for(int i = 0; i <100; i++)      {
            data.add("测试数据" + i);
        }

        return data;

    }
    public String getSDPath(){ 
        File sdDir = null; 
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在 
        if   (sdCardExist)   
        {                               
          sdDir = Environment.getExternalStorageDirectory();//获取跟目录 
       }   
        return sdDir.toString(); 
        
 }
}
