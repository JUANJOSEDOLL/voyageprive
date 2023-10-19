package model;

import controller.action.UsuarioAction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JUAN JOSE DOLL
 */
public class UserDAO implements DAO<Usuario, Integer>{
    private static final String SQL_INSERT
            = "INSERT INTO `usuario`(`email`,`password`) VALUES ";
    private static final String SQL_UPDATE
            = "UPDATE `usuario` SET ";
    private static final String SQL_DELETE
            = "DELETE FROM `usuario` WHERE `id`=";
    private static final String SQL_FIND_ALL
            = "SELECT * FROM `usuario` WHERE 1=1 ";
    /**
     * 
     */
    MotorSQL motorSql;
    
    public UserDAO() {
        motorSql = new MotorSQL();
    }
/**
 * 
 * @param entidad
 * @return 
 */
    @Override
    public int add(Usuario entidad) {
        String sql = SQL_INSERT
                + "('" + entidad.getEmail() + "',"
                + " '" + entidad.getPassword() + "')";
     
            motorSql.connect();
            int resp = motorSql.execute(sql);
            motorSql.disconnect();
      

        return resp;

     }
    /**
     * 
     * @param e
     * @return 
     */
    @Override
    public int delete(Integer e) {
        this.motorSql.connect();
        String sql = SQL_DELETE + e;
                
        int resp = this.motorSql.execute(sql);
        this.motorSql.disconnect();
        return resp;
    }

    /**
     * 
     * @param bean
     * @return 
     */
    @Override
    public int update(Usuario bean) {
        this.motorSql.connect();
        String sql = SQL_UPDATE;
        if (bean.getEmail()!=null) {
            sql+="email='"+bean.getEmail()+"',";
        }
        if (bean.getPassword()!=null) {
            sql+="password='"+bean.getPassword()+"',";
        }
        if (sql.endsWith(",")) {
            sql=sql.substring(0,sql.length()-1);
        }
        sql+="WHERE id="+bean.getId();
        int resp = this.motorSql.execute(sql);
        this.motorSql.disconnect();
        return resp;
    }
/**
 * Puede devolver un String??
 * @param bean
 * @return 
 */
    
//Metodo que llama a todos los registros de usuarios, los devuelve en un arraylist
    
 @Override
    public ArrayList<Usuario> findAll(Usuario bean) {
        ArrayList<Usuario> lstUsuarios = new ArrayList<>();
        String sqlCabecera = SQL_FIND_ALL;
        try {
        
        motorSql.connect();
        String sqlCuerpo = "";
        if (bean!=null) {
            if (bean.getId()!=0) {
                sqlCuerpo = " AND id="+bean.getId();
            }
            if (bean.getEmail()!=null && !bean.getEmail().equals("")) {
                sqlCuerpo += " AND email LIKE '"+bean.getEmail()+"'";
            }
            if (bean.getPassword()!=null && !bean.getPassword().equals("")) {
                sqlCuerpo += " AND password='"+bean.getPassword()+"'";
            }
            
             if (bean.getRol()!=null && !bean.getRol().equals("")) {
                sqlCuerpo += " AND rol='"+bean.getPassword()+"'";
            }
           
        }
       
        String sqlFinal = sqlCabecera + sqlCuerpo;
        ResultSet rs = motorSql.executeQuery(sqlFinal);
        
        
            //rs.beforeFirst();
            while (rs.next()) {                
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));//No acceder por nombre de columna
                usuario.setEmail(rs.getString(2));//No acceder por nombre de columna
                usuario.setPassword(rs.getString(3));//No acceder por nombre de columna
                usuario.setRol(rs.getString(4));
                lstUsuarios.add(usuario);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             this.motorSql.disconnect();
        }

        return lstUsuarios;
    }  
    
    
 

}
