
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
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

            <% if (mensaje.equalsIgnoreCase("corr")) { %>
            <div class="alert alert-success" role="alert">
                Artista creado exitosamente
            </div>
            <% } %>
            <% if (mensaje.equalsIgnoreCase("err")) { %>
            <div class="alert alert-danger" role="alert">
                Error al crear el artista
            </div>
            <% } %>
            <% if (mensaje.equalsIgnoreCase("borrcorr")) { %>
            <div class="alert alert-success" role="alert">
                Eliminacion de artista exitoso
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
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Edad</th>
                                <th>Nacionalidad</th>
                                <th>Genero</th>
                            </tr>
                            <tr>
                                <td><%=artista.getIdArtista()%></td>
                                <td><%=artista.getNombreArtista()%></td>
                                <td><%=artista.getNombreBanda()%></td>
                                <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/artistas?action=editar&id=<%=artista.getIdArtista()%>"><span class="fa fa-edit"></span></a></td>
                                <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/artistas?action=borrar&id=<%=artista.getIdArtista()%>"><span class="fa fa-trash"></span></a></td>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
