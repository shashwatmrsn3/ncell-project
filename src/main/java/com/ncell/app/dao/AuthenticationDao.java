package com.ncell.app.dao;

import com.ncell.app.model.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Service
public class AuthenticationDao {


    public Optional<Number> getNumber(long number){
        Number numberObj = null;
        try{
            Connection connection = DBConnection.getConnection();
            String sql = "select phone_number from numbers where phone_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                 numberObj = new Number(resultSet.getLong("phone_number"));
            }

            return Optional.ofNullable(numberObj);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }

    public void saveOTP(long number, int otp){
        String sql = """
                        insert into otp(otp, phone_number, expiration_timestamp, is_used)
                        values(?,?,DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 MINUTE),?)
                     """;
        try{
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, otp);
            preparedStatement.setLong(2, number);
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Optional<Number> getOTP(long number){}
}
