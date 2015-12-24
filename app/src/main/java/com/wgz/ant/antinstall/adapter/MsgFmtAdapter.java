package com.wgz.ant.antinstall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wgz.ant.antinstall.R;

import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/12/24.
 */
public class MsgFmtAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater inflater;
    private Context context;

    public MsgFmtAdapter(List<Map<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.msglv_item,null);
            viewHolder.workID = (TextView) convertView.findViewById(R.id.msg_workID);
            viewHolder.workerName = (TextView) convertView.findViewById(R.id.msg_workerName);
            convertView.setTag(viewHolder);


        }else {
            viewHolder= (ViewHolder) convertView.getTag();

        }
        Map<String,Object> map = data.get(position);
        viewHolder.workID.setText(map.get("workid").toString());
        viewHolder.workerName.setText(map.get("workname").toString());



        return convertView;
    }
    class ViewHolder{
        private TextView workID;
        private TextView workerName;


    }


}
