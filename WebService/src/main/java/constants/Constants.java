/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author atef
 */
public abstract class  Constants {
    
   private final static String SERVER_IP ="10.0.1.25";
  //  private final static String SERVER_IP ="192.168.1.7";
    private final static String SERVER_PORT="8084";
    public final static String IMAGE_PATH ="http://"+Constants.SERVER_IP+":"+Constants.SERVER_PORT+"/WebService/images/";
}
