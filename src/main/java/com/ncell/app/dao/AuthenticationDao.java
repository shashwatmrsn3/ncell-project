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


    public Optional<Number> getNumber(long number) {
        Number numberObj = null;
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "select phone_number from numbers where phone_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberObj = new Number(resultSet.getLong("phone_number"));
            }

            return Optional.ofNullable(numberObj);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }

    public boolean getAndDisableOtp(long number, int otp) {
        boolean isValid = false;

        String getOTPSQL = """
                select * from otp where
                phone_number = ? and otp = ? 
                and is_used = 0 and 
                current_timestamp < expiration_timestamp
                """;

        String disableOTPSQL = "update otp set is_used = 1 where phone_number = ? and otp = ?";

        try {
            Connection connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(getOTPSQL);
            preparedStatement.setLong(1, number);
            preparedStatement.setInt(2, otp);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(disableOTPSQL);
                preparedStatement2.setLong(1,number);
                preparedStatement2.setInt(2, otp);
                preparedStatement2.executeUpdate();

                connection.commit();

                isValid = true;
            }
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public void invalidateAllOtp(long number){
        String sql = """
                update otp set is_used = 1 where phone_number = ?   
                """;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOTP(long number, int otp) {
        String sql = """
                   insert into otp(otp, phone_number, expiration_timestamp, is_used)
                   values(?,?,DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 MINUTE),?)
                """;
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, otp);
            preparedStatement.setLong(2, number);
            preparedStatement.setBoolean(3, false);
            int affectedrows = preparedStatement.executeUpdate();
            System.out.println(affectedrows);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
