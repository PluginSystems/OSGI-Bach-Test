package com.github.ysl3000;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by ysl3000
 */
public class TestActivator implements BundleActivator {


    public void start(BundleContext context) throws Exception {

        System.out.println("Starting: "+getClass().getSimpleName());


    }

    public void stop(BundleContext context) throws Exception {

        System.out.println("Stopping: "+getClass().getSimpleName());
    }
}
