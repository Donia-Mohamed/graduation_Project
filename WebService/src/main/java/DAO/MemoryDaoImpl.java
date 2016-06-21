/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.Memory;
import dto.Relative;
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
        System.out.println("patient email>> " + patientEmail + "   " + relativeEmail);
        int patientId = 0;
        int relativeId = 0;
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
            if (relativeId != 0) {
                preparedStatement.setInt(2, relativeId);
            } else {
                preparedStatement.setString(2, null);
            }
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            System.out.println("result in save memory=" + result);
            //Query to select memories_id from memories table
            if (relativeId != 0) {
                String selectMemoriesId = "select * from alzheimer.memories where patient_id= ? and relative_id= ?";

                preparedStatement = connection.prepareStatement(selectMemoriesId);
                preparedStatement.setInt(1, patientId);
                preparedStatement.setInt(2, relativeId);
                System.out.println(preparedStatement);

            } else {
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
     * This method is used to get all memories of patient or mutual memories
     * between patient and his relatives in memories represents in his interface
     *
     * @param patientEmail
     * @return ArrayList<Memory>
     */
    @Override
    public ArrayList<Memory> getMemories(String patientEmail) {

        int patientId = 0;
        Memory memory;
        ArrayList<Memory> patientMemories = new ArrayList<>();
        //to get patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();

        try {

            //Queqy to select memories id and relative_id from memories table
            String selectMemoriesId = "select * from alzheimer.memories where patient_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectMemoriesId);
            preparedStatement.setInt(1, patientId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                memory = getMemory(resultSet.getInt("memories_id"));
                
                if (resultSet.getInt("relative_id") != 0) {
                    memory.setRelative(getRelative(resultSet.getInt("relative_id")));
                }
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

        System.out.println("date-------->>" + date);
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
     *
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

    /**
     * This method is used to get data of relative
     *
     * @param relativeId
     * @return Relative
     */
    public Relative getRelative(int relativeId) {

        Relative relative = null;
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        try {
            String getRelativeQuery = "select * from alzheimer.users where user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(getRelativeQuery);
            preparedStatement.setInt(1, relativeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                relative = new Relative();
                relative.setFirstName(resultSet.getString("first_name"));
                relative.setLastName(resultSet.getString("last_name"));
                relative.setEmail(resultSet.getString("email"));
                relative.setImageUrl(resultSet.getString("image_url"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return relative;
    }

    /**
     * This method is used to get memory of patient with specific memory id
     *
     * @param MemoriesId
     * @return Memory
     */
    public Memory getMemory(int MemoriesId) {
        Memory memory = null;
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        try {
            //Query to select all memories from meory table
            String selectMemories = "select * from alzheimer.memory where memory_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectMemories);
            preparedStatement.setInt(1, MemoriesId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                memory = new Memory();

                memory.setMemoryId(resultSet.getInt("memory_id"));
                memory.setMemoryText(resultSet.getString("memory_text"));
                memory.setImageUrl(constants.Constants.IMAGE_PATH + resultSet.getString("image_url"));
                memory.setVideo_url(resultSet.getString("video_url"));
                memory.setDateTime(resultSet.getString("date_time"));
                memory.setLongitude(resultSet.getDouble("longitude"));
                memory.setLatitude(resultSet.getDouble("latitude"));
                memory.setAddress(resultSet.getString("address"));
                memory.setCity(resultSet.getString("city"));
                memory.setCountry(resultSet.getString("country"));

            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return memory;

    }
    /**
     * This method is used to get mutual memories of relative and his patient 
     * @param patientEmail
     * @param relativeEmail
     * @return 
     */
      public ArrayList<Memory> getMutualMemories(String patientEmail,String relativeEmail) {

        int patientId = 0;
        int relativeId= 0;
        Memory memory;
        ArrayList<Memory> patientMemories = new ArrayList<>();
        //to get patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);

        relativeId=getRelativeId(relativeEmail);
        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();

        try {

            //Queqy to select memories id  from memories table
            String selectMemoriesId = "select * from alzheimer.memories where patient_id=? and relative_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectMemoriesId);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, relativeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                memory = getMemory(resultSet.getInt("memories_id"));
                
                
                memory.setRelative(getRelative(relativeId));
              
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

 
}
