package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.AlumnoDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlumnoServlet", value = "/AlumnoServlet")
public class AlumnoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action") == null ?  "mostrar": request.getParameter("action");
        RequestDispatcher view;
        AlumnoDao alumnoDao = new AlumnoDao();
        switch (accion){
            case "mostrar":
                String idUniversidadStr = request.getParameter("id");
                int idUniversidad1 = Integer.parseInt(idUniversidadStr);
                request.setAttribute("listaAlumnosPorUniversidad",alumnoDao.listaAlumnos(idUniversidad1));
                request.setAttribute("vista","mostrar");
                request.setAttribute("idUniversidad",idUniversidadStr);
                view = request.getRequestDispatcher("/alumnos.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idAlumnoEditar = request.getParameter("id");
                alumnoDao.editarCondicion(idAlumnoEditar);
                response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                break;
            case "crear":





                break;
            case "borrar":
                String idAlumnoBorrar = request.getParameter("id");
                alumnoDao.borrarAlumno(idAlumnoBorrar);
                ParticipanteDao participanteDao = new ParticipanteDao();
                participanteDao.borrarParticipante(Integer.parseInt(idAlumnoBorrar));
                response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
