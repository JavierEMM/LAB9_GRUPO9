package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ParticipanteServlet", value = "/ParticipanteServlet")
public class ParticipanteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParticipanteDao participanteDao = new ParticipanteDao();
        request.setAttribute("listaParticipantes",participanteDao.listaParticipantes());
        RequestDispatcher view = request.getRequestDispatcher("/participantes.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action") != null ? request.getParameter("action") : "listar";

        switch (accion){
            case "listar":


                break;

            case "crear":



                break;

            case "editar":

                break;

            //BORRAR LOGICO O BORRAR BASE DE DATOS
            case "borrar":

                break;

        }

    }
}
