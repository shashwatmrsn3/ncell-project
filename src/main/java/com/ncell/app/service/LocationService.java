package com.ncell.app.service;

import com.ncell.app.dao.LocationDao;
import com.ncell.app.model.NcellCenters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationDao locationDao;

    private static final double EARTH_RADIUS_METERS = 6371000;

    public NcellCenters getNearestNcellCenter(double latitude, double longitude) {
        NcellCenters nearest = null;
        double minDistance = Double.MAX_VALUE;
        List<NcellCenters> allCenters = locationDao.findAll();
        for (NcellCenters c : allCenters) {
            double dist = haversineDistance(latitude, longitude, c.getLatitude(), c.getLongitude());
            if (dist < minDistance) {
                minDistance = dist;
                nearest = c;
            }
        }

        return nearest;
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double rLat1 = Math.toRadians(lat1);
        double rLat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat/2)*Math.sin(dLat/2) +
                Math.cos(rLat1)*Math.cos(rLat2)*Math.sin(dLon/2)*Math.sin(dLon/2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS_METERS * c;
    }
}
