/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.RequestDaoImpl;
import DAO.Status;
import dto.Relationship;
import dto.Relative;
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
@Path("/request")
public class RequestWS {
    /**
     * This method used to get all requests send to patient
     * @param patientEmail
     * @return ArrayList of relative
     */
    @POST
    @Path("/getRequests")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Relative> getRequests(@HeaderParam("patientemail") String patientEmail) {

        RequestDaoImpl reqDaoImpl = new RequestDaoImpl();
        ArrayList<Relative> relatives = reqDaoImpl.getRequests(patientEmail);
          System.out.println("size of requests"+relatives.size());
        return relatives;
    }
    /**
     * This method used to respond to request send to patient if 
     * he accept or decline request.  
     * @param patientEmail
     * @param relativeEmail
     * @param respond
     * @return Status
     */
    @POST
    @Path("/respondToRequest")
    @Produces(MediaType.APPLICATION_JSON)
    public Status respondToRequest(@HeaderParam("patientEmail") String patientEmail,@HeaderParam("relativeEmail") String relativeEmail,@HeaderParam("status")boolean respond){
    RequestDaoImpl reqDaoImpl=new RequestDaoImpl();
    int result=reqDaoImpl.respondToRequest(patientEmail, relativeEmail, respond);
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

    //return result;
    }
    
    /**
     * This method used to add request of relative to table request in DB
     * @param patientEmail
     * @param relativeEmail
     * @param familyPosition
     * @return Status
     */
    
    @POST
    @Path("/addRequest")
    @Produces(MediaType.APPLICATION_JSON)
    public Status addRequest(@HeaderParam("patientEmail") String patientEmail,@HeaderParam("relativeEmail") String relativeEmail,@HeaderParam("familyPosition")int familyPosition){
    RequestDaoImpl ReqDaoImpl=new RequestDaoImpl();
    int result=ReqDaoImpl.addRequest(relativeEmail, patientEmail,familyPosition);
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

    
//    System.out.println("result="+result);
//    return result;
    }
    
    
}
