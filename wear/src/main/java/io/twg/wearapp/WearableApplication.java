package io.twg.wearapp;

import android.app.Application;

public class WearableApplication extends Application {

    private boolean isMainViewLaunched;

    public boolean isMainViewLaunched() {
        return isMainViewLaunched;
    }

    public void setMainViewLaunched(boolean mainViewLaunched) {
        isMainViewLaunched = mainViewLaunched;
    }

}
