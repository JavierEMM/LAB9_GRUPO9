package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.PaisDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.UniversidadDao;

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
        String accion = request.getParameter("action") != null ? request.getParameter("action") : "listar";
        UniversidadDao universidadDao = new UniversidadDao();
        String idUniversidad = request.getParameter("id") != null ? request.getParameter("id") : "0";
        RequestDispatcher view;
        switch (accion){
            case "listar":
                try {
                    request.setAttribute("listaUniversidades",universidadDao.listarUniversidades());
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath()+"/UniversidadServlet?msg=sql");
                }
                view = request.getRequestDispatcher("/universidades.jsp");
                view.forward(request, response);
                break;
            case "crear":
                break;
            case "editar":
                Universidad universidad = null;
                request.setAttribute("vista","editar");
                try {
                    if(idUniversidad.equalsIgnoreCase("0")){
                        response.sendRedirect(request.getContextPath()+"/UniversidadServlet?msg=nulo");
                    }else{
                        universidad = universidadDao.obtenerUniversidadPorId(Integer.parseInt(idUniversidad));
                        request.setAttribute("universidad",universidad);
                        PaisDao paisDao = new PaisDao();
                        ArrayList<Pais> listaPaises = paisDao.listarPaises();
                        request.setAttribute("listaPaises",listaPaises);
                        view = request.getRequestDispatcher("/editaruniversidades.jsp");
                        view.forward(request, response);
                    }
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

        UniversidadDao universidadDao = new UniversidadDao();
        switch (accion){
            case "listar":


                break;

            case "crear":



                break;

            case "editar":
                String idUniversidadStr = request.getParameter("id");
                String nombreUniversidad = request.getParameter("nombreUniversidad")  != null ? request.getParameter("nombreUniversidad") : "";
                String ranking = request.getParameter("ranking")  != null ? request.getParameter("ranking") : "";
                String foto = request.getParameter("Foto") != null ? request.getParameter("Foto") : "";
                String pais = request.getParameter("idPais") != "-1" ? request.getParameter("idPais") : "";
                try{
                    if(!nombreUniversidad.equalsIgnoreCase("") && !ranking.equalsIgnoreCase("") && !pais.equalsIgnoreCase("")){
                        universidadDao.editarUniversidad(Integer.parseInt(idUniversidadStr), nombreUniversidad,Integer.parseInt(ranking),foto,Integer.parseInt(pais));
                        response.sendRedirect(request.getContextPath() +"/UniversidadServlet");
                    }else{
                        response.sendRedirect(request.getContextPath() +"/UniversidadServlet?msg=in");
                    }
                }catch (SQLException e){
                    response.sendRedirect("/UniversidadServlet?msg=sql");
                }
        }

    }
}
