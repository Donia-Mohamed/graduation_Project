/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.LoginDao;
import DAO.Status;
import DAO.UserDao;
import dto.User;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dono
 * return array list of all patient
 */
@Path("/patient")
public class PatientsWS {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Status AllPatitents(@HeaderParam("email")String email){
        UserDao userdb=new UserDao();
        Status status=userdb.getAllPatient(email);
        System.out.println("status >>"+status.getMessage());
  //      System.out.println("status >>"+status.getUser().getFirstName());
        return status;
    }
    
}
