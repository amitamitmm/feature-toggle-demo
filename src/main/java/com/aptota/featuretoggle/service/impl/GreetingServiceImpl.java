package com.aptota.featuretoggle.service.impl;

import com.aptota.featuretoggle.config.BeFeatureFlags;
import com.aptota.featuretoggle.model.Greeting;
import com.aptota.featuretoggle.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final FeatureManager featureManager;

    @Override
    public Greeting getMessage() {
        String msg = "Welcome toggle is OFF";
        if(featureManager.isActive(BeFeatureFlags.BE_FEATURE_TOGGLE_1)) {
            msg = "Welcome to toggle1 is ON";
        }
        return Greeting.builder().message(msg).build();
    }
}
