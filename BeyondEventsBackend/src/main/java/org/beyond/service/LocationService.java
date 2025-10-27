package org.beyond.service;

import org.beyond.model.LocationEntity;
import org.beyond.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Optional<LocationEntity> getLocationByID(UUID id) {
        return locationRepository.findByid(id);
    }

    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    public LocationEntity addNewLocation(LocationEntity u) {
        return locationRepository.save(u);
    }
}
