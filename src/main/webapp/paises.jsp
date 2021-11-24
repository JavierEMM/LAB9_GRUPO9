<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Continente" %>
<%@ page import="pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos.ContinenteDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mensaje" scope="request" type="java.lang.String" class="java.lang.String"/>
<% ArrayList<Pais> listaPaises = (ArrayList<Pais>) request.getAttribute("listaPaises");
    String msg = (String) request.getAttribute("msg");
%>


<script>
    function filtradoPorContinente(){
        var valor = document.getElementById("continente").value;
        var url = "<%=request.getContextPath()%>/PaisServlet?action=filtrar&idcontinente="
        url = url.concat(valor);
        window.location.assign(url);
    }
</script>

<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Participantes"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="paises"/>
    </jsp:include>

    <% if (msg.equals("nombrevacio")) { %>
    <div class="alert alert-danger" role="alert">
        No deje el nombre vacio
    </div>
    <% } %>

    <% if (msg.equals("tamanovacio")) { %>
    <div class="alert alert-danger" role="alert">
        No deje el tamaño vacio
    </div>
    <% } %>

    <% if (msg.equals("poblacionVacia")) { %>
    <div class="alert alert-danger" role="alert">
        No deje la poblacion vacia
    </div>
    <% } %>

    <% if (msg.equals("continentevacio")) { %>
    <div class="alert alert-danger" role="alert">
        No deje el continente vacio
    </div>
    <% } %>

    <% if (msg.equals("corr")) { %>
    <div class="alert alert-success" role="alert">
        Creacion de pais exitoso
    </div>
    <% } %>

    <div class="mt-2 text-center">
        <h1>Lista de Participantes</h1>
    </div>

    <div class="d-flex justify-content-center">
        <div class="w-75">

            <div class="my-2">
                <a href="<%=request.getContextPath()%>/PaisServlet?action=crear" class="btn btn-info">Añadir Pais</a>
            </div>

            <table class="table table-dark table-transparent table-hover">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>continente</th>
                    <th>poblacion</th>
                    <th>tamaño</th>
                    <th>
                        <%
                            ContinenteDao continenteDao = new ContinenteDao();
                            ArrayList<Continente> listacontinentes = continenteDao.listarContinente();
                        %>
                        <label for="continente">Continentes</label>
                        <select id="continente" name="continente" class="form-control">
                            <%for (Continente continente:listacontinentes){%>
                            <option value="<%=continente.getIdcontinente()%>" >
                                <%=continente.getNombre()%>
                            </option>
                            <%}%>
                        </select>
                    </th>
                    <th>
                        <button type="button" class="btn btn-success" onclick="filtradoPorContinente()">Filtrar</button>
                    </th>
                </tr>
                </thead>
                <tbody>
                <%for(Pais pais : listaPaises){%>
                <tr>
                    <td><%=pais.getNombre()%></td>
                    <td><%=pais.getContinente()%></td>
                    <td><%=pais.getCantidadPoblacion()%></td>
                    <td><%=pais.getTamano()%></td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick="window.location.assign('<%=request.getContextPath()%>/PaisServlet?action=editar&id=<%=pais.getIdPais()%>')" >Editar</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger" onclick="window.location.assign('<%=request.getContextPath()%>/PaisServlet?action=eliminar&id=<%=pais.getIdPais()%>')">Eliminar</button>
                    </td>
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
