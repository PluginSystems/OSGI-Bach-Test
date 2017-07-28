package com.github.ysl3000.utils;


import com.github.ysl3000.StartApplication;

import java.io.FileNotFoundException;

public abstract class PluginTestCase extends TestCase {

    protected StartApplication startApplication;

    public PluginTestCase(StartApplication startApplication) {
        this.startApplication=startApplication;
    }



    @Override
    public void SetUp() throws FileNotFoundException {

        this.startApplication.start();
        this.startApplication.printBundle();
    }


    @Override
    public void TearDown() {
        this.startApplication.stop();
    }

}