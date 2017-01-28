package com.github.ysl3000;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Created by ysl3000 on 26.01.17.
 */
public class StartApplication {


    private Felix felix;
    private Set<Bundle> bundles = new HashSet<Bundle>();

    public StartApplication() throws FileNotFoundException {
        Map<String, Object> felixConfig = new HashMap<String, Object>();

        felixConfig.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                "some.module.i.dont.know; version=1.0.0");


        List<Object> list = new LinkedList<Object>();
        felixConfig.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, list);

        this.felix = new Felix(felixConfig);
        try {
            this.felix.init();

            File dir = new File("testbundles/");

            dir.mkdirs();


            File[] files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });

            for (File file : files) {
                bundles.add(this.felix.getBundleContext().installBundle("file:"+file.getAbsolutePath()));
            }

        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        StartApplication application = null;
        try {
            application = new StartApplication();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        application.start();
        application.stop();


    }

    public void start() {

        try {
            this.felix.start();

            for (Bundle bundle :
                    bundles) {
                bundle.start();
            }
        } catch (BundleException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

        try {

            for (Bundle bundle :
                    bundles) {
                bundle.stop();
            }

            this.felix.stop();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }
}
