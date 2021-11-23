<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="Participante" type="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante" scope="request" />
<jsp:useBean id="listaPaises" scope="request" type="java.util.ArrayList<pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais>"/>
<jsp:useBean id="vista" scope="request" type="java.lang.String"/>

<%if(vista.equals("editar")){%>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Edicion de Participante"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="participantes"/>
            </jsp:include>

            <div class="mt-2 text-center">
                <h1>Editar Participante</h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="w-75">
                    <form method="POST" action="<%=request.getContextPath()%>/ParticipanteServlet?action=editar&id=<%=Participante.getIdParticipante()%>">
                        <div class="form-group">
                            <label for="idParticipante">ID Participante</label>
                            <input class="form-control" type="text" value="<%=Participante.getIdParticipante()%>" disabled name="idParticipante" id="idParticipante">
                        </div>
                        <div class="form-group">
                            <label for="nombresParticipante">Nombre del Participante</label>
                            <input class="form-control" type="text" value="<%=Participante.getNombre()%>" name="nombreParticipante" id="nombresParticipante">
                        </div>
                        <div class="form-group">
                            <label for="apellidosParticipante">Apellidos del Participante</label>
                            <input class="form-control" type="text" value=" <%=Participante.getApellido()%>" name="apellidoParticipante" id="apellidosParticipante">
                        </div>
                        <div class="form-group">
                            <label for="edadParticipante">Edad del Participante</label>
                            <input class="form-control" type="text" value="<%=Participante.getEdad()%>" disabled name="edadParticipante" id="edadParticipante">
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
