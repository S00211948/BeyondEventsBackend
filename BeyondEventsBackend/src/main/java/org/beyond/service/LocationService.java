package org.beyond.service;

import org.beyond.model.Location;
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

    public Optional<Location> getLocationByID(UUID id) {
        return locationRepository.findByid(id);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location addNewLocation(Location u) {
        return locationRepository.save(u);
    }
}
