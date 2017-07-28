package com.github.ysl3000.benchmarks;

import com.github.ysl3000.StartApplication;
import com.github.ysl3000.utils.PluginTestCase;

import java.io.FileNotFoundException;

/**
 * Created by ysl3000
 */
public class LoadUnloadEnableDisableBenchmark extends PluginTestCase {


    public LoadUnloadEnableDisableBenchmark(StartApplication startApplication) {
        super(startApplication);
    }

    @Override
    protected void RunTest(int currentCycle) {


        StartTimer();
        this.startApplication.startBundles();
        this.startApplication.stopBundles();
        StopTimer();

        DefineBenchmarkPoint(currentCycle, "load_unload_enable_disable");

        ResetTimer();

    }
}
