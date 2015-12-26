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

    public OrderAdapter(List<Map<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.order_lv_item,null);
            holder.orderID = (TextView) convertView.findViewById(R.id.order_id);
            holder.orderimg = (ImageView) convertView.findViewById(R.id.order_img);
            holder.ordertype = (TextView) convertView.findViewById(R.id.order_type);
            holder.workername = (TextView) convertView.findViewById(R.id.order_content);
            convertView.setTag(holder);


        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        Map<String,Object> map = data.get(position);


        holder.orderID.setText(map.get("orderid").toString());
        holder.workername.setText(map.get("workername").toString());
        holder.ordertype.setText(map.get("type").toString());
        if (map.get("type").toString().equals("1")){
            holder.orderimg.setImageResource(R.drawable.gougou);

        }if (map.get("type").toString().equals("0")){
            holder.orderimg.setImageResource(R.drawable.chacha);

        }


        return convertView;
    }
    private class ViewHolder{
        private TextView orderID;
        private TextView workername;
        private ImageView orderimg;
        private TextView ordertype;



    }
}
