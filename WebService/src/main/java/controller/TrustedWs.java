/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Status;
import DAO.TrustedDao;
import dto.Relative;
import java.util.ArrayList;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Dono
 */
@Path("/trust")
public class TrustedWs {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Status setTrusted(@HeaderParam("patientemail") String patientEmail,@HeaderParam("relativeemail") String relativeEmail) {
    
    TrustedDao trustedDao=new TrustedDao();
    Status status=trustedDao.setTrusted(patientEmail,relativeEmail);
    
    return status;
    
    } 
    
//    @POST
//    @Path("/check")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Status getEmailTrusted(@HeaderParam("patientemail") String patientEmail,@HeaderParam("relativeemail") String relativeEmail) {
//    
//    TrustedDao trustedDao=new TrustedDao();
//    Status status=trustedDao.CheckTrusted(patientEmail,relativeEmail);
//    
//    return status;
//    
//    }
    
    
     @POST
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Status removeTrusted(@HeaderParam("patientemail") String patientEmail,@HeaderParam("relativeemail") String relativeEmail) {
    
    TrustedDao trustedDao=new TrustedDao();
    Status status=trustedDao.removeTrusted(patientEmail,relativeEmail);
    
    return status;
    
    }
    
    
     @POST
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Relative getTrusted(@HeaderParam("patientemail") String patientEmail) {
    
    TrustedDao trustedDao=new TrustedDao();
    Relative relative=trustedDao.getTrusted(patientEmail);
    
    return relative;
    
    }
    
    
}
