<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<header class="index-top">
    <a href="./" class="logo">
        <img src="img/logo.png">
    </a>
    <div class="search">
        <form action="search" >
        <input type="text" value="${keyword}" autocomplete="off" name="keyword">
        <button class="search-button" type="submit">搜索</button>
        </form>
        <ul class="search-below">
            <c:forEach items="${searchHistories}" var="c" varStatus="vs">
                <li><a href="search?keyword=${c.keyword}">${c.keyword}</a></li>
            </c:forEach>
        </ul>

    </div>

</header>