package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * Metodo interface
 * @author JUAN JOSE DOLL
 */
public interface IAction {
    public String execute(
            HttpServletRequest request, 
            HttpServletResponse response);
}
