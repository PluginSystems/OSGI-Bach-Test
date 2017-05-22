package com.github.ysl3000;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by ysl3000 on 26.01.17.
 */
public class StartApplication {


    private Felix felix;
    private Set<Bundle> bundles = new HashSet<>();

    public StartApplication() throws FileNotFoundException {
        Map<String, Object> felixConfig = new HashMap<>();

        felixConfig.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                "some.module.i.dont.know; version=1.0.0");


        List<Object> list = new LinkedList<>();
        felixConfig.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, list);

        this.felix = new Felix(felixConfig);
        try {
            this.felix.init();

            File dir = new File("testbundles/");

            dir.mkdirs();


            File[] files = dir.listFiles((dir1, name) -> name.endsWith(".jar"));

            for (File file : files) {
                Bundle bundle = this.felix.getBundleContext().installBundle("file:" + file.getAbsolutePath());
                bundles.add(bundle);
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

    public ServiceReference getServiceBy(Class<? extends ServiceReference> serviceReferenceClass) {
        BundleContext context = felix.getBundleContext();

        return context.
                getServiceReference(serviceReferenceClass);
    }
}
