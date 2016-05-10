/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.RequestDaoImpl;
import dto.Relationship;
import dto.Relative;
import java.util.ArrayList;
import javax.ws.rs.GET;
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
    @GET
    @Path("/{patientemail}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Relative> getRequests(@PathParam(value = "patientemail") String patientEmail) {

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
     * @return int
     */
    @GET
    @Path("/respondToRequest/{patientEmail}/{relativeEmail}/{respond}")
    @Produces(MediaType.APPLICATION_JSON)
    public int respondToRequest(@PathParam(value = "patientEmail") String patientEmail,@PathParam(value = "relativeEmail") String relativeEmail,@PathParam(value = "respond")boolean respond){
    RequestDaoImpl reqDaoImpl=new RequestDaoImpl();
    int result=reqDaoImpl.respondToRequest(patientEmail, relativeEmail, respond);
    return result;
    }
    
    /**
     * This method used to add request of relative to table request in DB
     * @param patientEmail
     * @param relativeEmail
     * @param familyPosition
     * @return int
     */
    
    @GET
    @Path("/addRequest/{patientEmail}/{relativeEmail}/{familyPosition}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addRequest(@PathParam(value = "patientEmail") String patientEmail,@PathParam(value = "relativeEmail") String relativeEmail,@PathParam(value = "familyPosition")int familyPosition){
    RequestDaoImpl ReqDaoImpl=new RequestDaoImpl();
    int result=ReqDaoImpl.addRequest(relativeEmail, patientEmail,familyPosition);
    System.out.println("result="+result);
    return result;
    }
    
    
}
