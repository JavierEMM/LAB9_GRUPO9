package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

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
        request.setAttribute("listaPaises",paisDao.listarPaises());
        RequestDispatcher view = request.getRequestDispatcher("/paises.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
