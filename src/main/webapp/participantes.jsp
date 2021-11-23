
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--jsp:useBean id="listaArtistas" scope="request" type="java.util.ArrayList<Beans.Artista>"/-->
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Artistas"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="artistas"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Lista de Artistas</h1>
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
                        <a href="<%=request.getContextPath()%>/artistas?action=crear" class="btn btn-info">Añadir artista</a>
                    </div>

                    <table class="table table-dark table-transparent table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre del Artista</th>
                                <th>Nombre de la Banda</th>
                                <th>Actualizar</th>
                                <th>Eliminar</th>
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
