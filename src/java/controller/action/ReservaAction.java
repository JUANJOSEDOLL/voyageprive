package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
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
import model.Reserva;
import model.ReservaDAO;

//El controller ha derivado hasta aquí la url para que gestione una accion relacionada con 
//el objeto reserva
/**
 * Metodo que distribuira las peticiones que le llegan por request a los diferentes metodos de la clase reserva
 * @author JUAN JOSE DOLL
 */
public class ReservaAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND_ALL_USER":
                cadDestino=findAllUserBookings(request, response);
                break;
            case "ADD":
                add(request, response);
                break;
            case "ADDADMIN":
                adadmin(request, response);
                break;
            case "EDIT":
                edit(request, response);
                break;
            case "DELETE":
                delete(request, response);
                break;

        }
        return cadDestino;
    }

    
    /**
     * Lista todas las reservas,a utilizar por el administrador
     * @param request
     * @param response
     * @return
     */
    private String findAll(HttpServletRequest request,
            HttpServletResponse response) {
        ReservaDAO reservaDao = new ReservaDAO();
        ArrayList<Reserva> reservas = reservaDao.
                findAll(null);
        return Reserva.toArrayJSon(reservas);
    }

    
    /**
     * Lista las reservas del usuario
     * @param request
     * @param response
     * @return devuelve un json de reservas del usuario
     */
    private String findAllUserBookings(HttpServletRequest request,
            HttpServletResponse response) {
        
        int id = parseInt(request.getParameter("ID_USER"));
        
        //Crea un objeto del bean reserva
        Reserva reserva = new Reserva();
        //Le informa de los atributos
        reserva.setID_Reserva(0);
        reserva.setID_Hotel(0);
        reserva.setID_Usuario(id);
        reserva.setFecha_Reserva(null);
        reserva.setNombreyApellidosHuesped(null);
        reserva.setEmail(null);
        reserva.setNombre_hotel(null);
        reserva.setFecha_Entrada(null);
        reserva.setUbicacion(null);
        reserva.setDuracion(0);
        reserva.setPrecioEstancia(0);
        reserva.setTitularTarjeta(null);
        reserva.setNumeroTarjeta(0);
        reserva.setVencimientoTarjeta(null);
        reserva.setCVV(0);

        //Creamos un objeto reservaDAO
        ReservaDAO reservaDAO = new ReservaDAO();
        
        
           
        ArrayList<Reserva> reservas = reservaDAO.findAll(reserva);
        return Reserva.toArrayJSon(reservas);
    }
    
    /**
     * Registra un reserva
     * @param request
     * @param response
     */
    private void add(HttpServletRequest request, HttpServletResponse response) {
        //Recoge los datos necesarios para identificar la reserva:
        //La ID de la oferta del reserva y la ID del cliente, con la fecha en
        //la que realiza la reserva
        //Para obtener una situación de las reservas del cliente, efectuaremos una consulta filtrada.
        //Paralelamente se debe de marcar como reservada la oferta.

        //this.ID_Reserva = ID_Reserva;
        String fecha_Reserva = request.getParameter("FECHA_RESERVA");
        String nombreyApellidosHuesped = request.getParameter("NOMBREYAPELLIDOSHUESPED");
        int iD_Usuario = parseInt(request.getParameter("ID_USUARIO"));
        String email = request.getParameter("EMAIL");
        int iD_Hotel = parseInt(request.getParameter("IDH"));
        String nombre_hotel = request.getParameter("NOMBRE");
        String fecha_Entrada = request.getParameter("FECHA");
        String ubicacion = request.getParameter("UBICACION");
        int duracion = parseInt(request.getParameter("DURACION"));
        float precio_estancia = parseFloat(request.getParameter("PRECIO"));
        String titularTarjeta = request.getParameter("TITULARTARJETA");
        long numeroTarjeta = parseLong(request.getParameter("NUMEROTARJETA"));
        String vencimientoTarjeta = request.getParameter("VENCIMIENTOTARJETA");
        int cVV = parseInt(request.getParameter("CVV"));

        //Crea un objeto del bean reserva
        Reserva reserva = new Reserva();

        //lo llenamos con sus atributos
        reserva.setFecha_Reserva(fecha_Reserva);
        reserva.setNombreyApellidosHuesped(nombreyApellidosHuesped);
        reserva.setID_Usuario(iD_Usuario);
        reserva.setEmail(email);
        reserva.setID_Hotel(iD_Hotel);
        reserva.setNombre_hotel(nombre_hotel);
        reserva.setFecha_Entrada(fecha_Entrada);
        reserva.setUbicacion(ubicacion);
        reserva.setDuracion(duracion);
        reserva.setPrecioEstancia(precio_estancia);
        reserva.setTitularTarjeta(titularTarjeta);
        reserva.setNumeroTarjeta(numeroTarjeta);
        reserva.setVencimientoTarjeta(vencimientoTarjeta);
        reserva.setCVV(cVV);

        //Creamos un objeto reservaDAO
        ReservaDAO reservaDAO = new ReservaDAO();
        //Inserta un reserva
        int numeroDeReservasInsertadas = reservaDAO.add(reserva);
        
        //Actualiza la reserva a true (reservada = true)
        Hotel hotel =new Hotel();
        hotel.setID_Hotel(iD_Hotel);
        hotel.setActivo(true);
        HotelDAO hotelDAO=new HotelDAO();
        int hotelReservado=hotelDAO.update(hotel);
        
        

        //Si no está vacio te traes el primero del array
        //Si no, informas que es incorrecto
        if (numeroDeReservasInsertadas > 0) {
            try {
                //response.sendRedirect("http://localhost:8080/VoyagePrive/user/index.html");
                response.sendRedirect("user/index.html");
            } catch (IOException ex) {
                Logger.getLogger(ReservaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ha habido un problema al registrar el reserva");
        }
    }

    
    /**
     * Borra el registro identificado por id
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {

        ReservaDAO reservaDao = new ReservaDAO();

        int idParaBorrar = parseInt(request.getParameter("ID"));

        int numeroReservasBorradas = reservaDao.delete(idParaBorrar);

        if (numeroReservasBorradas != 0) {
            try {
                response.sendRedirect("admin/listadoReservas.html");
            } catch (IOException ex) {
                Logger.getLogger(ReservaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    
    /**
     * Edita los datos de la reserva
     * @param request
     * @param response
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) {
        //Se trae los datos de la reserva a editar
        int id_reserva = parseInt(request.getParameter("ID_RESERVA"));
        int id_hotel = parseInt(request.getParameter("ID_HOTEL"));
        int id_cliente = parseInt(request.getParameter("ID_USUARIO"));
        int duracion = parseInt(request.getParameter("DURACION"));
        long numeroTarjeta = parseInt(request.getParameter("NUMEROTARJETA"));
        int cVV = parseInt(request.getParameter("CVV"));
        float precio_estancia = parseInt(request.getParameter("PRECIOESTANCIA"));
        String fecha_entrada = request.getParameter("FECHA_ENTRADA");
        String fecha_reserva = request.getParameter("FECHA_RESERVA");
        String nombreyApellidosHuesped = request.getParameter("NOMBREYAPELLIDOSHUESPED");
        String email = request.getParameter("EMAIL");
        String nombre_hotel = request.getParameter("NOMBRE_HOTEL");
        String ubicacion = request.getParameter("UBICACION");
        String titularTarjeta = request.getParameter("TITULARTARJETA");
        String vencimientoTarjeta = request.getParameter("VENCIMIENTOTARJETA");

        //Crea un objeto del bean reserva
        Reserva reserva = new Reserva();
        //Le informa de los atributos
        reserva.setID_Reserva(id_reserva);
        reserva.setID_Hotel(id_hotel);
        reserva.setID_Usuario(id_cliente);
        reserva.setFecha_Reserva(fecha_reserva);
        reserva.setNombreyApellidosHuesped(nombreyApellidosHuesped);
        reserva.setEmail(email);
        reserva.setNombre_hotel(nombre_hotel);
        reserva.setFecha_Entrada(fecha_entrada);
        reserva.setUbicacion(ubicacion);
        reserva.setDuracion(duracion);
        reserva.setPrecioEstancia(precio_estancia);
        reserva.setTitularTarjeta(titularTarjeta);
        reserva.setNumeroTarjeta(numeroTarjeta);
        reserva.setVencimientoTarjeta(vencimientoTarjeta);
        reserva.setCVV(cVV);

        //Creamos un objeto reservaDAO
        ReservaDAO reservaDAO = new ReservaDAO();
        //Actualiza una reserva
        int numeroDeReservasEditadas = reservaDAO.update(reserva);

        if (numeroDeReservasEditadas > 0) {
            try {
                response.sendRedirect("admin/listadoReservas.html");
            } catch (IOException ex) {
                Logger.getLogger(ReservaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Ha habido un problema al actualizar el reserva");
        }
    }

    private void adadmin(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
