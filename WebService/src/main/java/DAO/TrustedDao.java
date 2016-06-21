/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AlzheimerDB;

/**
 *
 * @author Dono
 */
public class TrustedDao {

    public Status setTrusted(String patientEmail, String relativeEmail) {
         Status status=new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         int patientId=getEmialId(patientEmail);
         int relativeId=getEmialId(relativeEmail);
         if(patientId ==0 || relativeId==0){
             status.setStatus(0);
             status.setMessage("fail");
         }else{
             status=checkPatientId(patientId);
         }
         if(status.getStatus()==1){
             try {
                 PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO trusted (patient_id,relative_id) VALUES (?,?)");
                 
                 preparedStatement.setInt(1, patientId);
                 preparedStatement.setInt(2, relativeId);
                 preparedStatement.executeUpdate();
                 
            preparedStatement.close();
            connection.close();
             } catch (SQLException ex) {
                 Logger.getLogger(TrustedDao.class.getName()).log(Level.SEVERE, null, ex);
             }

         }
         
         return status;
    }
    
    
     private int getEmialId(String email) {
         int id=0;
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         String sql ="select user_id FROM users where email = '"+email+"'";
         System.out.println("get id >> "+sql);
          java.sql.Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
         
             id=resultSet.getInt("user_id");
           
        }
          resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {         
            ex.printStackTrace();
            }
         
         return id;

    }
     
     
     public Status checkPatientId(int patientId){
         Status status= new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
          String sql = "SELECT * FROM trusted where patient_id = "+patientId;
        try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               status.setStatus(0);
             status.setMessage("fail");
            }else{
                status.setStatus(1);
                status.setMessage("success");
            }
            
            
        }catch(SQLException ex){ex.printStackTrace();}
         
         return status;
     }

     // meen el trusted bta3 kol patient
    public Status CheckTrusted(String patientEmail, String relativeEmail) {
         Status status= new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         int patientId =getEmialId(patientEmail);
         int relativeId=getEmialId(relativeEmail);
         Connection connection=alzheimerDB.getConnection();
          String sql = "SELECT * FROM trusted where patient_id = "+patientId+" and relative_id="+relativeId;
          System.out.println("check trusted >> "+sql);
           try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               status.setStatus(1);
             status.setMessage("success");
            }else{
                status.setStatus(0);
                status.setMessage("fail");
            }
            
        }catch(SQLException ex){ex.printStackTrace();}
         
         return status;
        
    }

    public Status removeTrusted(String patientEmail, String relativeEmail) {
        
        int patientId=getEmialId(patientEmail);
        int reltiveId=getEmialId(relativeEmail);
        String sql="delete from alzheimer.trusted where patient_id=? and relative_id=?";
        
        System.out.println("remove trust >> "+sql);
        Status status= new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, reltiveId);
            System.out.println(preparedStatement);

           int result= preparedStatement.executeUpdate();
           if(result==1){
           status.setStatus(result);
           status.setMessage("success");
           }
           else{
               status.setStatus(result);
               status.setMessage("fail");
           }
        } catch (SQLException ex) {
            Logger.getLogger(TrustedDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return status;
        
        
    }

    public String getTrusted(String patientEmail) {
        int patientId=getEmialId(patientEmail);
        int relativeId=0;
        String phoneNum="";
         AlzheimerDB alzheimerDB=new AlzheimerDB();
        Connection connection=alzheimerDB.getConnection();
          String sql = "SELECT * FROM trusted where patient_id = "+patientId;
          System.out.println("get Trusted >> "+sql);
           try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               relativeId=resultSet.getInt("relative_id");
                phoneNum=getPhoneById(relativeId);
            }
             resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException ex){ex.printStackTrace();}
        
        
        return phoneNum;

    }
    
    public String getPhoneById(int relativeId){
        String phoneNum="";
         AlzheimerDB alzheimerDB=new AlzheimerDB();
        Connection connection=alzheimerDB.getConnection();
          String sql = "SELECT phone_num FROM users where user_id = "+relativeId;
          System.out.println("getphonebyId>>  "+sql);
          
           try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               phoneNum=resultSet.getString("phone_num");
               
            }
             resultSet.close();
            statement.close();
            connection.close();
           }catch(SQLException ex){ex.printStackTrace();}
            
        return phoneNum;
    }
    
}
