<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" scope="request" type="java.lang.String" class="java.lang.String"/>
<!--jsp:useBean id="pais" scope="request" type="java.lang.String" class="java.lang.String"/-->
<% String pais = (String) request.getAttribute("pais");%>
<!--jsp:useBean id="porcentaje" scope="request" type="java.util.ArrayList<java.lang.Double>"/-->
<% ArrayList<Double> porcentaje = (ArrayList<Double>) request.getAttribute("porcentaje");%>
<!--jsp:useBean id="promedio" scope="request" type="java.lang.Double" class="java.lang.Double" class="java.lang.String"/-->
<% Double promedio = (Double) request.getAttribute("promedio");%>
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
            <% if (msg.equalsIgnoreCase("borrcorr")) { %>
            <div class="alert alert-success" role="alert">
                Participante borrado exitosamente
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
            <% if (msg.equalsIgnoreCase("borrerr")) { %>
            <div class="alert alert-danger" role="alert">
                No se puede eliminar al participante porque pertenece a una universidad
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
                        <a href="<%=request.getContextPath()%>/ParticipanteServlet?action=crear" class="btn btn-info">A??adir participante</a>
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
                                <td><a class="btn btn-danger"  href="<%=request.getContextPath()%>/ParticipanteServlet?action=borrar&id=<%=participante.getIdParticipante()%>"><span class="fa fa-trash"></span></a></td>
                            </tr>
                        <%}%>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-center">
                        <label class="form-label">Pais con mayor numero de participantes: <%=pais%> </label>
                        <br>
                        <label class="form-label">Porcentaje de hombres: <%=porcentaje.get(0)%><%="%"%> </label>
                        <br>
                        <label class="form-label">Porcentaje de mujeres: <%=porcentaje.get(1)%><%="%"%> </label>
                        <label class="form-label">Promedio de edad de los participantes: <%=promedio%> </label>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
