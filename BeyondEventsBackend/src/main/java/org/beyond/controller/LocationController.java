package org.beyond.controller;

import lombok.extern.slf4j.Slf4j;
import org.beyond.enums.FacilityFeatures;
import org.beyond.model.LocationEntity;
import org.beyond.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping
    public List<LocationEntity> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/features/office")
    public List<String> getOfficeFeatures() {
        return Arrays.stream(FacilityFeatures.values())
                .filter(FacilityFeatures::isOfficeFeature)
                .map(FacilityFeatures::getValue)
                .toList();
    }

    @GetMapping("/features/all")
    public List<String> getGeneralFeatures() {
        return Arrays.stream(FacilityFeatures.values())
                .map(FacilityFeatures::getValue)
                .toList();
    }
}
