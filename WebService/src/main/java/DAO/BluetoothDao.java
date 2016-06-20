/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Relative;
import dto.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AlzheimerDB;
import model.DesEncrypter;

/**
 *
 * @author Dono
 */
public class BluetoothDao {

    public Status checkMacAddress(String patientEmail, String macAddress) {
        //check mac address if exist return object relative 
         Status status=new Status();
         Relative relativeData=new Relative();
         int patientId=0;
         int positionId=0;
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         String sql1 = "SELECT * FROM users where mac_Address = '"+macAddress+"'";
          System.out.println("get relative mac address>> "+sql1);
          java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);
        if(resultSet.next()){
            relativeData.setUserId(resultSet.getInt("user_id"));
            relativeData.setFirstName(resultSet.getString("first_name"));
            relativeData.setLastName(resultSet.getString("last_name"));
            relativeData.setImageUrl(resultSet.getString("image_url"));
            status.setStatus(1);
            status.setMessage("succes");
            status.setRelative(relativeData);
        }else{
            status.setStatus(0);
            status.setMessage("fail");
        }
          resultSet.close();
            statement.close();
            connection.close();
        if(status.getStatus()==1){
            // get patient id
            patientId=getPatientId(patientEmail);
            if(patientId==0){
                status.setStatus(0);
                status.setMessage("fail");
            }
            //check el relation exist or not and return relation
            positionId=checkRelation(patientId,relativeData.getUserId());
            relativeData.setRelationshipPosition(positionId);
            if(positionId==0){
                status.setStatus(0);
                status.setMessage("fail");
            }
             
     
        }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  
        return status;
    }

    private int getPatientId(String patientEmail) {
         int patientId=0;
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         String sql2 ="select user_id FROM users where email = '"+patientEmail+"'";
         System.out.println("get id patient>> "+sql2);
          java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql2);
        if(resultSet.next()){
         
             patientId=resultSet.getInt("user_id");
           
        }
          resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {         
            ex.printStackTrace();
            }
         
         return patientId;

    }

    private int checkRelation(int patientId, int relativeId) {
        int positionId=0;
      String sql3 = "select family_position_id from relationship where relative_id="+relativeId+" and patient_id="+patientId;
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         System.out.println("get id relation>> "+sql3);
          java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql3);
        if(resultSet.next()){
             positionId=resultSet.getInt("family_position_id");
           
        }
          resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
                    ex.printStackTrace();
        }
         

      return positionId;
    }
    
}
