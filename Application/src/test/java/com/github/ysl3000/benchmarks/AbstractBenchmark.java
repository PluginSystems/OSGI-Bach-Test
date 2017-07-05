package com.github.ysl3000.benchmarks;

import com.github.ysl3000.StartApplication;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.io.FileNotFoundException;

/**
 * Created by ysl3000
 */
@State(Scope.Benchmark)
public abstract class AbstractBenchmark {



    protected StartApplication startApplication;

    @Setup
    public void setUp() throws FileNotFoundException {
        this.startApplication = new StartApplication();
        this.startApplication.start();
        this.startApplication.printBundle();
    }




    @TearDown
    public void tearDown() {
        this.startApplication.stop();
        this.startApplication = null;
    }

}
