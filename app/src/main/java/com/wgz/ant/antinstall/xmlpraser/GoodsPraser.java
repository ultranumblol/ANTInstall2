package com.wgz.ant.antinstall.xmlpraser;

import com.wgz.ant.antinstall.bean.Goods;

import java.io.InputStream;
import java.util.List;

/**
 * Created by qwerr on 2015/12/23.
 */
public interface GoodsPraser {

    public List<Goods> parse(InputStream is) throws Exception;
}
