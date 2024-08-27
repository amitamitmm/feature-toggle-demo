package com.aptota.featuretoggle.service.impl;

import com.aptota.featuretoggle.config.FeatureFlags;
import com.aptota.featuretoggle.model.Greeting;
import com.aptota.featuretoggle.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {


    @Override
    public Greeting getMessage() {
        String msg = "Welcome toggle is OFF";
        if(FeatureFlags.isActive(FeatureFlags.FEATURE_TOGGLE_1)) {
            msg = "Welcome to toggle1 is ON";
        }
        return Greeting.builder().message(msg).build();
    }
}
