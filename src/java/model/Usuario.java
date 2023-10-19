/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 * 
 * @author JUAN JOSE DOLL
 */
public class Usuario {
    
    // ID EMAIL PASSWORD
    
    private int id;
    private String email;
    private String password;
    private String rol;

    public Usuario() {
    }

/**
 * 
 * @param id
 * @param email
 * @param password
 * @param rol 
 */
    public Usuario(int id, String email, String password, String rol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    

    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }
/**
 * 
 * @param id 
 */
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
/**
 * 
 * @return 
 */
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", password=" + password + '}';
    }
    /**
     * 
     * @param usuarios
     * @return 
     */
    public static String toArrayJSon(ArrayList<Usuario> usuarios) {
            GsonBuilder builder = new GsonBuilder(); 
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String resp = gson.toJson(usuarios);
            
            return resp;
    }

}
