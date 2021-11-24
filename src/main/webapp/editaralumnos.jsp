<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaParticipantesNoUniversidad" scope="request" type="java.util.ArrayList<pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante>" class="java.util.ArrayList"/>
<jsp:useBean id="vista" scope="request" type="java.lang.String"/>
<jsp:useBean id="idUniversidad" scope="request" type="java.lang.String" class="java.lang.String"/>
<!--jsp:useBean id="Alumno" type="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno" scope="request" class="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno"/-->
<% Alumno Alumno = (Alumno) request.getAttribute("Alumno");%>
<%if(vista.equals("crear")){%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Adicion de Alumno"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Adicion de Alumno a la Universidad</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="POST" action="<%=request.getContextPath()%>/AlumnoServlet?action=crear&idUniversidad=<%=idUniversidad%>">
                        <div class="form-group">
                            <label>Escoger Alumno</label>
                            <select class="form-control form-select-sm" name="idParticipante">
                                <% for (Participante listaParticipantes : listaParticipantesNoUniversidad) { %>
                                <option value="<%=listaParticipantes.getIdParticipante()%>">
                                    <%=listaParticipantes.getNombre()%>
                                </option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="codigoParticipante">Codigo del Nuevo Alumno</label>
                            <input class="form-control" type="text" name="codigoParticipante" id="codigoParticipante">
                        </div>
                        <div class="form-group">
                            <label for="promedioParticipante">Promedio ponderado</label>
                            <input class="form-control" type="text"  name="promedioParticipante" id="promedioParticipante">
                        </div>
                        <button type="submit" class="btn btn-primary">Añadir</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
<%}%>
<%if(vista.equals("editar")){%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Edicion de Alumno"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="universidades"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Editar Alumno de la Universidad</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="POST" action="<%=request.getContextPath()%>/AlumnoServlet?action=editar&id=<%=Alumno.getIdParticipante()%>">
                        <div class="form-group">
                            <label for="codigoAlumno">Codigo del Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getCodigo()%>" name="codigoAlumno" id="codigoAlumno">
                        </div>
                        <div class="form-group">
                            <label for="nombreAlumno">Nombre del Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getNombre()%>" name="nombreAlumno" id="nombreAlumno">
                        </div>
                        <div class="form-group">
                            <label for="apellidoAlumno">Apellido del Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getApellido()%>" name="apellidoAlumno" id="apellidoAlumno">
                        </div>
                        <div class="form-group">
                            <label for="edadAlumno">Edad del Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getEdad()%>" name="edadAlumno" id="edadAlumno">
                        </div>
                        <div class="form-group">
                            <label for="promedioAlumno">Promedio Ponderadodel Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getPromedioPonderado()%>" name="promedioAlumno" id="promedioAlumno">
                        </div>
                        <div class="form-group">
                            <label for="condicionAlumno">Condicion del Alumno</label>
                            <input class="form-control" type="text" value="<%=Alumno.getCondicion()%>" name="condicionAlumno" id="condicionAlumno">
                        </div>
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/UniversidadServlet">Cancelar</a>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
<%}%>