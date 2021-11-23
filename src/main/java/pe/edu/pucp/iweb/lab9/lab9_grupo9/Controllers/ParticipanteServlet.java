package pe.edu.pucp.iweb.lab9.lab9_grupo9.Controllers;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.DTO.ParticipanteDTO;
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
        PaisDao paisDao = new PaisDao();
        switch (accion){
            case "listar":
                String mensaje = request.getParameter("msg");
                request.setAttribute("msg",mensaje);
                request.setAttribute("listaParticipantes",participanteDao.listaParticipantes());
                view = request.getRequestDispatcher("/participantes.jsp");
                view.forward(request, response);
                break;

            case "crear":
                request.setAttribute("listaPaises",paisDao.listarPaises());
                request.setAttribute("vista","crear");
                view = request.getRequestDispatcher("/editarparticipantes.jsp");
                view.forward(request,response);
                break;

            case "editar":
                String idParticipanteStr = request.getParameter("id");
                int idParticipante = Integer.parseInt(idParticipanteStr);
                Participante participante = participanteDao.obtenerParticipantePorId(idParticipante);
                request.setAttribute("Participante",participante);
                ArrayList<Pais> arreglito = paisDao.listarPaises();
                int indice = 0;
                for(Pais pais : arreglito){
                    if(pais.getNombre().equalsIgnoreCase(participante.getNacionalidad())){
                        break;
                    }
                    indice++;
                }
                ArrayList<Pais> listaPaises = new ArrayList<>();
                listaPaises.add(arreglito.get(indice));
                arreglito.remove(indice);
                for(Pais pais : arreglito){
                    listaPaises.add(pais);
                }
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
            case "crear":
                String nombreParticipante = request.getParameter("nombreParticipante");
                String apellidoParticipante = request.getParameter("apellidoParticipante");
                String edadParticipanteStr = request.getParameter("edadParticipante");
                int edadParticipante = Integer.parseInt(edadParticipanteStr);
                String idPaisParticipanteStr = request.getParameter("idPais");
                int idPaisParticipante = Integer.parseInt(idPaisParticipanteStr);
                String generoParticipante = request.getParameter("Genero");

                boolean nombreCorrecto1 = participanteDao.validarNombre(nombreParticipante) ;
                boolean apellidoCorrecto1 = participanteDao.validarApellido(apellidoParticipante);
                boolean edadAceptada1 = participanteDao.edadAceptable(edadParticipante);

                if(nombreCorrecto1 == true && apellidoCorrecto1 == true && edadAceptada1 == true){
                    ParticipanteDTO participante = new ParticipanteDTO(nombreParticipante,apellidoParticipante,edadParticipante,generoParticipante,idPaisParticipante);
                    //Participante participante = new Participante(nombre,apellido,edad,nombrePais,genero,id);
                    participanteDao.crearParticipante(participante);
                    response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=crecorr");
                }else{
                    if((nombreCorrecto1 == false || apellidoCorrecto1 == false) && edadAceptada1 == true){
                        response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=credaterr");
                    }else{
                        if(nombreCorrecto1 == false && apellidoCorrecto1 == false && edadAceptada1 == false){
                            response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=crealldaterr");
                        }else{
                            if(edadAceptada1 == false){
                                response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=creageerr");
                            }
                        }
                    }

                }

                break;

            case "editar":
                String idStr = request.getParameter("id");
                int id = Integer.parseInt(idStr);
                String nombre = request.getParameter("nombreParticipante");
                String apellido = request.getParameter("apellidoParticipante");
                String edadStr = request.getParameter("edadParticipante");
                int edad = Integer.parseInt(edadStr);
                String idPaisStr= request.getParameter("idPais");
                int idPais = Integer.parseInt(idPaisStr);
                String genero = request.getParameter("Genero");
                //OBTENGO EL NOMBRE DEL PAIS CON DICHO ID
                //PaisDao paisDao = new PaisDao();
                //String nombrePais = paisDao.obtenerNombrePaisPorId(idPais);

                boolean nombreCorrecto = participanteDao.validarNombre(nombre) ;
                boolean apellidoCorrecto = participanteDao.validarApellido(apellido);
                boolean edadAceptada = participanteDao.edadAceptable(edad);
                if(nombreCorrecto == true && apellidoCorrecto == true && edadAceptada == true){
                    ParticipanteDTO participante = new ParticipanteDTO(id,nombre,apellido,edad,genero,idPais);
                    //Participante participante = new Participante(nombre,apellido,edad,nombrePais,genero,id);
                    participanteDao.editarParticipante(participante);
                    response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=edcorr");
                }else{
                    if((nombreCorrecto == false || apellidoCorrecto == false) && edadAceptada == true){
                        response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=eddaterr");
                    }else{
                        if(nombreCorrecto == false && apellidoCorrecto == false && edadAceptada == false){
                            response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=edalldaterr");
                        }else{
                            if(edadAceptada == false){
                                response.sendRedirect(request.getContextPath() + "/ParticipanteServlet?msg=edageerr");
                            }
                        }
                    }

                }

                break;

            //BORRAR LOGICO O BORRAR BASE DE DATOS
            case "borrar":


                break;

        }

    }
}
