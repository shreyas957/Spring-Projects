package com.shreyas.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev"})  // only create bean if "dev" profile is active
public class ProfileExample {
    public ProfileExample() {
        System.out.println("Dev Profile Active");
    }
}
