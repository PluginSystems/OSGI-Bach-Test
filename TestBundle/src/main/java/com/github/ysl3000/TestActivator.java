package com.github.ysl3000;

import com.github.ysl3000.api.ContextService;
import com.github.ysl3000.impl.TestContextService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Properties;

/**
 * Created by ysl3000
 */
public class TestActivator implements BundleActivator {


    public void start(BundleContext context) throws Exception {


        Dictionary dic = new Properties();

        context.registerService(ContextService.class, new TestContextService(), dic);
    }

    public void stop(BundleContext context) throws Exception {

    }

}
