package com.github.ysl3000.benchmarks;

import com.github.ysl3000.api.ContextService;
import org.openjdk.jmh.annotations.Benchmark;
import org.osgi.framework.Bundle;

import java.io.FileNotFoundException;

/**
 * Created by ysl3000
 */
public class ContextSwitchingWithReturnValueBenchmark extends AbstractRuntimeBenchmark {


    @Benchmark
    public void contextSwitchingWithReturntype() throws FileNotFoundException {


        Bundle bundle = startApplication.getBundle("com.github.ysl3000.OSGI-FirstProject");

        if (bundle != null) {
            ContextService contextService = (ContextService) bundle.getBundleContext().getServiceReference(ContextService.class);

            if (contextService != null) {
                System.out.println(contextService.getTestString());
            } else {
                System.out.println("ContextService not available ");
            }

        }
    }


}
