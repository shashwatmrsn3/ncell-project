package com.ncell.app.controller;

import com.ncell.app.model.NcellCenters;
import com.ncell.app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/")
    public ResponseEntity<NcellCenters> getNearestCenter(@RequestParam double lat, @RequestParam double lng) {
        if(lat == 0 || lng == 0) {
            return  new ResponseEntity<>(null, HttpStatusCode.valueOf(200));
        }

        NcellCenters nearest = locationService.getNearestNcellCenter(lat,lng);

        return new ResponseEntity<NcellCenters>(nearest, HttpStatusCode.valueOf(200));
    }
}
