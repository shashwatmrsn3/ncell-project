package com.ncell.app.dao;

import com.ncell.app.model.NcellCenters;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationDao {

    public List<NcellCenters> findAll() {
        List<NcellCenters> ncellCenters = new ArrayList<NcellCenters>();
        String sql = "select * from ncell_centers";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                NcellCenters center = new NcellCenters(name, latitude, longitude);
                ncellCenters.add(center);
            }
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ncellCenters;
    }
}
