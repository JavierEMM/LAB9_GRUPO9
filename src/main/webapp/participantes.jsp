<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" scope="request" type="java.lang.String" class="java.lang.String"/>
<% ArrayList<Participante> listaParticipantes = (ArrayList<Participante>) request.getAttribute("listaParticipantes");%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Participantes"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="participantes"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Lista de Participantes</h1>
            </div>

            <% if (msg.equalsIgnoreCase("edcorr")) { %>
            <div class="alert alert-success" role="alert">
                Participante editado exitosamente
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("crecorr")) { %>
            <div class="alert alert-success" role="alert">
                Participante creado exitosamente
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("eddaterr")) { %>
            <div class="alert alert-danger" role="alert">
                Nombre o Apellido erroneo
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("credaterr")) { %>
            <div class="alert alert-danger" role="alert">
                Nombre o Apellido erroneo
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("edalldaterr")) { %>
            <div class="alert alert-danger" role="alert">
                Nombre , Apellidos y Edad erroneo
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("crealldaterr")) { %>
            <div class="alert alert-danger" role="alert">
                Nombre , Apellidos y Edad erroneo
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("edageerr")) { %>
            <div class="alert alert-danger" role="alert">
                Edad erronea
            </div>
            <% } %>
            <% if (msg.equalsIgnoreCase("creageerr")) { %>
            <div class="alert alert-danger" role="alert">
                Edad erronea
            </div>
            <% } %>

            <div class="d-flex justify-content-center">
                <div class="w-75">

                    <div class="my-2">
                        <a href="<%=request.getContextPath()%>/ParticipanteServlet?action=crear" class="btn btn-info">Añadir participante</a>
                    </div>

                    <table class="table table-dark table-transparent table-hover">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Edad</th>
                                <th>Genero</th>
                                <th>Nacionalidad</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%for(Participante participante : listaParticipantes){%>
                            <tr>

                                <td><%=participante.getNombre()%></td>
                                <td><%=participante.getApellido()%></td>
                                <td><%=participante.getEdad()%></td>
                                <td><%=participante.getNacionalidad()%></td>
                                <td><%=participante.getGenero()%></td>
                                <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/ParticipanteServlet?action=editar&id=<%=participante.getIdParticipante()%>"><span class="fa fa-edit"></span></a></td>
                                <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/ParticipanteServlet?action=borrar&id=<%=participante.getIdParticipante()%>"><span class="fa fa-trash"></span></a></td>
                            </tr>
                        <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
