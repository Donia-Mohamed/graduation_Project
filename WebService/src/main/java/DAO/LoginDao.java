/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.User;
import java.sql.Connection;
import java.sql.ResultSet;
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
    
     public User checkLogin(String email, String password){
         User user=new User();
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
            String dbPassEncrypt=encrypt.encrypt(dbPassword);
            
            if (dbPassEncrypt.equals(passEncrypt)){
                
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
                user.setLongitude(resultSet.getString("longitude"));
                user.setImageUrl("D:\\iti\\GP\\graduation_Project\\images\\"+resultSet.getString("image_url"));
                user.setLatitude(resultSet.getString("latitude"));
            }
        }
         // close the connection .. 
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
        
    }
    
}