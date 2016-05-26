/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import DAO.RegisterDao;
import DAO.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import dto.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author dono
 *  this class web service register call method register from database 
 * return json -> success or failed 
 */
@Path("/regist")
public class RegisterWS {
    
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Status regist (@HeaderParam("data")String Data){
       
        Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        User user=gson.fromJson(Data, User.class);
        RegisterDao registerDB=new RegisterDao();
        boolean flag=registerDB.registerUser(user);
         Status status = new Status();
        
                    if (flag) {

                        status.setStatus(1);
                       status.setMessage("Successfully register");

                    } else {

                        status.setStatus(0);
                       status.setMessage("register failed");

                    }
                   return status;
    }
    
    
        @POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
        @Produces(MediaType.APPLICATION_JSON)
	public Status uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail,
                @FormDataParam("email") String email) {
            
                RegisterDao registerDB=new RegisterDao();
		String uploadedFileLocation = "E:\\"+email+".jpg";
                //String uploadedFileLocation = "E:\\"+email;
                
                
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
                
                boolean flag= registerDB.registerUserImage(email,email+".jpg");
		
                String output = "File uploaded to : " + uploadedFileLocation;

                Status status = new Status();
        
                    if (flag) {

                        status.setStatus(1);
                       status.setMessage("Successfully file upload");

                    } else {

                        status.setStatus(0);
                       status.setMessage(" upload file failed");

                    }
                   return status;


	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}  
    
}
