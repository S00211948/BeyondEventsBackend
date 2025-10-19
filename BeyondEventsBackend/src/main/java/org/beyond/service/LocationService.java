package org.beyond.service;

import org.beyond.model.Location;
import org.beyond.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    LocationRepository LocationRepository;

    public Optional<Location> getLocationByID(UUID Id) {
        return LocationRepository.findByid(Id);
    }

    public Location addNewLocation(Location u)
    {
        return LocationRepository.save(u);
    }
}
