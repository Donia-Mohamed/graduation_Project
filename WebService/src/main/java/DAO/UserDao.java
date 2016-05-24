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
import java.util.ArrayList;
import model.AlzheimerDB;
import model.DesEncrypter;

/**
 *
 * @author dono
 * select all patient from table user
 * return array list
 */
public class UserDao {
    
    public User getAllPatient(String email){
       AlzheimerDB alzheimerDB=new AlzheimerDB();
         Connection connection=alzheimerDB.getConnection();
          User user=new User();
        String sql = "SELECT * FROM alzheimer.users where type=1 and email ='"+email+"'";
        
        try{
                java.sql.Statement statement=connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
               
               user.setFirstName(resultSet.getString("first_name"));
               user.setLastName(resultSet.getString("last_name"));
               user.setEmail(resultSet.getString("email"));
               user.setImageUrl("D:\\iti\\GP\\graduation_Project\\images\\"+resultSet.getString("image_url"));
                
              

            }
             // close the connection .. 
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception ex){ex.printStackTrace(); }
        return user;
    }
        
        
       
    
    
}
