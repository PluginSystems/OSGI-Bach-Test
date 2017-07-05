package com.github.ysl3000;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

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
                .include(this.getClass().getPackage().getName() + ".*")
                // Set the following options as needed
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .timeout(TimeValue.NONE)
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(70)
                .threads(1)
                .forks(1)
                .shouldFailOnError(false)
                .shouldDoGC(true)
                //.jvmArgs("-Djmh.ignoreLock=true" /*"-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining"*/)
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();


    }





}