package com.github.ysl3000.benchmarks;

import com.github.ysl3000.StartApplication;
import com.github.ysl3000.api.ContextService;
import com.github.ysl3000.utils.PluginTestCase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import java.io.FileNotFoundException;

/**
 * Created by ysl3000
 */
public class ContextSwitchingTest extends PluginTestCase {


    private ContextService contextService;

    public ContextSwitchingTest(StartApplication startApplication) {
        super(startApplication);
    }


    @Override
    public void SetUp() throws FileNotFoundException {
        super.SetUp();
        startApplication.startBundles();

        Bundle bundle = startApplication.getBundle("com.github.ysl3000.TestBundle");


        if (bundle != null) {
            BundleContext bundleContext = FrameworkUtil.getBundle(bundle.getClass()).getBundleContext();

            if(bundleContext!=null){

                contextService =  bundleContext.getService(bundleContext.getServiceReference(ContextService.class));
            }else{
                System.out.println("bundlecontext null");
            }
        }

    }

    @Override
    protected void RunTest(int currentCycle) {


        if (contextService != null) {
            contextService.printAvailability();
        }


        StartTimer();

        if (contextService != null) {
            contextService.test();
        }
        StopTimer();

        DefineBenchmarkPoint(currentCycle, "context_switch");
        ResetTimer();

    }

    @Override
    public void TearDown() {
        startApplication.stopBundles();
        super.TearDown();
    }
}
