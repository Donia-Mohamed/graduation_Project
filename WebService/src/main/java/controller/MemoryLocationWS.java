/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MemoryLocationDao;
import DAO.Status;
import com.google.gson.Gson;
import dto.Memory;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Doaa
 */
@Path("/memoryLocation")
public class MemoryLocationWS {
/**
 * This method is used to save memory location of patient
 * @param patientEmail
 * @param relativeEmail
 * @param memory
 * @return Status
 */
    @POST
    @Path("/saveMemoryLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Status saveMemoryLocation(@HeaderParam("patientEmail") String patientEmail,
            @HeaderParam("relativeEmail") String relativeEmail, 
            @HeaderParam("memory") String memoryString) {

        MemoryLocationDao memoryLocationDao = new MemoryLocationDao();
        Gson gson = new Gson();
        Memory memory = gson.fromJson(memoryString, Memory.class);
        int result = memoryLocationDao.saveMemoryLocation(patientEmail, relativeEmail,memory);

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
