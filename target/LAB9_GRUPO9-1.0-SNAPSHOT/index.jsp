<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <!--Colocar como value: nombre de la página actual -->
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="AllMusic"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value=""/>
            </jsp:include>

            <div class="container text-center mt-2">
                <h1>Bienvenido a nuestra pagina Lab9</h1>

                <p>Lo que tu quieras ver ctm</p>
            </div>


        </div>

        <jsp:include page="/static/scripts.jsp"/>

    </body>
</html>
