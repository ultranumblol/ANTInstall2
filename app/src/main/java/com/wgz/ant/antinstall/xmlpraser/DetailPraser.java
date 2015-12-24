package com.wgz.ant.antinstall.xmlpraser;

import com.wgz.ant.antinstall.bean.Detail;

import java.io.InputStream;
import java.util.List;

/**
 * Created by qwerr on 2015/12/23.
 */
public interface DetailPraser {

    public List<Detail> parse(InputStream is) throws Exception;
}
