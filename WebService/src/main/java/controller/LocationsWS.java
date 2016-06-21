package controller;

import DAO.LocationDao;
import DAO.Status;
import com.google.gson.Gson;
import static com.sun.jersey.server.impl.model.HttpHelper.produces;
import dto.fcm.CoordinatesData;
import dto.fcm.Data;
import dto.fcm.DataMessageFCM;
import dto.fcm.RequestLocationData;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author atef This class responsible for send location of patient to relatives
 * and controller registering in FCM Service
 */
@Path("/locationManager")
public class LocationsWS {

    Gson gson;

    private final static String API_KEY = "AIzaSyCTxdzNRrrKNJ32OJRmkPFjaUCIY_iZfzM";
    final static int DISCONNET_USER=5050;
    public LocationsWS() {
        gson = new Gson();
    }

    /**
     * This method takes an email and token and register them in database.
     *
     * @param email
     * @param token
     * @return
     */
    @Path("/registerToken")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Status registerToken(@HeaderParam("email") String email, @HeaderParam("token") String token) {

        Status status = new Status();

        LocationDao locationDao = new LocationDao();

        if (locationDao.registerToken(email, token) == 1) {
            status.setStatus(1);
            status.setMessage("Success token Inserted");
            System.out.println("Success Token and email updating >> " + token + " " + email);
        } else {
            status.setStatus(0);
            status.setMessage("failed token Inserted");
            System.out.println("failed Token and email updating >> " + token + " " + email);
        }

        return status;
    }

    /**
     * this method used to send request to the patient to get his location.
     *
     * @param email
     * @return
     */
    @POST
    @Path("/requestLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Status requestLocation(@HeaderParam("patientEmail") String patientEmail, @HeaderParam("relativeEmail") String relativeEmail) {

        Status status = new Status();

        LocationDao locationDao = new LocationDao();

        DataMessageFCM messageFCM = new DataMessageFCM();
        String token = locationDao.getToken(patientEmail);
        RequestLocationData data = locationDao.getRequestSenderData(relativeEmail);

        if (token != null) {

            messageFCM.setTo(token);
            messageFCM.setData(data);
        }

        // send the data to fcm
        String url = "https://fcm.googleapis.com/fcm/send";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("Authorization", "key=" + API_KEY);
        httpost.setHeader("Content-Type", "application/json");

        try {
            System.out.println("Json >>" + gson.toJson(messageFCM));
            httpost.setEntity(new StringEntity(gson.toJson(messageFCM)));
            System.out.println("Entity has been set");
            HttpResponse httpResponse = httpclient.execute(httpost);
            System.out.println("Request sent -- " + httpResponse.getStatusLine().getStatusCode());

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                status.setStatus(1);
                status.setMessage("Success Request -- 200");
            } else {
                status.setStatus(0);
                status.setMessage("Request failed with error -- " + httpResponse.getStatusLine().getStatusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    @POST
    @Path("/broadcastLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Status broadcastLocation(@HeaderParam("relativeEmail") String relativeEmail, @HeaderParam("longitude") String longitude, @HeaderParam("latitude") String latitude) {

        Status status = new Status();
        LocationDao locationDao = new LocationDao();

        // Log
        System.out.println("Longitude & Latitude >>" + longitude + " " + latitude + " Sent to >> " + relativeEmail);

        //Construct data
        CoordinatesData data = new CoordinatesData();
        data.setRelativeEmail(relativeEmail);
        data.setLongitude(longitude);
        data.setLatitude(latitude);
        data.setImageUrl(locationDao.getImageUrl(relativeEmail));

        //construct message 
        DataMessageFCM messageFCM = new DataMessageFCM();
        messageFCM.setData(data);
        messageFCM.setTo(locationDao.getToken(relativeEmail));

        // send the data to fcm
        String url = "https://fcm.googleapis.com/fcm/send";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("Authorization", "key="+API_KEY);
        httpost.setHeader("Content-Type", "application/json");

        try {
            System.out.println("Json >>" + gson.toJson(messageFCM));
            httpost.setEntity(new StringEntity(gson.toJson(messageFCM)));
            
            HttpResponse httpResponse = httpclient.execute(httpost);
            System.out.println("Request sent -- " + httpResponse.getStatusLine().getStatusCode());

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                status.setStatus(1);
                status.setMessage("Success Request -- 200");
            } else {
                status.setStatus(0);
                status.setMessage("Request failed with error -- " + httpResponse.getStatusLine().getStatusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;

    }
    
    
    /**
     * This method used to disconnect user from receiving GPS updates 
     * @param relativeEmail
     * @return 
     */
     @Path("/disconnectUser")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Status disconnectUser(@HeaderParam("relativeEmail") String relativeEmail) {
        
        Status status = new Status();
        
        LocationDao locationDao = new LocationDao();
        String Token = locationDao.getToken(relativeEmail);
        Data data = new Data();
        data.setRequestId(DISCONNET_USER);
        
        // message configured
        DataMessageFCM messageFCM = new DataMessageFCM();
        messageFCM.setTo(Token);
        messageFCM.setData(data);
        
        
        
        // send the data to fcm
        String url = "https://fcm.googleapis.com/fcm/send";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("Authorization", "key="+API_KEY);
        httpost.setHeader("Content-Type", "application/json");

        try {
            System.out.println("Json >>" + gson.toJson(messageFCM));
            httpost.setEntity(new StringEntity(gson.toJson(messageFCM)));
            
            HttpResponse httpResponse = httpclient.execute(httpost);
            System.out.println("Request sent -- " + httpResponse.getStatusLine().getStatusCode());

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                status.setStatus(1);
                status.setMessage("Success Request -- 200");
            } else {
                status.setStatus(0);
                status.setMessage("Request failed with error -- " + httpResponse.getStatusLine().getStatusCode());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
        
        
    }
}
