package com.github.ysl3000;

import org.openjdk.jmh.annotations.*;
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
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(0)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(7)
                .threads(1)
                .forks(1)
                .shouldFailOnError(false)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();


    }


    //@State(Scope.Benchmark)
    public static class LoadUnloadPerformance {


        private StartApplication application;

        //@Setup
        public void setUp() throws FileNotFoundException {

            this.application = new StartApplication();


        }

        //@Benchmark
        public void loadUnloadPerformance() throws FileNotFoundException {
            this.application.start();

            this.application.stop();

        }

    }

    @State(Scope.Benchmark)
    public static class ContextSwitchingTest {


        private StartApplication startApplication;

        @Setup
        public void setUp() throws FileNotFoundException {
            this.startApplication = new StartApplication();
            this.startApplication.start();
        }


        @Benchmark
        public void contextSwitching() throws FileNotFoundException {

            /*
            ContextService contextService = (ContextService) startApplication.getServiceBy(ContextService.class);

            if (contextService != null) {
                contextService.getTestString();
            } else {
                System.out.println("ContextService not available");
            }
            */

        }

        @Benchmark
        public void contextSwitchingWithReturntype() throws FileNotFoundException {


            //ContextService contextService = (ContextService) startApplication.getServiceBy(ContextService.class);

            /*if (contextService != null) {
                System.out.println(contextService.getTestString());
            } else {
                System.out.println("ContextService not available ");
            }*/

        }

        @TearDown
        public void tearDown() {
            this.startApplication.stop();
            this.startApplication = null;
            System.gc();

        }

    }


}