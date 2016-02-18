package com.wgz.ant.antinstall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.wgz.ant.antinstall.adapter.MyFragmentPagerAdapter;
import com.wgz.ant.antinstall.fragment.MapFragment;
import com.wgz.ant.antinstall.fragment.MapFragment2;
import com.wgz.ant.antinstall.fragment.MsgFragment;
import com.wgz.ant.antinstall.fragment.OrderFragment;
import com.wgz.ant.antinstall.fragment.PersonFragment;
import com.wgz.ant.antinstall.view.CustomViewPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private LinearLayout bar1,bar2,bar3,bar4;
    private TextView bartv1,bartv2,bartv3,bartv4;
    private ImageView barimg1,barimg2,barimg3,barimg4;
    private ArrayList<Fragment> fragments;//页面列表
    private CustomViewPager mainviewpager;
    private MsgFragment msgFragment;
    private MapFragment mapFragment;
    private PersonFragment personFragment;
    private OrderFragment orderFragment;
    private MapFragment2 mapFragment2;
    private MyFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_antinsatll);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
        initview();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");
        if(result.equals("该刷新了")){
            Log.i("xmll",",msgFragment--id=="+adapter.getItem(0).getId());
            Log.i("xmll",",orderFragment--id=="+adapter.getItem(1).getId());
            Fragment f = getSupportFragmentManager().findFragmentById(adapter.getItem(1).getId());
            f.onActivityResult(requestCode, resultCode, data);
            //initData();
        }
        if(result.equals("导航")){
           //String address = data.getExtras().getString("address");
            initview();
            bar1.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
            bar2.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
            bar3.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
            bar4.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
            mainviewpager.setCurrentItem(2);
            Log.i("xmll",",MapFragment--id=="+fragments.get(0).getId());
            Log.i("xmll",",MapFragment--id=="+fragments.get(1).getId());
            Log.i("xmll",",MapFragment--id=="+fragments.get(2).getId());
            Log.i("xmll",",MapFragment--id=="+fragments.get(3).getId());
            bar3.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
            Fragment f = getSupportFragmentManager().findFragmentById(fragments.get(2).getId());
            f.onActivityResult(requestCode, resultCode, data);
        }

    }
    public void initview() {
        msgFragment = new MsgFragment();
        mapFragment = new MapFragment();
        orderFragment = new OrderFragment();
        personFragment = new PersonFragment();
        mapFragment2 = new MapFragment2();

        mainviewpager = (CustomViewPager) findViewById(R.id.viewpager);
        bar1= (LinearLayout) findViewById(R.id.bar1);
        bar2= (LinearLayout) findViewById(R.id.bar2);
        bar3= (LinearLayout) findViewById(R.id.bar3);
        bar4= (LinearLayout) findViewById(R.id.bar4);
        barimg1 = (ImageView) findViewById(R.id.mainbar_img1);
        barimg2 = (ImageView) findViewById(R.id.mainbar_img2);
        barimg3 = (ImageView) findViewById(R.id.mainbar_img3);
        barimg4 = (ImageView) findViewById(R.id.mainbar_img4);
        bartv1 = (TextView) findViewById(R.id.mainbar1);
        bartv2 = (TextView) findViewById(R.id.mainbar2);
        bartv3 = (TextView) findViewById(R.id.mainbar3);
        bartv4 = (TextView) findViewById(R.id.mainbar4);
        fragments = new ArrayList<Fragment>();
        fragments.add(msgFragment);
        fragments.add(orderFragment);
        fragments.add(mapFragment2);
        fragments.add(personFragment);
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);

        mainviewpager.setAdapter(adapter);
        mainviewpager.setCurrentItem(0);
        barimg1.setImageResource(R.drawable.bar11);
        bartv1.setTextColor(Color.WHITE);
        bar1.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public  void BaronClick(View view){

        bar1.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
        bar2.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
        bar3.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
        bar4.setBackgroundColor(android.graphics.Color.parseColor("#000000"));

        int id = view .getId();
        switch (id){
            case  R.id.bar1:
                mainviewpager.setCurrentItem(0);
                bar1.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
                break;
            case R.id.bar2:
                mainviewpager.setCurrentItem(1);
                bar2.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
                break;
            case R.id.bar3:
                mainviewpager.setCurrentItem(2);
                bar3.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
                break;
            case R.id.bar4:
                mainviewpager.setCurrentItem(3);
                bar4.setBackgroundColor(android.graphics.Color.parseColor("#00A1E9"));
                break;
            default:
                break;

        }

    }
}
