package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/** 
 * Clase que carga en el servidor imagenes
 * @author JUAN JOSE DOLL
 */


@MultipartConfig
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
    
    /**   
     * 
     */
	private static final long serialVersionUID = 1L;
	
        /** 
         * 
         */
        
	private String pathFiles = "C:\\feedback_javaEE\\VoyagePrive\\web\\images";//ruta absoluta
        //private String pathFiles = "\\web\\images";//ruta relativa
	private File uploads = new File(pathFiles);
	private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};

        /** 
         * 
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException 
         */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		add(request, response);
	}
	/** 
         * Metodo que cambia imagen
         * @param req
         * @param res
         * @throws IOException 
         */
	private void add(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			
			String nombreImagen=req.getParameter("cambiaImagenF");
                        
			Part part = req.getPart("file");
			
			if(part == null) {
				System.out.println("No ha seleccionado un archivo");
				return;
			}
			
			if(isExtension(part.getSubmittedFileName(), extens)) {
				saveFile(part, uploads, nombreImagen);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		res.sendRedirect("/VoyagePrive/admin/listadoHoteles.html");
	}
	
        /** 
         * Metodo que guarda archivo de imagen en el servidor
         * @param part
         * @param pathUploads
         * @param nombreImagen 
         */
	private void saveFile(Part part, File pathUploads, String nombreImagen) {
		String pathAbsolute = "";
		
		try {
			
			Path path = Paths.get(part.getSubmittedFileName());
			//String fileName = path.getFileName().toString();
                        String fileName = nombreImagen;
			InputStream input = part.getInputStream();
			
			if(input != null) {
				File file = new File(pathUploads, fileName);
				pathAbsolute = file.getAbsolutePath();
				Files.copy(input, file.toPath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return pathAbsolute;
	}
	
        /** 
         * 
         * @param fileName
         * @param extensions
         * @return 
         */
	private boolean isExtension(String fileName, String[] extensions) {
		for(String et : extensions) {
			if(fileName.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		
		return false;
	}
}
