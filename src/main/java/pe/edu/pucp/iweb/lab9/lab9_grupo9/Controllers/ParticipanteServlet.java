package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.PaisDao;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ParticipanteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ParticipanteServlet", value = "/ParticipanteServlet")
public class ParticipanteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("action") != null ? request.getParameter("action") : "listar";
        ParticipanteDao participanteDao = new ParticipanteDao();
        RequestDispatcher view;
        switch (accion){
            case "listar":
                request.setAttribute("listaParticipantes",participanteDao.listaParticipantes());
                view = request.getRequestDispatcher("/participantes.jsp");
                view.forward(request, response);
                break;

            case "crear":

                break;
            case "editar":
                String idParticipanteStr = request.getParameter("id");
                int idParticipante = Integer.parseInt(idParticipanteStr);
                Participante participante = participanteDao.obtenerParticipantePorId(idParticipante);
                request.setAttribute("Participante",participante);

                PaisDao paisDao = new PaisDao();
                ArrayList<Pais> listaPaises = paisDao.listarPaises();
                request.setAttribute("listaPaises",listaPaises);
                request.setAttribute("vista","editar");
                view = request.getRequestDispatcher("/editarparticipantes.jsp");
                view.forward(request, response);

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

        ParticipanteDao participanteDao = new ParticipanteDao();
        switch (accion){
            case "listar":


                break;

            case "crear":



                break;

            case "editar":
                String idParticipanteStr = request.getParameter("id");
                int idParticipante = Integer.parseInt(idParticipanteStr);


                break;

            //BORRAR LOGICO O BORRAR BASE DE DATOS
            case "borrar":


                break;

        }

    }
}
