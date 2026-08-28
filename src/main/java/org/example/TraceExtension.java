package org.example;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TraceExtension implements BeforeEachCallback, AfterEachCallback {

    public static String testName;

    @Override
    public void beforeEach(ExtensionContext context) {
        testName = context.getRequiredTestMethod().getName();
    }

    @Override
    public void afterEach(ExtensionContext context) {}
}