/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.LoginDao;
import dto.User;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dono
 * this class web service login call method login from database 
 * return json -> success or failed 
 */
@Path("/login")
public class LoginWS {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(String email,String password){
        LoginDao loginDB=new LoginDao();
        User user=loginDB.checkLogin(email, password);
        
        return user;
    }
    
}
