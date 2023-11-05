package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Hotel;
import model.HotelDAO;


/**
 * El controller ha derivado hasta aquí la url para que gestione una accion relacionada con 
 * el objeto hotel
 * @author JUAN JOSE DOLL
 */
public class HotelAction implements IAction {

    /**
     * Metodo que distribuira las peticiones que le llegan por request a los diferentes metodos de la clase hotel
     * @param request
     * @param response
     * @return devuelve un json con una coleccion determinada en funcion del metodo que se ha derivado
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND_ALL_ACTIVO":
                cadDestino = findAllActive(request, response);
                break;
            case "ADD":
                add(request, response);
                break;
            case "EDIT":
                edit(request, response);
                break;
            case "DELETE":
                delete(request, response);
                break;
            case "FINDBYCITY":
                cadDestino = findByCity(request, response);
                break;
            case "FINDBYDATE":
                cadDestino = findByDate(request, response);
                break;
            case "SELECCION":
                cadDestino = seleccion(request, response);
                break;
        }
        return cadDestino;
    }

    
    /**
     * Lista todos los hoteles
     * @param request
     * @param response 
     * @return array jason de ofertas de hoteles con sus propiedades
     */
    private String findAll(HttpServletRequest request,
            HttpServletResponse response) {
        HotelDAO hotelDao = new HotelDAO();
        ArrayList<Hotel> hotels = hotelDao.
                findAll(null);
        return Hotel.toArrayJSon(hotels);
    }

