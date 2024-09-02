package com.aptota.featuretoggle.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.FeatureGroup;
import org.togglz.core.context.FeatureContext;

@FeatureGroup("BE Features")
public enum BeFeatureFlags implements Feature {


    BE_FEATURE_TOGGLE_1,

    BE_FEATURE_TOGGLE_2,

    BE_FEATURE_TOGGLE_3,

    BE_FEATURE_TOGGLE_4,

    BE_FEATURE_TOGGLE_5;

}
