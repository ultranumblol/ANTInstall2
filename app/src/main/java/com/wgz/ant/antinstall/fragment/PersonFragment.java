package com.wgz.ant.antinstall.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wgz.ant.antinstall.LoginActivity;
import com.wgz.ant.antinstall.R;

import java.io.File;

/**
 * Created by qwerr on 2015/11/16.
 */
public class PersonFragment extends Fragment {
    private TextView outLogin;
    private LinearLayout testmap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personfragment,null);
        initview(view);
        return  view;
    }

    private void initview(View view) {
        outLogin = (TextView) view.findViewById(R.id.login_out);
        testmap = (LinearLayout) view.findViewById(R.id.testmap);
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("注销").setMessage("是否注销登陆，返回登陆页面？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file= new File("/data/data/"+getActivity().getPackageName().toString()+"/shared_prefs","autologin.xml");
                        if(file.exists()){
                            file.delete();
                            SharedPreferences sp = getActivity().getSharedPreferences("autologin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.clear().commit();

                            Toast.makeText(getActivity().getApplicationContext(), "注销成功！", Toast.LENGTH_LONG).show();
                            getActivity().finish();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "注销成功！", Toast.LENGTH_LONG).show();
                            getActivity().finish();
                            startActivity(new Intent(getActivity(), LoginActivity.class));

                        }
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
    }
}
