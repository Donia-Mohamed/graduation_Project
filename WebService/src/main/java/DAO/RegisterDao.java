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
import model.AlzheimerDB;
import model.DesEncrypter;

/**
 *
 * @author dono
 * register class ->accept object of user then register data in database 
 * return true if register success
 * return false if register fail
 */
public class RegisterDao {
    
     public boolean registerUser(User user){
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         DesEncrypter encrypt=new DesEncrypter();
        String sql = "select * from users where email = '"+user.getEmail()+"'";
        boolean flag=true;
        try{
                java.sql.Statement statement=connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                flag = false;
            }else{
                String passEncrypt=encrypt.encrypt(user.getPassword());
                PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO users (first_name,last_name,birthday,gender,email,phone_num,home_num,country,city,address,type,password,longitude,image_url,latitude) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setDate(3, user.getBirthday());
                preparedStatement.setInt(4, user.getGender());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getPhoneNumber());
                preparedStatement.setString(7, user.getHomeNumber());
                preparedStatement.setString(8, user.getCountry());
                preparedStatement.setString(9, user.getCity());
                preparedStatement.setString(10, user.getAddress());
                preparedStatement.setInt(11, user.getType());
                preparedStatement.setString(12, passEncrypt);
                preparedStatement.setDouble(13, user.getLongitude());
                preparedStatement.setString(14, user.getImageUrl());
                preparedStatement.setDouble(15, user.getLatitude());
               
                
                    
                    preparedStatement.executeUpdate();
                    flag=true;

            }
             // close the connection .. 
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception ex){ex.printStackTrace(); flag=false;}
        return flag;
    }
     
      public boolean registerUserImage(String email , String imageUrl ){
         AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
         boolean flag=false;
        try{
            
            
                PreparedStatement preparedStatement=connection.prepareStatement("UPDATE users SET image_url = ? WHERE email = ?");
               
                preparedStatement.setString(1, imageUrl);
                preparedStatement.setString(2, email);
                
                    preparedStatement.executeUpdate();
                    flag=true;

             // close the connection .. 
            preparedStatement.close();
            connection.close();
        }catch(Exception ex){ex.printStackTrace(); flag=false;}
        return flag;
    }
     
    
}
