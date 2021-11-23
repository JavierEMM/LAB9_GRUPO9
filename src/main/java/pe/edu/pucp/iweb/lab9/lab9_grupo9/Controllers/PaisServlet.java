package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.PaisDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaisServlet", value = "/PaisServlet")
public class PaisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaisDao paisDao = new PaisDao();
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        RequestDispatcher view;
        switch (action){
            case "listar":
                request.setAttribute("listaPaises",paisDao.listarPaises());
                view = request.getRequestDispatcher("/paises.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idPais = request.getParameter("id");
                Pais pais = paisDao.obtenerPaisId(Integer.parseInt(idPais));
                request.setAttribute("pais",pais);
                view = request.getRequestDispatcher("/editarPais");
                view.forward(request, response);
                break;
            case "agregar":
                break;
            case "eliminar":
                break;

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaisDao paisDao = new PaisDao();
        String action = request.getParameter("action");
        switch (action){
            case "editar":
                String nombre = request.getParameter("nombrePais");
                int poblacion = Integer.parseInt(request.getParameter("poblacion"));
                String tamano = request.getParameter("tamano");
                int idPais = Integer.parseInt(request.getParameter("idPais"));
                paisDao.editarPais(nombre,poblacion,tamano,idPais);
                response.sendRedirect(request.getContextPath()+"/PaisServlet");
                break;
            case "agregar":
                break;
        }
    }
}
