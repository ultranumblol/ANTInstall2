package com.wgz.ant.antinstall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wgz.ant.antinstall.R;

import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/12/25.
 */
public class OrderAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private int state;

    public OrderAdapter(List<Map<String, Object>> data, Context context,int state) {
        this.data = data;
        this.context = context;
        this.state = state;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if (data.size()<100){
            return data.size();
        }else {

            return 100;
        }
        //return data.size();
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
        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.order_lv_item,null);
            holder.orderID = (TextView) convertView.findViewById(R.id.order_id);
            holder.orderimg = (ImageView) convertView.findViewById(R.id.order_img);
            holder.workID = (TextView) convertView.findViewById(R.id.work_id);
            holder.workername = (TextView) convertView.findViewById(R.id.order_content);
            convertView.setTag(holder);


        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        Map<String,Object> map = data.get(position);


        holder.orderID.setText(map.get("aznumber").toString());
        holder.workername.setText(map.get("workerName").toString());
        holder.workID.setText(map.get("workID").toString());
        if (state==1){
            holder.orderimg.setImageResource(R.drawable.gougou);

        }if (state==2){
            holder.orderimg.setImageResource(R.drawable.chacha);

        }


        return convertView;
    }
    private class ViewHolder{
        private TextView orderID;
        private TextView workername;
        private ImageView orderimg;
        private TextView workID;



    }
}
