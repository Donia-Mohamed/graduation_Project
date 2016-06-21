/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        String password = "Ab1234567";
	//String password = "";
	
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
        
        /**
         * @author Safaa.
         * This method used to return connection from database 
         * @return Connection
         */
        public Connection getConnection(){
        
            Connection connection=null;
            try{
               
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = (Connection) DriverManager.getConnection(url + dbname, username, password);
          
            }catch(SQLException ex) {
                ex.printStackTrace();
                        
            }
            
            return connection;
        }
    

        
        
        
    /**
     * this method used to get memory and it's an example for prepared statement.
     * @return 
     */
    /*
    public String selectMemory(){
    
        String value="";
        AlzheimerDB alzheimerDB=new AlzheimerDB();
        String query="SELECT * FROM `memory`";
        // connection 
        Connection connection = alzheimerDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                
                value= resultSet.getString("memory_id");
            }
            
            
            // close the connection .. 
            resultSet.close();
            preparedStatement.close();
            connection.close();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return value;
    }*/
        
    
}
