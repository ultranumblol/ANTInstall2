package com.wgz.ant.antinstall.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 签名生成器
 * @author qwerr
 *
 */
public class SignMaker {
	/**
	 * 获得加密的签名
	 * @return  返回md5加密的签名
	 */
	public String getsign(String type,String id,String state){
		MD5Util md5Util = new MD5Util();
		ArrayList<String> pass = new ArrayList<String>();
		if (state==null) {
			pass.add(type);
			pass.add(id);
		}
		if (state!=null){
			pass.add(type);
			pass.add(id);
			pass.add(state);

		}
		Log.i("xml", "===========" + pass.toString());
		Collections.sort(pass);//对数组里的元素按首字母排序
		String result = "";
		String seprater = "&";
		if (pass.size()==2) {
			result=pass.get(0)+seprater+pass.get(1);
		}if(pass.size()==3) {
			result=pass.get(0)+seprater+pass.get(1)+seprater+pass.get(2);
		}
		String sign1=md5Util.MD5(result);
		Log.i("xml", "加密内容：" + result + "加密后" + sign1);
		return sign1;


	}
	public String getsign(String name){
		MD5Util md5Util = new MD5Util();
		String sign1=md5Util.MD5(name);
		Log.i("xml", "加密内容：" + name + "加密后" + sign1);
		return sign1;


	}
}
