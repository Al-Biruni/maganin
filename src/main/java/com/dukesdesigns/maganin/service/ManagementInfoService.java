package com.dukesdesigns.maganin.service;

import io.quarkus.runtime.configuration.ProfileManager;
import com.dukesdesigns.maganin.config.JHipsterInfo;
import com.dukesdesigns.maganin.service.dto.ManagementInfoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
* Provides information for management/info resource
*/
@ApplicationScoped
public class ManagementInfoService {

    private final JHipsterInfo JHipsterInfo;

    @Inject
    public ManagementInfoService(JHipsterInfo JHipsterInfo) {
        this.JHipsterInfo = JHipsterInfo;
    }

    public ManagementInfoDTO getManagementInfo(){
        var info = new ManagementInfoDTO();
        if(JHipsterInfo.isEnable()){
            info.activeProfiles.add("swagger");
        }
        info.activeProfiles.add(ProfileManager.getActiveProfile());
        info.displayRibbonOnProfiles = ProfileManager.getActiveProfile();
        return info;
    }
}

