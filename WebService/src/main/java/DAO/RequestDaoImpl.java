/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Relative;
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
public class RequestDaoImpl implements RequestDaoInterface {

    /**
     * This method used to get list of user requests
     * @param patientEmail
     * @return array list of users that represents relatives
     */
    @Override
    public ArrayList<Relative> getRequests(String patientEmail) {

        //Query to select relative from users table
        String selectRelativeQuery = "select * From alzheimer.users where user_id in(select relative_id from alzheimer.request where  patient_id in(select user_id from alzheimer.users where email= ?)); ";
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        ArrayList<Relative> relativesReq = new ArrayList<>();
        ArrayList<Relative> relativeRequests=new ArrayList<>();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectRelativeQuery);
            preparedStatement.setString(1, patientEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("enter while get request");
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
                relative.setLongitude(rs.getString("longitude"));
                relative.setImageUrl(rs.getString("image_url"));
                relative.setLatitude(rs.getString("latitude"));
 
                relativesReq.add(relative);
          
            }
           
            relativeRequests=addPositionFamilyToGetRequest(relativesReq);
            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return relativeRequests;

    }

    /**
     * this method used to respond to request with o if he decline or 1 if he accept
     * @param patientEmail
     * @param relativeEmail
     * @param respond
     * @return int
     */
    @Override
    public int respondToRequest(String patientEmail, String relativeEmail, boolean respond) {

        int relativeId = 0;
        int patientId = 0;
        int familyPositionId = 0;
        int resultOfRespond = 0;

        //when patient accept request
        if (respond == true) {
            //Query to select relative id from users table
            String selectIdRelativeQuery = "select user_id from alzheimer.users where email= ?";
            AlzheimerDB alzheimerDB = new AlzheimerDB();
            Connection connection = alzheimerDB.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectIdRelativeQuery);
                preparedStatement.setString(1, relativeEmail);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    relativeId = rs.getInt("user_id");
                }
                //Query to select relative from request table
                String selectQuery = "select * From alzheimer.request where relative_id= ? ";
                preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, relativeId);

                rs = preparedStatement.executeQuery();

                while (rs.next()) {

                    patientId = rs.getInt("patient_id");
                    familyPositionId = rs.getInt("family_position_id");
                }
                System.out.println(patientId + " " + familyPositionId + " " + relativeId);
                //Query to insert relative to relationship table
                String insertQuery = "insert into alzheimer.relationship (relative_id,patient_id,family_position_id) values(?,?,?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, relativeId);
                preparedStatement.setInt(2, patientId);
                preparedStatement.setInt(3, familyPositionId);

                resultOfRespond = preparedStatement.executeUpdate();
              
                //Query to delete relative from request table
                String deleteQuery = "delete from alzheimer.request where relative_id= ?";
                preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, relativeId);
                preparedStatement.executeUpdate();

                rs.close();
                preparedStatement.close();
                connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //when patient decline request
            if (respond == false) {
                String deleteFromRequestQuery = "delete from alzheimer.request where relative_id= ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(deleteFromRequestQuery);
                    preparedStatement.setInt(1, relativeId);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    connection.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }//end if

        }
        return resultOfRespond;

    }

    /**
     * this method used to add a patient using its email by Relative
     * @param relativeEmail
     * @param patientEmail
     * @return int
     */
    @Override
    public int addRequest(String relativeEmail, String patientEmail, int familyPosition) {
        int relativeId = 0;
        int patientId = 0;
        int result = 0;

        //Query to select relative id from users table
        String selectIdRelativeQuery = "select user_id from alzheimer.users where email= ?";
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectIdRelativeQuery);
            preparedStatement.setString(1, relativeEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                relativeId = rs.getInt("user_id");
            }
            //Query to select patient id from users table
            String selectIdPatientQuery = "select user_id from alzheimer.users where email= ?";

            connection = alzheimerDB.getConnection();

            preparedStatement = connection.prepareStatement(selectIdRelativeQuery);
            preparedStatement.setString(1, patientEmail);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                patientId = rs.getInt("user_id");
            }
        
            //Query to add request into request table
            String addRequestQuery = "insert into alzheimer.request (relative_id,patient_id,family_position_id) values(?,?,?)";
            preparedStatement = connection.prepareStatement(addRequestQuery);
            preparedStatement.setInt(1, relativeId);
            preparedStatement.setInt(2, patientId);
            preparedStatement.setInt(3, familyPosition);

            result = preparedStatement.executeUpdate();

            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
    /**
     * This method used to get position family id 
     * @param relativeId
     * @return int
     */
    public int getPositionFamily(int relativeId){
    
    int familyPositionId=0;
       
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        try {
          
        //Query to select family position id from request table
            String selectFamilyPosition="select family_position_id from alzheimer.request where relative_id= ?";  
            PreparedStatement preparedStatement = connection.prepareStatement(selectFamilyPosition);
            preparedStatement.setInt(1, relativeId);
            ResultSet rs=preparedStatement.executeQuery();
            
             while (rs.next()) {
                familyPositionId = rs.getInt("family_position_id");
            }
             
            String selectFamilyPos="select family_position_id from alzheimer.relationship where relative_id= ?";  
            preparedStatement = connection.prepareStatement(selectFamilyPos);
            preparedStatement.setInt(1, relativeId);
            rs=preparedStatement.executeQuery();
            
             while (rs.next()) {
                familyPositionId = rs.getInt("family_position_id");
            }  
            
            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    return familyPositionId;
    }
    /**
     * This method used to add position family id to array list of relative.
     * @param arrayListOfRelative
     * @return ArrayList of Relative
     */
    public ArrayList<Relative>addPositionFamilyToGetRequest(ArrayList<Relative> arrayListOfRelative){
     
       for(int i=0;i<arrayListOfRelative.size();i++)
       {
           Relative relative=arrayListOfRelative.get(0);
           System.out.println("firstName "+relative.getFirstName());
           int relativeId=relative.getUserId();
           int positionFamily=getPositionFamily(relativeId);
           relative.setRelationshipPosition(positionFamily);
           System.out.println("family posId"+relative.getRelationshipPosition());
           //arrayListOfRelative.add(relative);
       }
        
       
    return arrayListOfRelative;
    }

}
