package com.jgt.pos.utils;

import android.app.Application;

public class ContextManager extends Application {
    private static ContextManager instance;

    public ContextManager() {
        instance = this;
    }

    public static ContextManager get() {
        return instance;
    }
}
