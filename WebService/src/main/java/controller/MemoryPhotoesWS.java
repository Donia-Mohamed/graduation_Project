/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.MemoryPhotoesDao;
import DAO.Status;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Doaa
 */
@Path("/memoryPhotoesWS")
public class MemoryPhotoesWS {

    ServletContext context;

    public MemoryPhotoesWS(@Context ServletContext context) {

        this.context = context;
    }

    /**
     * This method is used to upload image memories of patient
     * @param uploadedInputStream
     * @param fileDetail
     * @param date
     * @param text
     * @param PatientEmail
     * @param relativeEmail
     * @return Status
     */
    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Status uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("date") String date,
            @FormDataParam("text") String text,
            @FormDataParam("patientEmail") String PatientEmail,
            @FormDataParam("relativeEmail") String relativeEmail) {

        MemoryPhotoesDao memoryPhotoesDao = new MemoryPhotoesDao();

        // get the path from the context .. 
        String path = context.getRealPath("/images");

        
        

        Status status=memoryPhotoesDao.uploadMemoryImage(PatientEmail,date,text, PatientEmail, relativeEmail);
        
        if(status.getStatus()!=0){
        String uploadedFileLocation = path + "\\" +PatientEmail+status.getStatus()+".jpg";

        // save it
        writeToFile(uploadedInputStream, uploadedFileLocation);
        String output = "File uploaded to : " + uploadedFileLocation;
        System.out.println(output);
        System.out.println("message of status upload photo memory "+status.getMessage());
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
