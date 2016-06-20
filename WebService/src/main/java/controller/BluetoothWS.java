/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.BluetoothDao;
import DAO.Status;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Dono
 */
@Path("/Bluetooth")
public class BluetoothWS {
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Status bluetooth(@HeaderParam("patientEmail") String patientEmail, @HeaderParam("macAddress") String macAddress) {
        
        BluetoothDao bluetoothDao=new BluetoothDao();
        Status status=bluetoothDao.checkMacAddress(patientEmail,macAddress);
        
        return status;
        
    }
    
}
