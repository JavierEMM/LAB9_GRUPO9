<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="universidad" type="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad" scope="request" />
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais>"/>
<jsp:useBean id="vista" scope="request" type="java.lang.String"/>

<%if(vista.equals("editar")){%>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Edicion de Universidades"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="universidades"/>
    </jsp:include>

    <div class="mt-2 text-center">
        <h1>Editar Universidad</h1>
    </div>
    <div class="d-flex justify-content-center">
        <div class="w-75">
            <form method="POST" action="<%=request.getContextPath()%>/UniversidadServlet?action=editar&id=<%=universidad.getIdUniversidad()%>">
                <div class="form-group">
                    <label for="idUniversidad">ID Participante</label>
                    <input class="form-control" type="text" value="<%=universidad.getIdUniversidad()%>" disabled name="idUniversidad" id="idUniversidad">
                </div>
                <div class="form-group">
                    <label for="nombreUniversidad">Nombre de la Universidad</label>
                    <input class="form-control" type="text" value="<%=universidad.getNombre()%>" name="nombreUniversidad" id="nombreUniversidad">
                </div>
                <div class="form-group">
                    <label for="ranking">Ranking</label>
                    <input class="form-control" type="text" value="<%=universidad.getRanking()%>"  name="ranking" id="ranking">
                </div>
                <div class="form-group">
                    <label for="numeroAlumnos">Numero de Alumnos</label>
                    <input class="form-control" type="text" value=" <%=universidad.getNumeroAlumnos()%>" disabled name="numeroAlumnos" id="numeroAlumnos">
                </div>
                <div class="form-group">
                    <label for="Foto">Foto</label>
                    <input class="form-control" type="text" value="<%=universidad.getFoto()%>"  name="Foto" id="Foto">
                </div>
                <div class="form-group">
                    <label>Pais</label>
                    <select class="form-control form-select-sm" name="idPais">
                        <% for (Pais pais : listaPaises) { %>
                        <option value="<%=pais.getIdPais()%>">
                            <%=pais.getNombre()%>
                        </option>
                        <% } %>
                    </select>
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
<%if(vista.equals("crear")){%>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Adicion de Participante"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="participantes"/>
    </jsp:include>

    <div class="mt-2 text-center">
        <h1>Añadir Participante</h1>
    </div>
    <div class="d-flex justify-content-center">
        <div class="w-75">
            <form method="POST" action="<%=request.getContextPath()%>/ParticipanteServlet?action=crear">
                <div class="form-group">
                    <label for="nombresParticipante1">Nombre del Participante</label>
                    <input class="form-control" type="text"  name="nombreParticipante" id="nombresParticipante1">
                </div>
                <div class="form-group">
                    <label for="apellidosParticipante1">Apellidos del Participante</label>
                    <input class="form-control" type="text"  name="apellidoParticipante" id="apellidosParticipante1">
                </div>
                <div class="form-group">
                    <label for="edadParticipante1">Edad del Participante</label>
                    <input class="form-control" type="text"  name="edadParticipante" id="edadParticipante1">
                </div>
                <div class="form-group">
                    <label>Nacionalidad</label>
                    <select class="form-control form-select-sm" name="idPais">
                        <% for (Pais pais : listaPaises) { %>
                        <option value="<%=pais.getIdPais()%>">
                            <%=pais.getNombre()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Genero</label>
                    <select class="form-control form-select-sm" name="Genero">
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="X">Otros</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/ParticipanteServlet">Cancelar</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>
<%}%>