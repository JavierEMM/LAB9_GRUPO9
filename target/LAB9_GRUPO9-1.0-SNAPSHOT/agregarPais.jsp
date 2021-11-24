<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Continente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Continente> listacontinentes = (ArrayList<Continente>) request.getAttribute("continentes");
%>

<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Creacion de Pais"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="pais"/>
    </jsp:include>

    <div class="mt-2 text-center">
        <h1>Editar Pais</h1>
    </div>
    <div class="d-flex justify-content-center">
        <div class="w-75">
            <form method="POST" action="<%=request.getContextPath()%>/PaisServlet?action=crear">
                <div class="form-group">
                    <label for="nombrePais">Nombre del Pais</label>
                    <input class="form-control" type="text" name="nombrePais" id="nombrePais">
                </div>
                <div class="form-group">
                    <label for="continente">Continentes</label>
                    <select id="continente" name="continente" class="form-control">
                        <%for (Continente continente:listacontinentes){%>
                        <option value="<%=continente.getIdcontinente()%>">
                            <%=continente.getNombre()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <div class="form-group">
                    <label for="poblacion">Poblacion</label>
                    <input class="form-control" type="text"  name="poblacion" id="poblacion">
                </div>
                <div class="form-group">
                    <label for="tamano">Tamaño del pais</label>
                    <input class="form-control" type="text"  name="tamano" id="tamano">
                </div>
                <button type="submit" class="btn btn-primary">Crear</button>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/PaisServlet">Cancelar</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>