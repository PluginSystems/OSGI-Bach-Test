package com.github.ysl3000;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ysl3000
 */
public class StartApplicationTest {

    @org.junit.Test
    public void main() throws Exception {

        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(this.getClass().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(0)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(7)
                .threads(1)
                .forks(0)
                .shouldFailOnError(false)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();


    }


    @Benchmark
    public void loadUnloadPerformance() throws FileNotFoundException {
        StartApplication startApplication= new StartApplication();
        startApplication.start();


        startApplication.stop();

    }


    @Benchmark
    public void contextSwitching() throws FileNotFoundException {

        StartApplication startApplication = new StartApplication();

        startApplication.start();


        ContextService contextService = (ContextService) startApplication.getServiceBy(ContextService.class);

        if (contextService != null) {
            contextService.callAPI(API.api);
        } else {
            System.out.println("ContextService not available");
        }

        startApplication.stop();


    }

    @Benchmark
    public void contextSwitchingWithReturntype() throws FileNotFoundException {
        StartApplication startApplication= new StartApplication();

        startApplication.start();


        ContextService contextService = (ContextService) startApplication.getServiceBy(ContextService.class);

        if (contextService != null) {
            System.out.println(contextService.getTestString(API.api));
        } else {
            System.out.println("ContextService not available ");
        }


        startApplication.stop();

    }





}