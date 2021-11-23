<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaAlumnosPorUniversidad" scope="request" type="java.util.ArrayList<pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno>"/>
<jsp:useBean id="vista" scope="request" type="java.lang.String"/>
<jsp:useBean id="idUniversidad" scope="request" type="java.lang.String"/>
<%if(vista.equalsIgnoreCase("mostrar")){%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Alumnos de la Universidad"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Alumnos pertenecientes a la Universidad</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">

                    <div class="my-2">
                        <a href="<%=request.getContextPath()%>/AlumnoServlet?action=crear&idUniversidad=<%=idUniversidad%>" class="btn btn-info">Añadir alumno a la Universidad</a>
                    </div>
                    <%if(listaAlumnosPorUniversidad.size() == 0){%>
                    <h1>No hay ningun alumno en la Universidad</h1>
                    <%}else{%>
                    <table class="table table-dark table-transparent table-hover">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Edad</th>
                                <th>Codigo</th>
                                <th>Promedio Ponderado</th>
                                <th>Condicion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for(Alumno alumno : listaAlumnosPorUniversidad){%>
                                <%if(alumno.getCondicion().equalsIgnoreCase("Normal")){%>
                                    <tr>
                                        <td style="color: blue"><%=alumno.getNombre()%></td>
                                        <td style="color: blue"><%=alumno.getApellido()%></td>
                                        <td style="color: blue"><%=alumno.getEdad()%></td>
                                        <td style="color: blue"><%=alumno.getCodigo()%></td>
                                        <td style="color: blue"><%=alumno.getPromedioPonderado()%></td>
                                        <td style="color: blue"><%=alumno.getCondicion()%></td>
                                        <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/AlumnoServlet?action=editar&id=<%=alumno.getIdParticipante()%>"><span class="fa fa-edit"></span></a></td>
                                        <td><a class="btn btn-danger"  href="<%=request.getContextPath()%>/AlumnoServlet?action=borrar&id=<%=alumno.getIdParticipante()%>"><span class="fa fa-trash"></span></a></td>
                                    </tr>
                                <%}else{%>
                                    <tr>
                                        <td style="color: red"><%=alumno.getNombre()%></td>
                                        <td style="color: red"><%=alumno.getApellido()%></td>
                                        <td style="color: red"><%=alumno.getEdad()%></td>
                                        <td style="color: red"><%=alumno.getCodigo()%></td>
                                        <td style="color: red"><%=alumno.getPromedioPonderado()%></td>
                                        <td style="color: red"><%=alumno.getCondicion()%></td>
                                        <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/AlumnoServlet?action=editar&id=<%=alumno.getIdParticipante()%>"><span class="fa fa-edit"></span></a></td>
                                        <td><a class="btn btn-danger"  href="<%=request.getContextPath()%>/AlumnoServlet?action=borrar&id=<%=alumno.getIdParticipante()%>"><span class="fa fa-trash"></span></a></td>
                                    </tr>
                                <%}%>
                            <%}%>
                        </tbody>
                    </table>
                    <%}%>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
<%}%>