package controller.action;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.HotelDAO;
import model.UserDAO;
import model.Usuario;

/**
 *
 * @author JUAN JOSE DOLL
 */
public class UsuarioAction implements IAction {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagDestino = "";
        String action = request.getParameter("ACTION");

        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                pagDestino = findAll(request, response);
                break;
            case "ADD":
                add(request, response);
                break;
            case "ADDADMIN":
                addAdmin(request, response);
                break;
            case "EDIT":
                edit(request, response);
                break;
            case "DELETE":
                delete(request, response);
                break;
            case "LOGIN"://escoge login y lo manda al metodo login
                login(request, response);
                break;
            case "LOGOUT"://escoge login y lo manda al metodo logout
                logout(request, response);
                break;
        }
        return pagDestino;
    }

    //Muestra todos los usuarios
    /**
     *
     * @param request
     * @param response
     * @return
     */
    private String findAll(HttpServletRequest request,
            HttpServletResponse response) {
        UserDAO usuarioDao = new UserDAO();
        ArrayList<Usuario> usuarios = usuarioDao.
                findAll(null);
        return Usuario.toArrayJSon(usuarios);
    }

    //Comprueba que el usuario este registrado
    /**
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request,
            HttpServletResponse response) {
        //Se crea una sesion
        HttpSession sesion = request.getSession();
        //Se trae los datos de email y contrasenya
        String email = request.getParameter("EMAIL");
        String pass = request.getParameter("PASS");
        //Crea un objeto del bean usuario
        Usuario usuario = new Usuario();
        //Le informa el atributo email
        usuario.setEmail(email);
        //Le informa del atributo password
        usuario.setPassword(pass);
        //Ahora crea un usuarioDAO para poder comunicarse con la BDD
        UserDAO usuarioDAO = new UserDAO();
        //Llama a la bdd y se trae un array de usuarios filtrado por el objeto usuario creado
        ArrayList<Usuario> loginUsuario = usuarioDAO.
                findAll(usuario);
        //Si no está vacio o nulo te traes el primero del array

        if (loginUsuario.isEmpty()) {
            try {
                response.sendRedirect("login.html");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //Si no, informas que es incorrecto
            int id_usuario = loginUsuario.get(0).getId();
            //Obtiene el rol del usuario de la bdd
            String rolUser = loginUsuario.get(0).getRol();

            if (!loginUsuario.isEmpty() && rolUser.equals("admin")) {

                try {
                    sesion.setAttribute("rol_usuario", rolUser);
                    sesion.setAttribute("email_usuario", email);
                    sesion.setAttribute("id_usuario", id_usuario);
                    response.sendRedirect("admin/listadoHoteles.html");
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (!loginUsuario.isEmpty() && rolUser.equals("user")) {
                try {
                    sesion.setAttribute("rol_usuario", rolUser);
                    sesion.setAttribute("email_usuario", email);
                    sesion.setAttribute("id_usuario", id_usuario);
                    response.sendRedirect("user/index.html?IDUSER=" + id_usuario);
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    response.sendRedirect("login.html");
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Registro de un usuario DESDE USER
    /**
     *
     * @param request
     * @param response
     */
    private void add(HttpServletRequest request,
            HttpServletResponse response) {
        //Se trae los datos de email y contrasenya
        String email = request.getParameter("EMAIL");
        String pass = request.getParameter("PASS");
        //Crea un objeto del bean usuario
        Usuario usuario = new Usuario();
        //Le informa el atributo email
        usuario.setEmail(email);
        //Le informa del atributo password
        usuario.setPassword(pass);
        //Ahora crea un usuarioDAO para poder comunicarse con la BDD
        UserDAO usuarioDAO = new UserDAO();
        //Inserta un usuario
        int numeroDeUsuariosInsertados = usuarioDAO.add(usuario);

        //Si no está vacio te traes el primero del array
        //TODO
        if (numeroDeUsuariosInsertados != 0) {

            try {
                response.sendRedirect("user/index.html");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                response.sendRedirect("login.html");
            } catch (IOException ex) {
                Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    //Registro de un usuario DESDE ADMIN
    /**
     *
     * @param request
     * @param response
     */
    private void addAdmin(HttpServletRequest request,
            HttpServletResponse response) {
        //Se trae los datos de email y contrasenya
        String email = request.getParameter("EMAIL");
        String pass = request.getParameter("PASS");
        //Crea un objeto del bean usuario
        Usuario usuario = new Usuario();
        //Le informa el atributo email
        usuario.setEmail(email);
        //Le informa del atributo password
        usuario.setPassword(pass);
        //Ahora crea un usuarioDAO para poder comunicarse con la BDD
        UserDAO usuarioDAO = new UserDAO();
        //Inserta un usuario
        int numeroUsers = usuarioDAO.add(usuario);

        if (numeroUsers != 0) {
            try {
                response.sendRedirect("admin/listadoUsuarios.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     * @param request
     * @param response
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) {

        //Se trae los datos de email y contrasenya
        int id = parseInt(request.getParameter("ID"));
        String email = request.getParameter("EMAIL");
        String pass = request.getParameter("PASS");
        //Crea un objeto del bean usuario
        Usuario usuario = new Usuario();
        //Le informa el atributo id
        usuario.setId(id);
        //Le informa el atributo email
        usuario.setEmail(email);
        //Le informa del atributo password
        usuario.setPassword(pass);
        //Ahora crea un usuarioDAO para poder comunicarse con la BDD
        UserDAO usuarioDAO = new UserDAO();

        int numeroUsers = usuarioDAO.update(usuario);

        if (numeroUsers != 0) {
            try {
                response.sendRedirect("admin/listadoUsuarios.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //Borra el registro identificadi por id
    /**
     *
     * @param request
     * @param response
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {

        UserDAO userDao = new UserDAO();

        int idParaBorrar = parseInt(request.getParameter("ID"));

        int numeroUsersBorrados = userDao.delete(idParaBorrar);

        if (numeroUsersBorrados != 0) {
            try {
                response.sendRedirect("admin/listadoUsuarios.html");
            } catch (IOException ex) {
                Logger.getLogger(HotelAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        HttpSession sesion = request.getSession();
        sesion.invalidate();

        try {
            response.sendRedirect("/VoyagePrive/login.html");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
