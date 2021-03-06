/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.RelationDaoImpl;
import DAO.Status;
import dto.Relative;
import dto.User;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Doaa
 */

@Path("/relation")
public class RelationWS {
    /**
     * This method used to get all relatives list to patient
     * @param patientEmail
     * @return ArrayList of Relative
     */
    
    @POST
    @Path("/getRelatives")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Relative> getRelatives(@HeaderParam(value = "patientEmail") String patientEmail) {

        RelationDaoImpl relDaqImpl=new RelationDaoImpl();
         ArrayList<Relative> relatives=relDaqImpl.getRelatives(patientEmail);
         System.out.println("size of relatives"+relatives.size());
         return relatives;
    }
    /**
     * This method used to get all patients list to relative
     * @param relativeEmail
     * @return ArrayList of User
     */
    @POST
    @Path("/getPatients")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getPatients(@HeaderParam("relativeEmail") String relativeEmail) {

        RelationDaoImpl relDaqImpl=new RelationDaoImpl();
         ArrayList<User> patients=relDaqImpl.getPatients(relativeEmail);
       System.out.println("size of patients"+patients.size());
         return patients;
    }
    /**
     * This method used to make relative remove patient from his patient list
     * @param patientEmail
     * @param relativeEmail
     * @return Status
     */
    @POST
    @Path("/removePatient")
    @Produces(MediaType.APPLICATION_JSON)
    public Status removePatient(@HeaderParam(value = "patientEmail") String patientEmail,@HeaderParam(value = "relativeEmail") String relativeEmail){
       RelationDaoImpl relDaqImpl=new RelationDaoImpl();
       int result=relDaqImpl.removePatient(patientEmail, relativeEmail);
        boolean flag;
    if(result==1){
        flag=true;
    }else{
        flag=false;
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

   // return result;
    }
    
}
