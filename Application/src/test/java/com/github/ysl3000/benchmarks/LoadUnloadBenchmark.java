package com.github.ysl3000.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by ysl3000
 */
public class LoadUnloadBenchmark extends AbstractBenchmark{

    @Benchmark
    public void loadUnloadPerformance() {

        this.startApplication.startBundles();
        this.startApplication.stopBundles();

    }

}
