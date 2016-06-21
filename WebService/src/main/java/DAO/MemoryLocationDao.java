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
import model.AlzheimerDB;

/**
 *
 * @author Doaa
 */
public class MemoryLocationDao {
/**
 * This method is used to save memory location of patient
 * @param patientEmail
 * @param relativeEmail
 * @param memory
 * @return int
 */
    public int saveMemoryLocation(String patientEmail, String relativeEmail,Memory memory) {

        int patientId = 0;
        int relativeId = 0;
        int result = 0;
        int memoriesId = 0;

        AlzheimerDB alzheimerDB = new AlzheimerDB();
        Connection connection = alzheimerDB.getConnection();
        //to select patient id
        RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
        patientId = requestDaoImpl.getPatientId(patientEmail);
        System.out.println("patientId in save memory location" + patientId);
        //to select relative id
        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        relativeId = memoryDaoImpl.getRelativeId(relativeEmail);

        System.out.println("relativeId in save memory location " + relativeId);
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
            String addIntoMemoryQuery = "insert into alzheimer.memory (memory_id,date_time,patient_id,longitude,latitude,address,city,country)values(?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(addIntoMemoryQuery);
            preparedStatement.setInt(1, memoriesId);
            preparedStatement.setString(2, memory.getDateTime());
            preparedStatement.setInt(3, patientId);
            preparedStatement.setDouble(4,memory.getLongitude());
            preparedStatement.setDouble(5,memory.getLatitude());
            preparedStatement.setString(6, memory.getAddress());
            preparedStatement.setString(7, memory.getCity());
            preparedStatement.setString(8, memory.getCountry());
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

}
