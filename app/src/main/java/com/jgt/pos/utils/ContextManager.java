package com.jgt.pos.utils;

import android.app.Application;

//TODO: Adjust text size of PRICE if exceeds boundaries
//TODO: Adjust VIEWS for tablet
public class ContextManager extends Application {
    private static ContextManager instance;

    public ContextManager() {
        instance = this;
    }

    public static ContextManager get() {
        return instance;
    }
}
