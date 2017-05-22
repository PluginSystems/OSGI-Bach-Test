package com.github.ysl3000;

import org.osgi.framework.ServiceReference;

/**
 * Created by ysl3000
 */
public interface ContextService extends ServiceReference {

    void callAPI(API api);

    String getTestString(API api);

}
