<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Pais pais = (Pais) request.getAttribute("pais");
%>

<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Edicion de Pais"/>
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
            <form method="POST" action="<%=request.getContextPath()%>/PaisServlet?action=editar">
                <div class="form-group">
                    <label for="idPais">ID Pais</label>
                    <input class="form-control" type="text" value="<%=pais.getIdPais()%>" readonly name="idPais" id="idPais">
                </div>
                <div class="form-group">
                    <label for="nombrePais">Nombre del Pais</label>
                    <input class="form-control" type="text" value="<%=pais.getNombre()%>" name="nombrePais" id="nombrePais">
                </div>
                <div class="form-group">
                    <label for="poblacion">Poblacion</label>
                    <input class="form-control" type="text" value="<%=pais.getCantidadPoblacion()%>" name="poblacion" id="poblacion">
                </div>
                <div class="form-group">
                    <label for="tamano">Tamaño del pais</label>
                    <input class="form-control" type="text" value="<%=pais.getTamano()%>" name="tamano" id="tamano">
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/PaisServlet">Cancelar</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>