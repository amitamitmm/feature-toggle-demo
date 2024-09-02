package com.aptota.featuretoggle.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.FeatureGroup;
import org.togglz.core.context.FeatureContext;

@FeatureGroup("FE Features")
public enum FeFeatureFlags implements Feature {


    FE_FEATURE_TOGGLE_1,

    FE_FEATURE_TOGGLE_2,

    FE_FEATURE_TOGGLE_3,

    FE_FEATURE_TOGGLE_4,

    FE_FEATURE_TOGGLE_5;

}
