/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import constants.Constants;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.AlzheimerDB;
import model.DesEncrypter;

/**
 *
 * @author dono
 * function check login from database using  email and password
 * return true if login -> success
 * return false if login -> fail
 */
public class LoginDao {
    
     public Status checkLogin(String email, String password){
         User user=new User();
         Status status=new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         DesEncrypter encrypt=new DesEncrypter();
         String sql = "SELECT * FROM users where email = '"+email+"'";
        try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            String dbPassword = resultSet.getString("password");
           
            String passEncrypt=encrypt.encrypt(password);
           // String dbPassEncrypt=encrypt.encrypt(dbPassword);
            
            if (dbPassword.equals(passEncrypt)){
                System.out.println("true password");
                
                user.setFirstName(resultSet.getString("first_name"));
               user.setLastName(resultSet.getString("last_name"));
               user.setBirthday(resultSet.getDate("birthday"));
               user.setGender(resultSet.getInt("gender"));
               user.setEmail(email);
               user.setPhoneNumber(resultSet.getString("phone_num"));
               user.setHomeNumber(resultSet.getString("home_num"));
               user.setCountry(resultSet.getString("country"));
               user.setCity(resultSet.getString("city"));
               user.setAddress(resultSet.getString("address"));
               user.setType(resultSet.getInt("type"));
                user.setPassword(password);
                user.setLongitude(resultSet.getDouble("longitude"));
                user.setImageUrl(Constants.IMAGE_PATH+resultSet.getString("image_url"));
                user.setLatitude(resultSet.getDouble("latitude"));
                
              
            
                status.setStatus(1);
                status.setMessage("success");
                status.setUser(user);
               
               
                
            }else{
                System.out.println("fail pass");
                status.setStatus(0);
                status.setMessage("fail");
                
            }
        }
         // close the connection .. 
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
        
    }

    public Status updateMac(String email,String macAddress) {
         Status status=new Status();
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
          String updateMacAddress = "update users SET mac_Address = ? where email = ?";
          PreparedStatement ps;
         try {
             ps = connection.prepareStatement(updateMacAddress);
             ps.setString(1, macAddress);
             ps.setString(2, email);
             ps.executeUpdate();
                status.setStatus(1);
                status.setMessage("success");
            ps.close();
            connection.close();
         } catch (SQLException ex) {
                status.setStatus(0);
                status.setMessage("fail");   
         }
         
         return status;
         
    }

                
        
        
    
    
}
