<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Toda la música</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("bandas")? "active": "" %>"
                   href="<%=request.getContextPath()%>/bandas">Bandas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("artistas")? "active": "" %>"
                   href="<%=request.getContextPath()%>/artistas">Artistas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("albumes")? "active": "" %>"
                   href="<%=request.getContextPath()%>/albumes">Álbumes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("canciones")? "active": "" %>"
                   href="<%=request.getContextPath()%>/canciones">Canciones</a>
            </li>

        </ul>
    </div>
</nav>
