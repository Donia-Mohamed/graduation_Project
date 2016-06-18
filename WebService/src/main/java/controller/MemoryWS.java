/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MemoryDaoImpl;
import DAO.Status;
import dto.Memory;
import java.util.ArrayList;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Doaa
 */
@Path("/memory")
public class MemoryWS {
/**
 * This method is used to save text memories of patient.
 * @param patientEmail
 * @param relativeEmail
 * @param text
 * @param date
 * @return Status
 */
    @POST
    @Path("/saveMemoryText")
    @Produces(MediaType.APPLICATION_JSON)
    public Status saveMemoryText(@HeaderParam("patientEmail") String patientEmail, @HeaderParam("relativeEmail") String relativeEmail, @HeaderParam("text") String text,
            @HeaderParam("date") String date) {

        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        int result = memoryDaoImpl.saveMemoryText(patientEmail, relativeEmail, text, date);

        boolean flag;
        if (result == 1) {
            flag = true;
        } else {
            flag = false;
        }
        Status status = new Status();

        if (flag) {

            status.setStatus(1);
            status.setMessage("Successfully ");

        } else {

            status.setStatus(0);
            status.setMessage("failed");

        }
        return status;
    }
/**
 * This method is used to get all memories of patient.
 * @param patientEmail
 * @return ArrayList<Memory> 
 */
    @POST
    @Path("/getMemories")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Memory> getMemories(@HeaderParam("patientEmail") String patientEmail) {

        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        ArrayList<Memory> memories = memoryDaoImpl.getMemories(patientEmail);
        System.out.println("size-->" + memories.size());
        return memories;
    }
/**
 * This method is used to delete memory of patient.
 * @param patientEmail
 * @param memoryId
 * @return Status
 */
    @POST
    @Path("/deleteMemory")
    @Produces(MediaType.APPLICATION_JSON)
    public Status deleteMemory(@HeaderParam("patientEmail") String patientEmail, @HeaderParam("memoryId") int memoryId) {
        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        int result = memoryDaoImpl.deleteMemory(patientEmail, memoryId);
        boolean flag;
        if (result == 1) {
            flag = true;
        } else {
            flag = false;
        }
        Status status = new Status();

        if (flag) {

            status.setStatus(1);
            status.setMessage("Successfully ");

        } else {

            status.setStatus(0);
            status.setMessage("failed");

        }
        return status;
    }
/**
 * This method is used to edit memories of patient
 * @param memoryId
 * @param date
 * @param text
 * @return Status
 */
    @POST
    @Path("/updateTextMemory")
    @Produces(MediaType.APPLICATION_JSON)
    public Status updatMemory( @HeaderParam("memoryId") int memoryId, @HeaderParam("date") String date,
            @HeaderParam("text") String text) {
        MemoryDaoImpl memoryDaoImpl = new MemoryDaoImpl();
        int result = memoryDaoImpl.updateTextMemory( memoryId, date,text);
        boolean flag;
        if (result == 1) {
            flag = true;
        } else {
            flag = false;
        }
        Status status = new Status();

        if (flag) {

            status.setStatus(1);
            status.setMessage("Successfully ");

        } else {

            status.setStatus(0);
            status.setMessage("failed");

        }
        return status;

    }
    
}
