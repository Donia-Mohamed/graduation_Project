/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dono
 */
public class AlzheimerDB {
    
        String url = "jdbc:mysql://localhost:3306/";
	String Driver = "com.mysql.jdbc.Driver";
	String dbname = "alzheimer";
	String username = "root";
	String password = "";
	Connection connection;
	
//    public AlzheimerDB(){
//            try {
//               
//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//                connection = (Connection) DriverManager.getConnection(url + dbname, username, password);
//          
//            } catch (SQLException ex) {
//                Logger.getLogger(AlzheimerDB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    
//    }
    
    
    
    public void selectUser(){
        String sql = "select * from users";
        //currentUser=ui;
        try{
            java.sql.Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
