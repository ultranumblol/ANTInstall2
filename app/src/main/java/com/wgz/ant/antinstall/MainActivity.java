package com.wgz.ant.antinstall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.wgz.ant.antinstall.adapter.MyFragmentPagerAdapter;
import com.wgz.ant.antinstall.fragment.MapFragment;
import com.wgz.ant.antinstall.fragment.MsgFragment;
import com.wgz.ant.antinstall.fragment.OrderFragment;
import com.wgz.ant.antinstall.fragment.PersonFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private LinearLayout bar1,bar2,bar3,bar4;
    private ArrayList<Fragment> fragments;//页面列表
    private ViewPager mainviewpager;
    private MsgFragment msgFragment;
    private MapFragment mapFragment;
    private PersonFragment personFragment;
    private OrderFragment orderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_antinsatll);
        initview();

    }

    public void initview() {
         msgFragment = new MsgFragment();
        mapFragment = new MapFragment();
        orderFragment = new OrderFragment();
        personFragment = new PersonFragment();
        mainviewpager = (ViewPager) findViewById(R.id.viewpager);
        bar1= (LinearLayout) findViewById(R.id.bar1);
        bar2= (LinearLayout) findViewById(R.id.bar2);
        bar3= (LinearLayout) findViewById(R.id.bar3);
        bar4= (LinearLayout) findViewById(R.id.bar4);
        fragments = new ArrayList<Fragment>();
        fragments.add(msgFragment);
        fragments.add(orderFragment);
        fragments.add(mapFragment);
        fragments.add(personFragment);

        mainviewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments));
    }

    public  void BaronClick(View view){
        int id = view .getId();
        switch (id){
            case  R.id.bar1:
                mainviewpager.setCurrentItem(0);
                break;
            case R.id.bar2:
                mainviewpager.setCurrentItem(1);
                break;
            case R.id.bar3:
                mainviewpager.setCurrentItem(2);
                break;
            case R.id.bar4:
                mainviewpager.setCurrentItem(3);
                break;
                default:
                    break;

        }

    }
}
