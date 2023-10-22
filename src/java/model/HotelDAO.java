package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JUAN JOSE DOLL
 */
public class HotelDAO implements DAO<Hotel, Integer> {

    private final String SQL_FINDALL
            = "SELECT * FROM `hotel` WHERE 1=1 ";
    private final String SQL_ADD
            = "INSERT INTO `hotel`(`Nombre_hotel`, `PrecioEstancia`, `Duracion`, `Ubicacion`, `Descripcion`, `Fecha_Entrada`, `URL_Imagen`, `Activo`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `hotel` WHERE ID_Hotel=";
    private final String SQL_UPDATE = "UPDATE `hotel` SET ";
    private MotorSQL motorSql;

    /**
     *
     */
    public HotelDAO() {
        motorSql = new MotorSQL();
    }

    /**
     *
     * @param bean
     * @return
     */
    @Override
    public ArrayList<Hotel> findAll(Hotel bean) {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {

            motorSql.connect();
            if (bean != null) {
                if (bean.getID_Hotel() != 0) {
                    sql += "AND ID_hotel='" + bean.getID_Hotel() + "'";
                }
                if (bean.getNombre_hotel() != null) {
                    sql += "AND Nombre_hotel='" + bean.getNombre_hotel() + "'";
                }

                if (bean.getUbicacion() != null) {
                    sql += "AND Ubicacion='" + bean.getUbicacion() + "'";
                }

                if (bean.getDuracion() != 0) {
                    sql += "AND DURACION='" + bean.getDuracion() + "'";
                }

                if (bean.getDescripcion() != null) {
                    sql += "AND Descripcion LIKE('%" + bean.getDescripcion() + "%'";
                }

                if (bean.getFecha_Entrada() != null) {
                    sql += "AND FECHA_Entrada='" + bean.getFecha_Entrada() + "'";
                }
                if (bean.isActivo()) {
                     sql += " AND Activo='" + bean.isActivo() + "'";
                }
                
                ////ORDENO POR FECHA
                if (bean.isActivo()) {
                     sql += " ORDER BY FECHA_Entrada";
                }
            }

            /**
             *
             */
            //System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {// TRANSFOMAR LA COLECCIÓN DE BASE DE DATOS A UN ARRAYLIST
                Hotel hotel = new Hotel();
                hotel.setID_Hotel(rs.getInt(1));
                hotel.setNombre_hotel(rs.getString(2));
                hotel.setPrecioEstancia(rs.getFloat(3));
                hotel.setDuracion(rs.getInt(4));
                hotel.setUbicacion(rs.getString(5));
                hotel.setDescripcion(rs.getString(6));
                hotel.setFecha_Entrada(rs.getString(7));
                hotel.setURL_Imagen(rs.getString(8));
                hotel.setActivo(rs.getBoolean(9));
                hotels.add(hotel);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return hotels;
    }

    @Override

    /**
     *
     */
    public int add(Hotel bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre_hotel() + "', "
                    + bean.getPrecioEstancia() + ", "
                    + bean.getDuracion() + ", '"
                    + bean.getUbicacion() + "', '"
                    + bean.getDescripcion() + "', '"
                    + bean.getFecha_Entrada() + "', '"
                    + bean.getURL_Imagen() + "', "
                    + bean.isActivo() +");";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Hotel insertado con exito.");
        }
        return resp;
    }

    /**
     *
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
     *
     * @param bean
     * @return
     */
    @Override
    public int update(Hotel bean) {
        int resp = 0;
        String sql = "";
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getNombre_hotel() != null) {
                    sql += "Nombre_hotel='" + bean.getNombre_hotel() + "'";
                }

                if (bean.getPrecioEstancia() != 0) {
                    sql += ", PrecioEstancia='" + bean.getPrecioEstancia() + "'";
                }

                if (bean.getURL_Imagen() != null) {
                    sql += ", URL_Imagen='" + bean.getURL_Imagen() + "'";
                }

                if (bean.getUbicacion() != null) {
                    sql += ", Ubicacion='" + bean.getUbicacion() + "'";
                }

                if (bean.getDuracion() != 0) {
                    sql += ", Duracion='" + bean.getDuracion() + "'";
                }

                if (bean.getDescripcion() != null) {
                    sql += ", Descripcion='" + bean.getDescripcion() + "'";
                }

                if (bean.getFecha_Entrada() != null) {
                    sql += ", Fecha_Entrada='" + bean.getFecha_Entrada() + "' ,";
                }
                
                if (bean.isActivo()==true||bean.isActivo()==false) {
                    sql += " Activo=" + bean.isActivo() + "";
                }

                sql += " WHERE ID_Hotel=" + bean.getID_Hotel() + ";";
                //System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("hotel actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
    

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /*PRUEBAS UNITARIAS - TEST*/
        HotelDAO hotelDAO = new HotelDAO();
        //Findall - filtra segun campos que no son null o 0
        ArrayList lsthotels
                = hotelDAO.
                        findAll(null);
        System.out.println(lsthotels.toString());

    }

}
