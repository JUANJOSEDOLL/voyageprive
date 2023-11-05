package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 * Clase que representa las ofertas que se muestran en el sitio
 * @author JUAN JOSE DOLL
 */

public class Hotel {
    
    private String Nombre_hotel, Descripcion, Fecha_Entrada, URL_Imagen, Ubicacion;
    private int duracion, ID_Hotel;
    private float PrecioEstancia;
    private boolean Activo; 
   /**
     * Constructor de la clase
     * @param Nombre_hotel
     * @param Descripcion
     * @param Fecha_Entrada
     * @param URL_Imagen
     * @param duracion
     * @param ID_Hotel
     * @param PrecioEstancia
     * @param Activo
     */
    public Hotel(String Nombre_hotel, String Descripcion, String Fecha_Entrada, 
            String URL_Imagen, int duracion, int ID_Hotel, float PrecioEstancia, boolean Activo) {
        this.ID_Hotel = ID_Hotel;
        this.Nombre_hotel = Nombre_hotel;
        this.Ubicacion = Ubicacion;
        this.Descripcion = Descripcion;
        this.Fecha_Entrada = Fecha_Entrada;
        this.URL_Imagen = URL_Imagen;
        this.duracion = duracion;
        this.PrecioEstancia = PrecioEstancia;
        this.Activo = Activo;
    }
    
    /**
     * Constructor vacio
     */
    public Hotel() {
    }
   
    
    public String getNombre_hotel() {
        return Nombre_hotel;
    }

    public void setNombre_hotel(String Nombre_hotel) {
        this.Nombre_hotel = Nombre_hotel;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFecha_Entrada() {
        return Fecha_Entrada;
    }

    public void setFecha_Entrada(String Fecha_Entrada) {
        this.Fecha_Entrada = Fecha_Entrada;
    }

    public String getURL_Imagen() {
        return URL_Imagen;
    }

    public void setURL_Imagen(String URL_Imagen) {
        this.URL_Imagen = URL_Imagen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getID_Hotel() {
        return ID_Hotel;
    }

    public void setID_Hotel(int ID_Hotel) {
        this.ID_Hotel = ID_Hotel;
    }

    public float getPrecioEstancia() {
        return PrecioEstancia;
    }

    public void setPrecioEstancia(float PrecioEstancia) {   
        this.PrecioEstancia = PrecioEstancia;
    }
    
    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }

 
    

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "hotel{" + "Nombre_hotel=" + Nombre_hotel + ", Descripcion=" + Descripcion + ", Fecha_Entrada=" + Fecha_Entrada + ", duracion=" + duracion + ", ID_Hotel=" + ID_Hotel + ", PrecioEstancia=" + PrecioEstancia + '}';
    }
    
    /**
     * Metodo que construye un json
     * @param hotel
     * @return 
     */
    public static String toCadena(Hotel hotel) {
        return "hotel{" + 
                "Nombre_hotel=" + hotel.getNombre_hotel() + ", "
                + " Descripcion=" + hotel.getDescripcion() + ", "
                + "Fecha_Entrada=" + hotel.getFecha_Entrada() + ", "
                + "duracion=" + hotel.getDuracion() + ", "
                + ", ID_Hotel=" + hotel.getID_Hotel() + ", PrecioEstancia=" + hotel.getPrecioEstancia() + '}';
    }
    

    /**
     * Metodo que construye un json empleando api gson
     * @param hotels
     * @return devuelve un json pasandole un array de objetos hotel
     */
     public static String toArrayJSon(ArrayList<Hotel> hotels) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String resp = gson.toJson(hotels);
            
            return resp;
    }
    
    
    

}
