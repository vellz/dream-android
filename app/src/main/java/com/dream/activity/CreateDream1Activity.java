package com.dream.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.dream.R;

public class CreateDream1Activity extends AppCompatActivity {
    private TextView mDream,mDreamSpak,mDreamKeepTime;
    private Button mNext;
    private Switch mDreamHide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dream_create1);
        initView();
    }
    private void initView(){
        mDream=(EditText)findViewById(R.id.ed_dream);
        mDreamSpak=(EditText)findViewById(R.id.ed_dream_spak);
        mDreamKeepTime=(EditText)findViewById(R.id.ed_dream_time);
        mNext=(Button)findViewById(R.id.bt_next);
        mDreamHide=(Switch)findViewById(R.id.sh_hide);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),CreateDream2Activity.class);
                startActivity(intent);
            }
        });
    }

}
