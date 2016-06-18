/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Memory;
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
public class MemoryDaoImpl implements MemoryDaoInterface {

    /**
     * This method is used to save text memories of patient.
     *
     * @param patientEmail
     * @param relativeEmail
     * @param text
     * @param date
     * @return int
     */
    public int saveMemoryText(String patientEmail, String relativeEmail, String text, String date) {
        System.out.println("patient email>> "+patientEmail+"   "+relativeEmail);
        int patientId = 0;
        int relativeId =0;
        int result = 0;
        int memoriesId = 0;

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        //to select patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);
        System.out.println("patientId in save memory" + patientId);
        //to select relative id
        relativeId = getRelativeId(relativeEmail);
       
        System.out.println("relativeId in save memory" + relativeId);
        try {
            //Query to insert memory into memories table
            String addIntoMemoriestQuery = "insert into alzheimer.memories (patient_id,relative_id) values(?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(addIntoMemoriestQuery);
            preparedStatement.setInt(1, patientId);
            if(relativeId!=0){
            preparedStatement.setInt(2, relativeId);
            }else{
            preparedStatement.setString(2, null);
            }
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            System.out.println("result in save memory=" + result);
            //Query to select memories_id from memories table
            if(relativeId!=0){
            String selectMemoriesId = "select * from alzheimer.memories where patient_id= ? and relative_id= ?";
            
            preparedStatement = connection.prepareStatement(selectMemoriesId);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, relativeId);
                            System.out.println(preparedStatement);
            
            }else{
               String selectMemoriesId = "select * from alzheimer.memories where patient_id= ?";
            
            preparedStatement = connection.prepareStatement(selectMemoriesId);
            preparedStatement.setInt(1, patientId);
                            System.out.println(preparedStatement);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                memoriesId = rs.getInt("memories_id");
             
            }

            rs.close();
            preparedStatement.close();
            //Query to add memory into table memory
            String addIntoMemoryQuery = "insert into alzheimer.memory (memory_id,memory_text,date_time,patient_id)values(?,?,?,?);";
            preparedStatement = connection.prepareStatement(addIntoMemoryQuery);
            preparedStatement.setInt(1, memoriesId);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, patientId);
                        System.out.println(preparedStatement);


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
     * This method is used to get all memories of patient
     * @param patientEmail 
     * @return ArrayList<Memory>
     */
    @Override
    public ArrayList<Memory> getMemories(String patientEmail) {

        int patientId = 0;
        int memoriesId = 0;
        ArrayList<Memory> patientMemories = new ArrayList<>();
        //to get patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();

        try {
            //Query to select all memories from memory table
            String selectMemoriesQuery = "select * from alzheimer.memory where patient_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectMemoriesQuery);
            preparedStatement.setInt(1, patientId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                System.out.println("inside while");
                Memory memory = new Memory();

                memory.setMemoryId(resultSet.getInt("memory_id"));
                memory.setMemoryText(resultSet.getString("memory_text"));
                memory.setImageUrl(constants.Constants.IMAGE_PATH+ resultSet.getString("image_url"));
                memory.setVideo_url(resultSet.getString("video_url"));
                memory.setDateTime(resultSet.getString("date_time"));
                memory.setLongitude(resultSet.getDouble("longitude"));
                memory.setLatitude(resultSet.getDouble("latitude"));
                memory.setAddress(resultSet.getString("address"));
                memory.setCity(resultSet.getString("city"));
                memory.setCountry(resultSet.getString("country"));

                patientMemories.add(memory);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return patientMemories;
    }

    /**
     * This method is used to delete memory of patient
     *
     * @param patientEmail
     * @param memoryId
     * @return int
     */
    @Override
    public int deleteMemory(String patientEmail, int memoryId) {

        int patientId = 0;
        int result = 0;
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        //to get patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);

        try {
            //Query to delete memory from memory table
            String deleteMemoryQuery = "delete from alzheimer.memory where memory_id= ? and patient_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteMemoryQuery);
            preparedStatement.setInt(1, memoryId);
            preparedStatement.setInt(2, patientId);

            preparedStatement.executeUpdate();
            //Query to delete memmory from memories table
            String deleteMemoryFromMemories = "delete from alzheimer.memories where memories_id= ?";
            preparedStatement = connection.prepareStatement(deleteMemoryFromMemories);
            preparedStatement.setInt(1, memoryId);

            result = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * This method is used to edit memories of patient
     *
     * @param patientEmail
     * @param memoryId
     * @param date
     * @return int
     */
    public int updateTextMemory(int memoryId, String date, String text) {
        int patientId = 0;
        int result = 0;
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        
        System.out.println("date-------->>"+date);
        try {
            //Query to update memory in memory table
            String updateMemoryQuery = "update alzheimer.memory SET memory_text= ?,date_time= ? where memory_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateMemoryQuery);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, date);
          //  preparedStatement.setInt(3, patientId);
            preparedStatement.setInt(3, memoryId);
            result = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

    /**
     * This method is used to get relativeId
     * @param relativeEmail
     * @return int
     */
    public int getRelativeId(String relativeEmail) {
        int relativeId = 0;

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        // Query to select relative_id
        String selectIdPatientQuery = "select user_id from alzheimer.users where email= ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(selectIdPatientQuery);

            preparedStatement.setString(1, relativeEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                relativeId = rs.getInt("user_id");
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return relativeId;
    }

}
