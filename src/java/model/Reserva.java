package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 * 
 * @author JUAN JOSE DOLL
 */
public class Reserva {

    /**
     * 
     */
    private int ID_Reserva;
    private String Fecha_Reserva;//Lo establece la bdd
    private String NombreyApellidosHuesped;
    private int ID_Usuario;
    private String Email;
    private int ID_Hotel;
    private String Nombre_hotel;
    private String Fecha_Entrada;
    private String Ubicacion;
    private int Duracion;
    private float PrecioEstancia;
    private String TitularTarjeta;
    private long NumeroTarjeta;
    private String VencimientoTarjeta;
    private int CVV;
    
    /**
     * 
     */

    public Reserva() {
    }
    
    /**
     * 
     * @param ID_Reserva
     * @param Fecha_Reserva
     * @param NombreyApellidosHuesped
     * @param ID_Usuario
     * @param Email
     * @param ID_Hotel
     * @param Nombre_hotel
     * @param Fecha_Entrada
     * @param Ubicacion
     * @param Duracion
     * @param PrecioEstancia
     * @param TitularTarjeta
     * @param NumeroTarjeta
     * @param VencimientoTarjeta
     * @param CVV 
     */

    public Reserva(int ID_Reserva, String Fecha_Reserva, String NombreyApellidosHuesped, int ID_Usuario, String Email, int ID_Hotel, String Nombre_hotel, String Fecha_Entrada, String Ubicacion, int Duracion, float PrecioEstancia, String TitularTarjeta, long NumeroTarjeta, String VencimientoTarjeta, int CVV) {
        this.ID_Reserva = ID_Reserva;
        this.Fecha_Reserva = Fecha_Reserva;
        this.NombreyApellidosHuesped = NombreyApellidosHuesped;
        this.ID_Usuario = ID_Usuario;
        this.Email = Email;
        this.ID_Hotel = ID_Hotel;
        this.Nombre_hotel = Nombre_hotel;
        this.Fecha_Entrada = Fecha_Entrada;
        this.Ubicacion = Ubicacion;
        this.Duracion = Duracion;
        this.PrecioEstancia = PrecioEstancia;
        this.TitularTarjeta = TitularTarjeta;
        this.NumeroTarjeta = NumeroTarjeta;
        this.VencimientoTarjeta = VencimientoTarjeta;
        this.CVV = CVV;
    }

   /**
    * 
    * @return 
    */

    public int getID_Reserva() {
        return ID_Reserva;
    }

    public void setID_Reserva(int ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    public int getID_Hotel() {
        return ID_Hotel;
    }

    public void setID_Hotel(int ID_Hotel) {
        this.ID_Hotel = ID_Hotel;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public String getFecha_Reserva() {
        return Fecha_Reserva;
    }

    public void setFecha_Reserva(String Fecha_Reserva) {
        this.Fecha_Reserva = Fecha_Reserva;
    }

    public String getNombreyApellidosHuesped() {
        return NombreyApellidosHuesped;
    }

    public void setNombreyApellidosHuesped(String NombreyApellidosHuesped) {
        this.NombreyApellidosHuesped = NombreyApellidosHuesped;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNombre_hotel() {
        return Nombre_hotel;
    }

    public void setNombre_hotel(String Nombre_hotel) {
        this.Nombre_hotel = Nombre_hotel;
    }

    public String getFecha_Entrada() {
        return Fecha_Entrada;
    }

    public void setFecha_Entrada(String Fecha_Entrada) {
        this.Fecha_Entrada = Fecha_Entrada;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int Duracion) {
        this.Duracion = Duracion;
    }

    public float getPrecioEstancia() {
        return PrecioEstancia;
    }

    public void setPrecioEstancia(float PrecioEstancia) {
        this.PrecioEstancia = PrecioEstancia;
    }

    public String getTitularTarjeta() {
        return TitularTarjeta;
    }

    public void setTitularTarjeta(String TitularTarjeta) {
        this.TitularTarjeta = TitularTarjeta;
    }

    public long getNumeroTarjeta() {
        return NumeroTarjeta;
    }

    public void setNumeroTarjeta(long NumeroTarjeta) {
        this.NumeroTarjeta = NumeroTarjeta;
    }

    public String getVencimientoTarjeta() {
        return VencimientoTarjeta;
    }

    public void setVencimientoTarjeta(String VencimientoTarjeta) {
        this.VencimientoTarjeta = VencimientoTarjeta;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
    
    

    @Override
    public String toString() {
        return "Reserva{" + "ID_Reserva=" + ID_Reserva + ", ID_Hotel=" + ID_Hotel + ", ID_Usuario=" + ID_Usuario + ", Fecha_Reserva=" + Fecha_Reserva + '}';
    }

    public static String toArrayJSon(ArrayList<Reserva> reservas) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(reservas);

        return resp;
    }

}
