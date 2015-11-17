package com.wgz.ant.antinstall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wgz.ant.antinstall.R;

/**
 * Created by qwerr on 2015/11/16.
 */
public class PersonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personfragment,null);
        return  view;
    }
}
