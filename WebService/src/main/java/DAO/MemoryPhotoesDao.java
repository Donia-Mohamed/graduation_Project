/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.AlzheimerDB;

/**
 *
 * @author Doaa
 */
public class MemoryPhotoesDao {
/**
 * This method is used to save memory of patient that contains image
 * @param imageUrl
 * @param date
 * @param text
 * @param patientEmail
 * @param relativeEmail
 * @return Status
 */
    public Status uploadMemoryImage(String imageUrl, String date, String text, String patientEmail, String relativeEmail) {
        int patientId = 0;
        int relativeId = 0;
        int result = 0;
        int memoriesId = 0;
        boolean flag = false;
        Status status=new Status();
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        //to select patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);
        //to select relative id
        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        relativeId = memoryDaoImpl.getRelativeId(relativeEmail);

        try {
            //Query to insert memory into memories table
            String addIntoMemoriestQuery = "insert into alzheimer.memories (patient_id,relative_id) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addIntoMemoriestQuery);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, relativeId);

            preparedStatement.executeUpdate();

            //Query to select memories_id from memories table
            String selectMemoriesId = "select * from alzheimer.memories where patient_id= ? and relative_id= ?";
            preparedStatement = connection.prepareStatement(selectMemoriesId);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, relativeId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                memoriesId = rs.getInt("memories_id");

            }
            rs.close();
            preparedStatement.close();
            //Query to inset photo memory im memory table
            preparedStatement = connection.prepareStatement("insert into alzheimer.memory(memory_id,date_time,image_url,patient_id,memory_text) values(?,?,?,?,?)");
            preparedStatement.setInt(1, memoriesId);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, imageUrl+memoriesId+".jpg");
            preparedStatement.setInt(4, patientId);
            preparedStatement.setString(5, text);

            preparedStatement.executeUpdate();
            flag = true;
            status.setStatus(memoriesId);
            status.setMessage("successfully");
            // close the connection .. 
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
            status.setStatus(0);
            status.setMessage("fail");
        }
        return status;
    }

  
}
