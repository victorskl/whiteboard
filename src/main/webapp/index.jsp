<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.sendRedirect("cover");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to Whiteboard</title>

    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <meta name="description" content="Whiteboard Enterprise System"/>
    <meta name="author" content="Whiteboard Team"/>
    <link rel="icon" href="resources/images/favicon.ico"/>

    <script src="resources/js/jquery-1.11.3.min.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"/>

    <style type="text/css">
        #clip {
            position: fixed;
            left: 0;
            top: 20em;
            z-index: -1;
        }

        #clip:before {
            content: "LMS";
            font-size: 20.0em;
            text-align: center;
            display: block;
            opacity: 0.2;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Whiteboard Enterprise LMS</h1>
    </div>

    <h3>Views</h3>
    <ul>
        <li><a href="<c:url value = '/subject/list'/>">subject/list</a></li>
        <li><a href="<c:url value = '/subject/new'/>">subject/new</a></li>
        <li><a href="<c:url value = '/announcement/new'/>">announcement/new</a></li>
    </ul>

    <%--<hr/>--%>

    <%--<h2>Sample</h2>--%>
    <%--<ul>--%>
        <%--<li><c:out value="${'Knock, knock, anybody there!'}"/></li>--%>
        <%--<li><a href="<c:url value="/hello"/>">Test Hello Servlet</a></li>--%>

        <%--<li><a href="cover">cover</a></li>--%>
        <%--<li><a href="theme">theme</a></li>--%>
        <%--<li><a href="admin">admin</a></li>--%>
        <%--<li><a href="admin/dashboard">admin/dashboard</a></li>--%>
    <%--</ul>--%>

</div>
<div id="clip"></div>
</body>
</html>
