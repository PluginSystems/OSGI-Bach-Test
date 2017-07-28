package com.github.ysl3000;

import com.github.ysl3000.benchmarks.ContextSwitchingTest;
import com.github.ysl3000.benchmarks.LoadUnloadEnableDisableBenchmark;
import com.github.ysl3000.utils.TestCase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysl3000
 */
public class StartApplicationTest {

    @org.junit.Test
    public void main() throws Exception {

        List<TestCase> stats = new ArrayList<>();

        StartApplication startApplication = new StartApplication();
        startApplication.start();
        startApplication.stopBundles();
        startApplication.stop();


       // stats.add(new LoadUnloadEnableDisableBenchmark(startApplication));
        stats.add(new ContextSwitchingTest(startApplication));

        // 10, 50, 70, 100, 250
        int[] count = {5000, 10000, 50000};

        long currentTimeMillis = System.currentTimeMillis();


        for (int cycle : count) {

            stats.forEach(testCase -> {
                testCase.RunTestFully(cycle);
            });


            stats.forEach(finishedTest ->
            {
                FileOutputStream streamWriter = null;
                try {
                    streamWriter = new FileOutputStream("./results_" + cycle + "_" + finishedTest.GetName() + "_nanoseconds_" + currentTimeMillis + ".csv");

                    finishedTest.PrintStats(streamWriter);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        streamWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            streamWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


            });
        }

        System.out.println("Test finished");

    }


}