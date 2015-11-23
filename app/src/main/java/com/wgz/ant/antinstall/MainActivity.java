package com.wgz.ant.antinstall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.wgz.ant.antinstall.adapter.MyFragmentPagerAdapter;
import com.wgz.ant.antinstall.fragment.MapFragment;
import com.wgz.ant.antinstall.fragment.MapFragment2;
import com.wgz.ant.antinstall.fragment.MsgFragment;
import com.wgz.ant.antinstall.fragment.OrderFragment;
import com.wgz.ant.antinstall.fragment.PersonFragment;
import com.wgz.ant.antinstall.fragment.RecyclerFragment;
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
    private RecyclerFragment recyclerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_antinsatll);
        initview();

    }

    public void initview() {
         msgFragment = new MsgFragment();
        mapFragment = new MapFragment();
        orderFragment = new OrderFragment();
        personFragment = new PersonFragment();
        mapFragment2 = new MapFragment2();
        recyclerFragment = new RecyclerFragment();
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
        //fragments.add(mapFragment);
        fragments.add(mapFragment2);
        fragments.add(personFragment);
        //fragments.add(recyclerFragment);
        mainviewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mainviewpager.setCurrentItem(0);
        barimg1.setImageResource(R.drawable.bar11);
        bartv1.setTextColor(Color.WHITE);
        bar1.setBackgroundColor(android.graphics.Color.parseColor("#0080ff"));
    }


    public  void BaronClick(View view){
        barimg1.setImageResource(R.drawable.bar1);
        bartv1.setTextColor(android.graphics.Color.parseColor("#0080ff"));
        bar1.setBackgroundColor(Color.WHITE);
        barimg2.setImageResource(R.drawable.bar2);
        bartv2.setTextColor(android.graphics.Color.parseColor("#0080ff"));
        bar2.setBackgroundColor(Color.WHITE);
        barimg3.setImageResource(R.drawable.bar3);
        bartv3.setTextColor(android.graphics.Color.parseColor("#0080ff"));
        bar3.setBackgroundColor(Color.WHITE);
        barimg4.setImageResource(R.drawable.bar4);
        bartv4.setTextColor(android.graphics.Color.parseColor("#0080ff"));
        bar4.setBackgroundColor(Color.WHITE);

        int id = view .getId();
        switch (id){
            case  R.id.bar1:
                mainviewpager.setCurrentItem(0);
                barimg1.setImageResource(R.drawable.bar11);
                bartv1.setTextColor(Color.WHITE);
                bar1.setBackgroundColor(android.graphics.Color.parseColor("#0080ff"));

                break;
            case R.id.bar2:
                mainviewpager.setCurrentItem(1);
                barimg2.setImageResource(R.drawable.bar22);
                bartv2.setTextColor(Color.WHITE);
                bar2.setBackgroundColor(android.graphics.Color.parseColor("#0080ff"));
                break;
            case R.id.bar3:
                mainviewpager.setCurrentItem(2);
                barimg3.setImageResource(R.drawable.bar33);
                bartv3.setTextColor(Color.WHITE);
                bar3.setBackgroundColor(android.graphics.Color.parseColor("#0080ff"));
                break;
            case R.id.bar4:
                mainviewpager.setCurrentItem(3);
                barimg4.setImageResource(R.drawable.bar44);
                bartv4.setTextColor(Color.WHITE);
                bar4.setBackgroundColor(android.graphics.Color.parseColor("#0080ff"));
                break;
                default:
                    break;

        }

    }
}
