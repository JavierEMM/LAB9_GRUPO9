package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.DTO.ParticipanteDTO;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.AlumnoDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AlumnoServlet", value = "/AlumnoServlet")
public class AlumnoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action") != null ?  request.getParameter("action") : "mostrar";
        RequestDispatcher view;
        AlumnoDao alumnoDao = new AlumnoDao();
        ParticipanteDao participanteDao = new ParticipanteDao();
        switch (accion) {
            case "mostrar":
                String idUniversidadStr = request.getParameter("id");
                int idUniversidad1 = Integer.parseInt(idUniversidadStr);
                request.setAttribute("listaAlumnosPorUniversidad", alumnoDao.listaAlumnos(idUniversidad1));
                request.setAttribute("vista", "mostrar");
                request.setAttribute("idUniversidad", idUniversidadStr);
                view = request.getRequestDispatcher("/alumnos.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idParticipanteStr = request.getParameter("id");
                int idParticipante = Integer.parseInt(idParticipanteStr);
                Alumno alumnito = alumnoDao.obtenerAlumnoPorId(idParticipante);
                request.setAttribute("Alumno",alumnito);
                request.setAttribute("vista","editar");
                view = request.getRequestDispatcher("/editaralumnos.jsp");
                view.forward(request, response);
                break;
            case "crear":
                String idUniversidadStr1 = request.getParameter("idUniversidad");
                ArrayList<Participante> listaParticipanteNoUniversidad = new ArrayList<>();
                listaParticipanteNoUniversidad = alumnoDao.listaParticipantesCrear(Integer.parseInt(idUniversidadStr1));
                request.setAttribute("listaParticipantesNoUniversidad",listaParticipanteNoUniversidad);
                request.setAttribute("vista","crear");
                request.setAttribute("idUniversidad",idUniversidadStr1);
                view = request.getRequestDispatcher("/editaralumnos.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                String idAlumnoEditar = request.getParameter("id");
                String condicion = request.getParameter("condicion");
                System.out.println(condicion);
                switch (condicion){
                    case "normal":
                        alumnoDao.editarCondicion(idAlumnoEditar);
                        response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                        break;
                    case "Normal":
                        alumnoDao.editarCondicion(idAlumnoEditar);
                        response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                        break;
                    case "eliminado":
                        alumnoDao.borrarAlumno(idAlumnoEditar);
                        participanteDao.borrarParticipante(Integer.parseInt(idAlumnoEditar));
                        response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                        break;
                    case "Eliminado":
                        alumnoDao.borrarAlumno(idAlumnoEditar);
                        participanteDao.borrarParticipante(Integer.parseInt(idAlumnoEditar));
                        response.sendRedirect(request.getContextPath()+"/UniversidadServlet");
                        break;
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action");

        ParticipanteDao participanteDao = new ParticipanteDao();
        switch (accion) {
            case "crear":
                String idUniversidadStr = request.getParameter("idUniversidad");
                int idUniversidad = Integer.parseInt(idUniversidadStr);
                String idParticipanteStr = request.getParameter("idParticipante");
                int idParticipante = Integer.parseInt(idParticipanteStr);
                String codigoParticipanteStr = request.getParameter("codigoParticipante");
                int codigoParticipante = Integer.parseInt(codigoParticipanteStr);
                String promedioParticipanteStr = request.getParameter("promedioParticipante");
                double promedioParticipante = Double.parseDouble(promedioParticipanteStr);
                AlumnoDao alumnoDao = new AlumnoDao();
                alumnoDao.insertAlumno(idParticipante,codigoParticipante,promedioParticipante,idUniversidad);
                response.sendRedirect(request.getContextPath()+ "/UniversidadServlet");
                break;

            case "editar":
                String idAlumnoStr = request.getParameter("id");
                int idAlumno = Integer.parseInt(idAlumnoStr);
                String codigoAlumnoStr = request.getParameter("codigoAlumno");
                int codigoAlumno = Integer.parseInt(codigoAlumnoStr);
                String nombreAlumno = request.getParameter("nombreAlumno");
                String apellidoAlumno = request.getParameter("apellidoAlumno");
                String edadAlumnoStr = request.getParameter("edadAlumno");
                int edadAlumno = Integer.parseInt(edadAlumnoStr);
                String condicionAlumno = request.getParameter("condicionAlumno");
                String promedioAlumnoStr = request.getParameter("promedioAlumno");
                double promedioAlumno = Double.parseDouble(promedioAlumnoStr);

                Alumno alumnito = new Alumno(nombreAlumno,apellidoAlumno,edadAlumno,idAlumno,codigoAlumno,promedioAlumno,condicionAlumno);
                AlumnoDao alumnoDao1 = new AlumnoDao();
                alumnoDao1.updateAlumno(alumnito);
                response.sendRedirect(request.getContextPath()+ "/UniversidadServlet");
                break;


        }
    }
}
