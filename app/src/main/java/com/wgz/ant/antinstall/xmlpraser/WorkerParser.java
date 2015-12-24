package com.wgz.ant.antinstall.xmlpraser;

import com.wgz.ant.antinstall.bean.Worker;

import java.io.InputStream;
import java.util.List;

/**
 * Created by qwerr on 2015/12/23.
 */
public interface WorkerParser {
    public List<Worker> parse(InputStream is) throws Exception;

}
