package com.wgz.ant.antinstall.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qwerr on 2016/1/8.
 */
public class PostInputStream {
    private InputStream inStream;

    public InputStream getStream(Context context,String type, String id, String state, String sign, String username, String remark, String code){
        RequestQueue mQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "url", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("volley","result:"+s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("volley","error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");

                return map;
            }
        };
        mQueue.add(stringRequest);

        return null;
    }

}
