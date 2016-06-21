/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import constants.Constants;
import dto.fcm.CoordinatesData;
import dto.fcm.DataMessageFCM;
import dto.fcm.RequestLocationData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AlzheimerDB;

/**
 *
 * @author atef
 */
public class LocationDao {

    AlzheimerDB alzheimerDB;

    public LocationDao() {
        this.alzheimerDB = new AlzheimerDB();
    }

    /**
     * this method used to register token in database
     *
     * @param email
     * @param token
     * @return
     */
    public int registerToken(String email, String token) {

        int flag = 0;

        Connection connection = alzheimerDB.getConnection();

        String query = "UPDATE `users` SET `user_token`=? WHERE `email` = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, email);

            flag = preparedStatement.executeUpdate();

            // close block .. 
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return flag;
    }

    /**
     * this method used to return token of user.
     *
     * @param email
     * @return
     */
    public String getToken(String email) {
        String sql = "SELECT `user_token` FROM `users` WHERE `email` = ?";
        String token = null;
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        Connection connection = alzheimerDB.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                token = resultSet.getString(1);
            }

            // close block .. 
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return token;

    }

    /**
     * getRequestSenderdata object .
     *
     * @param email
     * @return
     */
    public RequestLocationData getRequestSenderData(String email) {

        String sql = "SELECT `first_name`, `last_name`, `image_url` FROM `users` WHERE `email` = ?";
        RequestLocationData data = new RequestLocationData();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.setFirstname(resultSet.getString(1));
                data.setLastname(resultSet.getString(2));
                data.setImageUrl(Constants
                        .IMAGE_PATH+resultSet.getString(3));
                data.setEmail(email);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    /**
     * get image URL for an email
     * @param email
     * @return 
     */
    public String getImageUrl(String email) {

        String imageUrl = null;
        String sql = "SELECT `image_url` FROM `users` WHERE `email` = ?";

        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                imageUrl = Constants.IMAGE_PATH + resultSet.getString(1);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return imageUrl;
    }

}
