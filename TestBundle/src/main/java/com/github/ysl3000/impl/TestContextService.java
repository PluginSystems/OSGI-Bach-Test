package com.github.ysl3000.impl;

import com.github.ysl3000.api.ContextService;

import java.util.Arrays;

/**
 * Created by ysl3000
 */
public class TestContextService implements ContextService {
    @Override
    public void printString() {
        Arrays.asList("Hey", "das", "ist", "ein", "Test").forEach(System.out::println);
    }

    @Override
    public String getTestString() {
        return "Der Teststring ist: teststring ";
    }
}
