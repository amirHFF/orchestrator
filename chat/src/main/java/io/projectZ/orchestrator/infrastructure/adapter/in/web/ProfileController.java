package io.projectZ.orchestrator.infrastructure.adapter.in.web;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:18 PM
*/

import io.projectZ.orchestrator.application.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sync")
    public ResponseEntity<Void> manualSyncProfiles() {
        profileService.syncProfiles();
        return ResponseEntity.ok().build();
    }
}

