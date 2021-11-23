<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" scope="request" type="java.lang.String" class="java.lang.String"/>
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

    <% if (msg.equalsIgnoreCase("nombrevacio")) { %>
    <div class="alert alert-danger" role="alert">
        No deje el nombre vacio
    </div>
    <% } %>
    <% if (msg.equalsIgnoreCase("rankingvacio")) { %>
    <div class="alert alert-danger" role="alert">
        No deje el ranking vacio
    </div>
    <% } %>
    <% if (msg.equalsIgnoreCase("nombreinvalido")) { %>
    <div class="alert alert-danger" role="alert">
        Los caracteres ingresados en el nombre son invalidos
    </div>
    <% } %>
        <% if (msg.equalsIgnoreCase("sql")) { %>
    <div class="alert alert-danger" role="alert">
        Error de guardado de datos
    </div>
    <% } %>
    <% if (msg.equalsIgnoreCase("crcorr")) { %>
    <div class="alert alert-success" role="alert">
        Creacion de universidad exitosa
    </div>
    <% } %>
    <% if (msg.equalsIgnoreCase("edcorr")) { %>
    <div class="alert alert-success" role="alert">
        Edicion de universidad exitosa
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
                    <th style="text-align: center"><a style="color: white; text-decoration: none;" href="<%=request.getContextPath()%>/UniversidadServlet?filter=nombre">Nombre</a></th>
                    <th style="text-align: center"><a style="color: white; text-decoration: none;" href="<%=request.getContextPath()%>/UniversidadServlet?filter=Pais">Pais</a></th>
                    <th style="text-align: center"><a style="color: white; text-decoration: none;" href="<%=request.getContextPath()%>/UniversidadServlet">Ranking</a></th>
                    <th style="text-align: center"><a style="color: white; text-decoration: none;" href="<%=request.getContextPath()%>/UniversidadServlet?filter=Alumnos">Numero de Alumnos</a></th>
                    <th style="text-align: center">Foto</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%for(Universidad universidad : listaUniversidades){%>
                <%if(universidad.getContinente().getNombre().equalsIgnoreCase("Africa")){%>
                <tr class="fila-green">
                    <td style="text-align: center"><%=universidad.getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getPais().getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getRanking()%></td>
                    <td style="text-align: center"><%=universidad.getNumeroAlumnos()%></td>
                    <td style="text-align: center"><img src="<%=universidad.getFoto()%>"/></td>
                    <td style="text-align: center"><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td style="text-align: center"><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/AlumnoServlet?action=mostrar&id=<%=universidad.getIdUniversidad()%>">Mostrar alumnos</a></td>
                </tr>
                <%}%>
                <%if(universidad.getContinente().getNombre().equalsIgnoreCase("America")){%>
                <tr class="fila-yellow">
                    <td style="text-align: center"><%=universidad.getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getPais().getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getRanking()%></td>
                    <td style="text-align: center"><%=universidad.getNumeroAlumnos()%></td>
                    <td style="text-align: center"><img src="<%=universidad.getFoto()%>"/></td>
                    <td style="text-align: center"><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td style="text-align: center"><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/AlumnoServlet?action=mostrar&id=<%=universidad.getIdUniversidad()%>">Mostrar alumnos</a></td>
                </tr>
                <%}%>
                <%if(universidad.getContinente().getNombre().equalsIgnoreCase("Oceania")){%>
                <tr class="fila-purple">
                    <td style="text-align: center"><%=universidad.getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getPais().getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getRanking()%></td>
                    <td style="text-align: center"><%=universidad.getNumeroAlumnos()%></td>
                    <td style="text-align: center"><img src="<%=universidad.getFoto()%>"/></td>
                    <td style="text-align: center"><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td style="text-align: center"><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/AlumnoServlet?action=mostrar&id=<%=universidad.getIdUniversidad()%>">Mostrar alumnos</a></td>
                </tr>
                <%}%>
                <%if(universidad.getContinente().getNombre().equalsIgnoreCase("Europa")){%>
                <tr class="fila-blue">
                    <td style="text-align: center"><%=universidad.getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getPais().getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getRanking()%></td>
                    <td style="text-align: center"><%=universidad.getNumeroAlumnos()%></td>
                    <td style="text-align: center"><img src="<%=universidad.getFoto()%>"/></td>
                    <td style="text-align: center"><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td style="text-align: center"><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/AlumnoServlet?action=mostrar&id=<%=universidad.getIdUniversidad()%>">Mostrar alumnos</a></td>
                </tr>
                <%}%>
                <%if(universidad.getContinente().getNombre().equalsIgnoreCase("Asia")){%>
                <tr class="fila-red">
                    <td style="text-align: center"><%=universidad.getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getPais().getNombre()%></td>
                    <td style="text-align: center"><%=universidad.getRanking()%></td>
                    <td style="text-align: center"><%=universidad.getNumeroAlumnos()%></td>
                    <td style="text-align: center"><img src="<%=universidad.getFoto()%>"/></td>
                    <td style="text-align: center"><a class="btn btn-primary" href="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-edit"></span></a></td>
                    <td style="text-align: center"><a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet?action=borrar&id=<%=universidad.getIdUniversidad()%>"><span class="fa fa-trash"></span></a></td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/AlumnoServlet?action=mostrar&id=<%=universidad.getIdUniversidad()%>">Mostrar alumnos</a></td>
                </tr>
                <%}%>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>