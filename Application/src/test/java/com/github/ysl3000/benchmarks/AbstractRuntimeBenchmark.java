package com.github.ysl3000.benchmarks;

import java.io.FileNotFoundException;

/**
 * Created by ysl3000
 */
public class AbstractRuntimeBenchmark extends AbstractBenchmark {

    @Override
    public void setUp() throws FileNotFoundException {
        super.setUp();
        this.startApplication.startBundles();
    }

    @Override
    public void tearDown(){
        this.startApplication.stopBundles();
        super.tearDown();
    }

}
