package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que contiene las sentencias SQL a trasladar a la base de datos y devolver las respuestas al frontend
 * @author JUAN JOSE DOLL
 */
public class ReservaDAO implements DAO<Reserva, Integer> {

    private final String SQL_FINDALL
            = "SELECT * FROM `reserva` WHERE 1=1 ";
    private final String SQL_ADD
            = "INSERT INTO `reserva` (`ID_Hotel`, `ID_Usuario`,"
            + " `Fecha_Reserva`, `NombreyApellidosHuesped`, `PrecioEstancia`,"
            + " `Duracion`, `NumeroTarjeta`, `CVV`, `Email`, `Nombre_hotel`,"
            + " `Fecha_Entrada`, `Ubicacion`, `TitularTarjeta`,"
            + " `VencimientoTarjeta`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `reserva` WHERE ID_Reserva=";
    private final String SQL_UPDATE = "UPDATE `reserva` SET ";
    private MotorSQL motorSql;

    /**
     *
     */
    public ReservaDAO() {
        motorSql = new MotorSQL();
    }

    /**
     * Metodo que devuelve un array de la sentencia sql
     * @param bean
     * @return
     */
    @Override
    public ArrayList<Reserva> findAll(Reserva bean) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String sqlCabecera = SQL_FINDALL;
//        String sql="";
        try {

            motorSql.connect();
            String sqlCuerpo = "";
            if (bean != null) {
                if (bean.getID_Reserva()!= 0) {
                    sqlCuerpo = "AND ID_Reserva='" + bean.getID_Reserva() + "'";
                }

                if (bean.getFecha_Reserva()!= null) {
                    sqlCuerpo += " AND Fecha_Reserva='" + bean.getFecha_Reserva() + "'";
                }
                if (bean.getNombreyApellidosHuesped()!= null) {
                    sqlCuerpo += " AND NombreyApellidosHuesped='" + bean.getNombreyApellidosHuesped() + "'";
                }
                if (bean.getID_Usuario()!= 0) {
                    sqlCuerpo += " AND ID_Usuario='" + bean.getID_Usuario() + "'";
                }
                
                 if (bean.getEmail()!= null) {
                    sqlCuerpo += " AND Email='" + bean.getEmail() + "'";
                }
                 
                if (bean.getID_Hotel()!= 0) {
                    sqlCuerpo += " AND ID_Hotel='" + bean.getID_Hotel() + "'";
                }
                if (bean.getNombre_hotel()!= null) {
                    sqlCuerpo += " AND Nombre_hotel='" + bean.getNombre_hotel() + "'";
                }
                if (bean.getFecha_Entrada() != null) {
                    sqlCuerpo += " AND Fecha_Entrada='" + bean.getFecha_Entrada() + "'";
                }
                if (bean.getUbicacion() != null) {
                    sqlCuerpo += " AND Ubicacion='" + bean.getUbicacion() + "'";
                }
                if (bean.getDuracion() != 0) {
                    sqlCuerpo += " AND Duracion='" + bean.getDuracion() + "'";
                }
                if (bean.getPrecioEstancia() != 0) {
                    sqlCuerpo += " AND PrecioEstancia='" + bean.getPrecioEstancia() + "'";
                }
                
                if (bean.getTitularTarjeta() != null) {
                    sqlCuerpo += " AND TitularTarjeta='" + bean.getTitularTarjeta() + "'";
                }
                
                if (bean.getNumeroTarjeta() != 0) {
                    sqlCuerpo += " AND NumeroTarjeta='" + bean.getNumeroTarjeta() + "'";
                }
                
                if (bean.getVencimientoTarjeta() != null) {
                    sqlCuerpo += " AND VencimientoTarjeta='" + bean.getVencimientoTarjeta() + "'";
                }
                
                if (bean.getCVV() != 0) {
                    sqlCuerpo += " AND CVV='" + bean.getCVV() + "'";
                }

            }

             String sqlFinal = sqlCabecera + sqlCuerpo;
            ResultSet rs = motorSql.executeQuery(sqlFinal);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Reserva reserva = new Reserva();
                reserva.setID_Reserva(rs.getInt(1));
                reserva.setID_Hotel(rs.getInt(2));
                reserva.setID_Usuario(rs.getInt(3));
                reserva.setFecha_Reserva(rs.getString(4));
                
                reserva.setEmail(rs.getString(10));
                reserva.setNombreyApellidosHuesped(rs.getString(5));
                reserva.setNombre_hotel(rs.getString(11));
                reserva.setFecha_Entrada(rs.getString(12));
                reserva.setUbicacion(rs.getString(13));
                reserva.setDuracion(rs.getInt(7));
                reserva.setPrecioEstancia(rs.getFloat(6));
                
                reserva.setTitularTarjeta(rs.getString(14));
                reserva.setNumeroTarjeta(rs.getLong(8));
                reserva.setVencimientoTarjeta(rs.getString(15));
                reserva.setCVV(rs.getInt(9));
                

                reservas.add(reserva);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return reservas;
    }

    @Override
    /**
     * Anyade una reserva en la base de datos
     */
    public int add(Reserva bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getID_Hotel() + "', '"
                    + bean.getID_Usuario() + "', '"
                    + bean.getFecha_Reserva() + "', '"
                    + bean.getNombreyApellidosHuesped() + "', '"
                    + bean.getPrecioEstancia() + "', '"
                    + bean.getDuracion() + "', '"
                    + bean.getNumeroTarjeta() + "', '"
                    + bean.getCVV() + "', '"
                    + bean.getEmail() + "', '"
                    + bean.getNombre_hotel() + "', '"
                    + bean.getFecha_Entrada() + "', '"
                    + bean.getUbicacion() + "', '"
                    + bean.getTitularTarjeta() + "', '"
                    + bean.getVencimientoTarjeta() + "');";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Reserva insertada con exito.");
        }
        return resp;
    }

    /**
     * Borra una reserva
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        int resp = 0;
        motorSql.connect();
        try {
            String sql = SQL_DELETE + id;
            //desactivo la restriccion de claves foráneas para borrado
            motorSql.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSql.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            motorSql.execute("SET FOREIGN_KEY_CHECKS=1;");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    /**
     * Actualiza una reserva
     * @param bean
     * @return devuelve un entero
     */
    @Override
    public int update(Reserva bean) {
        int resp = 0;
        String sql = "";
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;

                if (bean.getID_Hotel() != 0) {
                    sql += "ID_Hotel='" + bean.getID_Hotel() + "'";
                }

                if (bean.getID_Usuario() != 0) {
                    sql += ", ID_Usuario='" + bean.getID_Usuario() + "'";
                }

                if (bean.getFecha_Reserva() != null) {
                    sql += ", Fecha_Reserva='" + bean.getFecha_Reserva() + "'";
                }

                sql += " WHERE ID_Reserva=" + bean.getID_Reserva() + ";";
                //System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("reserva actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }

    /**
     * Metodo para pruebas unitarias
     *
     * @param args
     */
    public static void main(String[] args) {
        /*PRUEBAS UNITARIAS - TEST*/
        ReservaDAO reservaDAO = new ReservaDAO();
        //Findall - filtra segun campos que no son null o 0
        ArrayList lstreservas
                = reservaDAO.
                        findAll(null);
        System.out.println(lstreservas.toString());

    }

}
