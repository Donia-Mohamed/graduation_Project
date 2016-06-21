/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.LoginDao;
import DAO.Status;
import dto.RequestBodyLogin;
import dto.User;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dono
 * this class web service login call method login from database 
 * return json -> success or failed 
 */
@Path("/login")
public class LoginWS {

    
  //  @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Status login(@HeaderParam("email") String email,@HeaderParam("password") String password){
        LoginDao loginDB=new LoginDao();
        System.out.println ("email: "+email+"  password  "+password);
        
       
        Status status=loginDB.checkLogin(email,password);
        
        return status;
    }
    
    @Path("/mac")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Status updateMacAddress(@HeaderParam("email") String email,@HeaderParam("macAddress") String macAddress){
         LoginDao loginDB=new LoginDao();
        System.out.println ("email: "+email+"  mac  "+macAddress);
       
        Status status=loginDB.updateMac(email,macAddress);
        
        return status;
        
        
    }
     
}