    /**
     * Metodo de hoteles filtrados por activos
     * @param request
     * @param response
     * @return array de hoteles filtrados por activos, o sea false, no reservados
     */
    private String findAllActive(HttpServletRequest request,
            HttpServletResponse response) {
        HotelDAO hotelDao = new HotelDAO();

        Hotel hotel = new Hotel();
        hotel.setID_Hotel(0);
        hotel.setNombre_hotel(null);
        hotel.setUbicacion(null);
        hotel.setDuracion(0);
        hotel.setDescripcion(null);
        hotel.setFecha_Entrada(null);
        hotel.setActivo(true);
        ArrayList<Hotel> hotels = hotelDao.
                findAll(hotel);
        return Hotel.toArrayJSon(hotels);
    }

    
    /**
     * Registra un hotel en la base de datos
     * @param request
     * @param response
     */
    private void add(HttpServletRequest request,
            HttpServletResponse response) {
        //Se trae los datos del hotel a insertar
        String nombre = request.getParameter("NOMBRE_HOTEL");
        String ubicacion = request.getParameter("UBICACION");
        String descripcion = request.getParameter("DESCRIPCION");
        String fecha_entrada = request.getParameter("FECHA_ENTRADA");
        String imagen = request.getParameter("URL_IMAGEN");
        int duracion = parseInt(request.getParameter("DURACION"));
        float precio_estancia = parseFloat(request.getParameter("PRECIOESTANCIA"));
        boolean activo = Boolean.parseBoolean(request.getParameter("ACTIVO"));
        //Crea un objeto del bean hotel
        Hotel hotel = new Hotel();
        //Le informa de los atributos
        hotel.setNombre_hotel(nombre);
        hotel.setUbicacion(ubicacion);
        hotel.setDescripcion(descripcion);
        hotel.setFecha_Entrada(fecha_entrada);
        hotel.setURL_Imagen(imagen);
        hotel.setDuracion(duracion);
        hotel.setPrecioEstancia(precio_estancia);
        hotel.setActivo(activo);
        //Creamos un objeto hotelDAO
        HotelDAO hotelDAO = new HotelDAO();
        //Inserta un hotel
        int numeroDeHotelsInsertados = hotelDAO.add(hotel);

        //Si no está vacio te traes el primero del array
        //Si no, informas que es incorrecto
        if (numeroDeHotelsInsertados > 0) {
            try {

                response.sendRedirect("admin/listadoHoteles.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ha habido un problema al registrar el hotel");
        }

    }

    
    /**
     * Listar los hoteles por ubicacion de su oferta
     * @param request
     * @param response
     * @return devuelve un json de hoteles filtrados por una ciudad
     */
    private String findByCity(HttpServletRequest request,
            HttpServletResponse response) {
        HotelDAO hotelDao = new HotelDAO();

        String ubicacion = request.getParameter("UBICACION");

        Hotel hotel = new Hotel();
        hotel.setID_Hotel(0);
        hotel.setNombre_hotel(null);
        hotel.setUbicacion(ubicacion);
        hotel.setDuracion(0);
        hotel.setDescripcion(null);
        hotel.setFecha_Entrada(null);
        hotel.setActivo(false);
        ArrayList<Hotel> hotels = hotelDao.
                findAll(hotel);
        return Hotel.toArrayJSon(hotels);
    }

    /**
     * Listar los hoteles disponibles para una fecha
     * @param request
     * @param response
     * @return devuelve un json de hoteles filtrados por fecha
     */
    
    private String findByDate(HttpServletRequest request,
            HttpServletResponse response) {
        HotelDAO hotelDao = new HotelDAO();
        String fecha = request.getParameter("FECHA_ENTRADA");

        Hotel hotel = new Hotel();
        hotel.setID_Hotel(0);
        hotel.setNombre_hotel(null);
        hotel.setUbicacion(null);
        hotel.setDuracion(0);
        hotel.setDescripcion(null);
        hotel.setFecha_Entrada(fecha);

        ArrayList<Hotel> hotels = hotelDao.
                findAll(hotel);
        return Hotel.toArrayJSon(hotels);
    }

    /**
     * Borra el registro identificado por id
     * @param request
     * @param response
     */
    
    private void delete(HttpServletRequest request, HttpServletResponse response) {

        HotelDAO hotelDao = new HotelDAO();

        int idParaBorrar = parseInt(request.getParameter("ID"));

        int numeroHotelesBorrados = hotelDao.delete(idParaBorrar);

        if (numeroHotelesBorrados != 0) {
            try {
                response.sendRedirect("admin/listadoHoteles.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    
    /**
     * Edita los datos del hotel
     * @param request
     * @param response
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) {
        //Se trae los datos del hotel a editar
        int id = parseInt(request.getParameter("ID_HOTEL"));
        String nombre = request.getParameter("NOMBRE_HOTEL");
        String ubicacion = request.getParameter("UBICACION");
        String descripcion = request.getParameter("DESCRIPCION");
        String fecha_entrada = request.getParameter("FECHA_ENTRADA");
        String imagen = request.getParameter("URL_IMAGEN");
        int duracion = parseInt(request.getParameter("DURACION"));
        float precio_estancia = parseFloat(request.getParameter("PRECIOESTANCIA"));
        boolean activo = Boolean.parseBoolean(request.getParameter("ACTIVO"));

        //Crea un objeto del bean hotel
        Hotel hotel = new Hotel();
        //Le informa de los atributos
        hotel.setID_Hotel(id);
        hotel.setNombre_hotel(nombre);
        hotel.setUbicacion(ubicacion);
        hotel.setDescripcion(descripcion);
        hotel.setFecha_Entrada(fecha_entrada);
        hotel.setURL_Imagen(imagen);
        hotel.setDuracion(duracion);
        hotel.setPrecioEstancia(precio_estancia);
        hotel.setActivo(activo);

        //Creamos un objeto hotelDAO
        HotelDAO hotelDAO = new HotelDAO();
        //Inserta un hotel
        int numeroDeHotelesEditados = hotelDAO.update(hotel);

        if (numeroDeHotelesEditados > 0) {
            try {
                response.sendRedirect("admin/listadoHoteles.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ha habido un problema al actualizar el hotel");
        }
    }

    
    /**
     * Lista el hotel por id
     * @param request
     * @param response
     * @return devuelve json compuesto por un unico elemento
     */
    private String seleccion(HttpServletRequest request,
            HttpServletResponse response) {

        int id = parseInt(request.getParameter("ID_HOTEL"));

        Hotel hotel = new Hotel();

        hotel.setID_Hotel(id);

        HotelDAO hotelDAO = new HotelDAO();

        ArrayList<Hotel> hotels = hotelDAO.
                findAll(hotel);
        return Hotel.toArrayJSon(hotels);
    }

}
