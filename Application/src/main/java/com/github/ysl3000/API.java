package com.github.ysl3000;

import java.util.List;

/**
 * Created by ysl3000
 */
public class API {

    public static final API api = new API();


    private API() {

    }


    public void logStringlist(List<String> stringList) {
        stringList.forEach(e -> System.out.println(e));
    }

    public String getAPIName(){
        return API.class.getName();
    }


}
