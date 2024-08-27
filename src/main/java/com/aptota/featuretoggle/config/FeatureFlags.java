package com.aptota.featuretoggle.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum FeatureFlags implements Feature {

    @Label("THis is a feature toggle one")
    FEATURE_TOGGLE_1,

    @Label("THis is a feature toggle two")
    FEATURE_TOGGLE_2;

    public static boolean isActive(FeatureFlags featureFlags) {
        return FeatureContext.getFeatureManager().isActive(featureFlags);
    }
}
