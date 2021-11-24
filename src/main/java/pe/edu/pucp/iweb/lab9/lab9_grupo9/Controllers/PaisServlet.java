package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ContinenteDao;
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
        String msg = request.getParameter("msg") == null ?  "" : request.getParameter("msg");
        RequestDispatcher view;
        switch (action){
            case "listar":
                request.setAttribute("listaPaises",paisDao.listarPaises());
                request.setAttribute("msg",msg);
                view = request.getRequestDispatcher("/paises.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String idPais = request.getParameter("id");
                Pais pais = paisDao.obtenerPaisId(Integer.parseInt(idPais));
                request.setAttribute("pais",pais);
                view = request.getRequestDispatcher("/editarPais.jsp");
                view.forward(request, response);
                break;
            case "crear":
                ContinenteDao continenteDao = new ContinenteDao();
                request.setAttribute("continentes",continenteDao.listarContinente());
                view = request.getRequestDispatcher("/agregarPais.jsp");
                view.forward(request, response);
                break;
            case "filtrar":
                String idcontinente = request.getParameter("idcontinente");
                request.setAttribute("listaPaises",paisDao.listarPaisesFiltrados(Integer.parseInt(idcontinente)));
                view = request.getRequestDispatcher("/paises.jsp");
                view.forward(request, response);
                break;
            case "eliminar":
                String idPaisEliminar = request.getParameter("id");
                paisDao.eliminarPais(idPaisEliminar);
                response.sendRedirect(request.getContextPath()+"/PaisServlet");
                break;

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaisDao paisDao = new PaisDao();
        String action = request.getParameter("action");
        switch (action){
            case "editar":
                String idPais = request.getParameter("idPais");
                String nombre = request.getParameter("nombrePais");
                int poblacion = Integer.parseInt(request.getParameter("poblacion"));
                String tamano = request.getParameter("tamano");
                paisDao.editarPais(nombre,poblacion,tamano,idPais);
                response.sendRedirect(request.getContextPath()+"/PaisServlet");
                break;
            case "crear":
                String nombreAgregar = request.getParameter("nombrePais").equals("") ? null: request.getParameter("nombrePais");
                int idContinente = request.getParameter("continente").equals("") ? 0 : Integer.parseInt(request.getParameter("continente"));
                int poblacionAgregar = request.getParameter("poblacion").equals("")? -1 : Integer.parseInt(request.getParameter("poblacion"));
                String tamanoAgregar = request.getParameter("tamano").equals("") ? null : request.getParameter("tamano");

                if (nombreAgregar != null ){
                    if (idContinente != 0){
                        if (poblacionAgregar != -1 ){
                            if (tamanoAgregar != null){
                                paisDao.añadirPais(nombreAgregar,poblacionAgregar,tamanoAgregar,idContinente);
                                response.sendRedirect(request.getContextPath()+"/PaisServlet?msg=corr");
                            }else{
                                response.sendRedirect(request.getContextPath() +"/PaisServlet?msg=tamanovacio");
                            }
                        }else {
                            response.sendRedirect(request.getContextPath() +"/PaisServlet?msg=poblacionVacia");
                        }
                    }else{
                        response.sendRedirect(request.getContextPath() +"/PaisServlet?msg=continentevacio");
                    }
                }else{
                    response.sendRedirect(request.getContextPath() +"/PaisServlet?msg=nombrevacio");
                }
                break;
        }
    }
}
