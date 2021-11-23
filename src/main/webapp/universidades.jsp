<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
<% ArrayList<Universidad> listaUniversidades = (ArrayList) request.getAttribute("listaUniversidades");%>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Universidades"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="universidades"/>
    </jsp:include>

    <div class="mt-2 text-center">
        <h1>Lista de Universidades</h1>
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
                <a href="<%=request.getContextPath()%>/UniversidadServlet?action=crear" class="btn btn-info">Añadir Universidad</a>
            </div>
            <table class="table table-dark table-transparent table-hover">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Pais</th>
                    <th>Ranking</th>
                    <th>Numero de alumnos</th>
                    <th>Foto</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%for(Universidad universidad : listaUniversidades){%>
                <tr>
                    <td><%=universidad.getNombre()%></td>
                    <td><%=universidad.getPais().getNombre()%></td>
                    <td><%=universidad.getRanking()%></td>
                    <td><%=universidad.getNumeroAlumnos()%></td>
                    <td><%=universidad.getFoto()%></td>
                    <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
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