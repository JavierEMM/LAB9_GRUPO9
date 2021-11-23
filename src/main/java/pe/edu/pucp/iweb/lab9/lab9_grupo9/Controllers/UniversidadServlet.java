package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.PaisDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.UniversidadDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UniversidadServlet", value = "/UniversidadServlet")
public class UniversidadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PaisDao paisDao = new PaisDao();
        UniversidadDao universidadDao = new UniversidadDao();
        ArrayList<Pais> listaPaises = paisDao.listarPaises();

        String accion = request.getParameter("action") != null ? request.getParameter("action") : "listar";
        String idUniversidad = request.getParameter("id") != null ? request.getParameter("id") : "0";
        RequestDispatcher view;

        switch (accion){
            case "listar":
                String msg = request.getParameter("msg") != null ? request.getParameter("msg") : "";
                try {
                    System.out.println(universidadDao.listarUniversidades().get(1).getContinente().getNombre());
                    request.setAttribute("listaUniversidades",universidadDao.listarUniversidades());

                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath()+"/UniversidadServlet?msg=sql");
                }
                view = request.getRequestDispatcher("/universidades.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaPaises",paisDao.listarPaises());
                request.setAttribute("vista","crear");
                view = request.getRequestDispatcher("/editaruniversidades.jsp");
                view.forward(request, response);
                break;
            case "editar":
                Universidad universidad = null;
                request.setAttribute("vista","editar");
                try {
                    universidad = universidadDao.obtenerUniversidadPorId(Integer.parseInt(idUniversidad));
                    request.setAttribute("universidad",universidad);
                    request.setAttribute("listaPaises",listaPaises);
                    view = request.getRequestDispatcher("/editaruniversidades.jsp");
                    view.forward(request, response);
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath()+"/UniversidadServlet?msg=sql");
                }
                break;
            //BORRAR LOGICO O BORRAR BASE DE DATOS
            case "borrar":
                String idParticipante2 = request.getParameter("id");
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action") != null ? request.getParameter("action") : "listar";
        String ranking,foto,pais,nombreUniversidad,idUniversidadStr;
        UniversidadDao universidadDao = new UniversidadDao();
        switch (accion){
            case "listar":
                response.sendRedirect(request.getContextPath() + "/UniversidadServlet");
                break;
            case "crear":
                nombreUniversidad = request.getParameter("nombreUniversidad")  != "" ? request.getParameter("nombreUniversidad") : "";
                ranking = request.getParameter("ranking") != "" ? request.getParameter("ranking") : "0";
                foto = request.getParameter("Foto") != "" ? request.getParameter("Foto") : "";
                pais = request.getParameter("idPais");
                try {
                    if(nombreUniversidad.equalsIgnoreCase("")){
                        response.sendRedirect(request.getContextPath() +"/UniversidadServlet?msg=nombrevacio");
                    }else if(ranking.equalsIgnoreCase("0")) {
                        response.sendRedirect(request.getContextPath() + "/UniversidadServlet?msg=rankingvacio");
                    } else{
                        if (universidadDao.validarNombre(nombreUniversidad)){
                            universidadDao.crearUniversidad(nombreUniversidad,Integer.parseInt(ranking),foto, Integer.parseInt(pais));
                            response.sendRedirect(request.getContextPath() +"/UniversidadServlet");
                        }else{
                            response.sendRedirect(request.getContextPath() + "/UniversidadServlet?msg=nombreinvalido");
                        }
                    }
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath() +"/UniversidadServlet?msg=sql");
                }
                break;

            case "editar":
                idUniversidadStr = request.getParameter("id");
                nombreUniversidad = request.getParameter("nombreUniversidad")  != "" ? request.getParameter("nombreUniversidad") : "";
                ranking = request.getParameter("ranking")  != "" ? request.getParameter("ranking") : "0";
                foto = request.getParameter("Foto") != "" ? request.getParameter("Foto") : "";
                pais = request.getParameter("idPais");
                try {
                    if(nombreUniversidad.equalsIgnoreCase("")){
                        response.sendRedirect(request.getContextPath() +"/UniversidadServlet?msg=nombrevacio");
                    }else if(ranking.equalsIgnoreCase("0")) {
                        response.sendRedirect(request.getContextPath() + "/UniversidadServlet?msg=rankingvacio");
                    } else{
                        if (universidadDao.validarNombre(nombreUniversidad)){
                            universidadDao.editarUniversidad(Integer.parseInt(idUniversidadStr), nombreUniversidad,Integer.parseInt(ranking),foto,Integer.parseInt(pais));
                            response.sendRedirect(request.getContextPath() +"/UniversidadServlet");
                        }else{
                            response.sendRedirect(request.getContextPath() + "/UniversidadServlet?msg=nombreinvalido");
                        }
                    }
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath() +"/UniversidadServlet?msg=sql");
                }
        }

    }
}
