/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Relative;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AlzheimerDB;

/**
 *
 * @author Doaa
 */
public class RelationDaoImpl implements RelationDaoInterface {

    /**
     * this method used to get List of relatives of this patients.
     * @param patientEmail
     * @return ArrayList of Relative
     */
    @Override
    public ArrayList<Relative> getRelatives(String patientEmail) {

        //Query to select all relatives 
        String selectRelatives = "select * from alzheimer.users where user_id in(select relative_id from\n"
                + "alzheimer.relationship where patient_id in(select user_id from alzheimer.users where email= ?));";

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        ArrayList<Relative> relativesList = new ArrayList<>();
        ArrayList<Relative> relatives=new ArrayList<>();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectRelatives);
            preparedStatement.setString(1, patientEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

            
                Relative relative = new Relative();
                relative.setUserId(rs.getInt("user_id"));
                relative.setFirstName(rs.getString("first_name"));
                relative.setLastName(rs.getString("last_name"));
                relative.setBirthday(rs.getDate("birthday"));
                relative.setGender(rs.getInt("gender"));
                relative.setEmail(rs.getString("email"));
                relative.setPhoneNumber(rs.getString("phone_num"));
                relative.setHomeNumber(rs.getString("home_num"));
                relative.setCountry(rs.getString("country"));
                relative.setCity(rs.getString("city"));
                relative.setAddress(rs.getString("address"));
                relative.setType(rs.getInt("type"));
                relative.setPassword(rs.getString("password"));
                relative.setLongitude(rs.getString("longitude"));
                relative.setImageUrl(rs.getString("image_url"));
                relative.setLatitude(rs.getString("latitude"));
                relativesList.add(relative);
                System.err.println("done");
            }
            RequestDaoImpl reqDaoImpl=new RequestDaoImpl();
            relatives=reqDaoImpl.addPositionFamilyToGetRequest(relativesList);
            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return relatives;

    }

    /**
     * This method used to get list of patients to this relative.
     *
     * @param relativeEmail
     * @return ArrayList of User
     */
    @Override
    public ArrayList<User> getPatients(String relativeEmail) {

        //Query to select all patients
        String selectPatients = "select * from alzheimer.users where user_id in(select patient_id from\n"
                + "alzheimer.relationship where relative_id in(select user_id from alzheimer.users where email= ?));";

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        ArrayList<User> patientsList = new ArrayList<User>();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectPatients);
            preparedStatement.setString(1, relativeEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
               
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getInt("gender"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_num"));
                user.setHomeNumber(rs.getString("home_num"));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setType(rs.getInt("type"));
                user.setPassword(rs.getString("password"));
                user.setLongitude(rs.getString("longitude"));
                user.setImageUrl(rs.getString("image_url"));
                user.setLatitude(rs.getString("latitude"));

                patientsList.add(user);

            }
            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return patientsList;

    }

    /**
     * this method is used to remove patient from patients list in relative
     * list.
     *
     * @param patientEmail
     * @param relativeEmail
     * @return int
     */
    @Override
    public int removePatient(String patientEmail, String relativeEmail) {
        int patientId = 0;
        int result = 0;
        // Query to select patient_id
        String selectIdPatientQuery = "select user_id from alzheimer.users where email= ?";
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectIdPatientQuery);
            preparedStatement.setString(1, patientEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                patientId = rs.getInt("user_id");
            }
            //Query to delete patient from relationship table
            String deleteRelationshipQuery = "delete from alzheimer.relationship where patient_id= ?";
            preparedStatement = connection.prepareStatement(deleteRelationshipQuery);
            preparedStatement.setInt(1, patientId);

           result= preparedStatement.executeUpdate();
//            //Query to delete patient from request table
//            String deleteRequestQuery = "delete from alzheimer.request where patient_id= ?";
//            preparedStatement = connection.prepareStatement(deleteRequestQuery);
//            preparedStatement.setInt(1, patientId);
//
//            preparedStatement.executeUpdate();
//            //Query to delete patient from users table
//            String deleteUserQuery = "delete from alzheimer.users where user_id= ?";
//            preparedStatement = connection.prepareStatement(deleteUserQuery);
//            preparedStatement.setInt(1, patientId);
//
//            result = preparedStatement.executeUpdate();

            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

}
