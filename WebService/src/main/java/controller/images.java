/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author atef
 */
@Path("/images")
public class images {
    
    private static final String FILE_PATH = "E:\\atef@yahoo.com.jpg";

//	@GET
//	//@Path("/{imagePath}")
//	@Produces("text/html")
//	public Response getFile() {
//
//		File file = new File(FILE_PATH);
//
//		ResponseBuilder response = Response.ok((Object) file);
//		//response.header("Content-Disposition",
//		//	"attachment; filename=image_from_server.png");
//		return response.build();
//
//	}
        
        
        

//    @GET
//    @Path("getImage/{imageId}")
//    @Produces("image/*")
//    public Response getImage(String imageId) {
//
//        Image image = getImage(imageId);
//
//        if (image != null) {
//
//            // resize the image to fit the GUI's image frame
//            image = resize((BufferedImage) image, 300, 300);
//
//            final ByteArrayOutputStream out = new ByteArrayOutputStream();
//            try {
//                ImageIO.write((BufferedImage) image, "jpg", out);
//
//                final byte[] imgData = out.toByteArray();
//
//                final InputStream bigInputStream = 
//                      new ByteArrayInputStream(imgData);
//
//                return Response.ok(bigInputStream).
//                     cacheControl(getCacheControl(true)).build();
//            }
//            catch (final IOException e) {
//                return Response.noContent().build();
//            }
//        }
//
//        return Response.noContent().build();
//
//    }


    
    
    
}
