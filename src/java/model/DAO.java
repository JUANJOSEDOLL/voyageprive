/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 * Clase interface para el bean, que represente la clase modelo correspondiente
 * @author JUAN JOSE DOLL
 * @param <E>
 * @param <I> 
 */
public interface DAO<E,I> {
    public int add(E bean);
    public int delete(Integer e);
    public int update(E bean);
    public ArrayList<E> findAll(E bean);
}
