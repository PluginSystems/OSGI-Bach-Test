package com.github.ysl3000.impl;

import com.github.ysl3000.api.ContextService;

import java.util.Arrays;

/**
 * Created by ysl3000
 */
public class TestContextService implements ContextService {
    @Override
    public void test() {

    }

    @Override
    public void printAvailability() {
        System.out.println("jep ist vorhanden");
    }
}
