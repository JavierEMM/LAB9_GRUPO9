package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.AlumnoDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlumnoServlet", value = "/AlumnoServlet")
public class AlumnoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action");
        RequestDispatcher view;
        AlumnoDao alumnoDao = new AlumnoDao();
        switch (accion){
            case "mostrar":
                String idUniversidadStr = request.getParameter("id");
                int idUniversidad1 = Integer.parseInt(idUniversidadStr);
                request.setAttribute("listaAlumnosPorUniversidad",alumnoDao.listaAlumnos(idUniversidad1));
                request.setAttribute("vista","mostrar");
                view = request.getRequestDispatcher("/alumnos.jsp");
                view.forward(request, response);
                break;
            case "editar":




                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
