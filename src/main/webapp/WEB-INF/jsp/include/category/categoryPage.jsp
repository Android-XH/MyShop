<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/search.js"></script>


<section class="select-bar">
    <a href="?sort=all${pagination.param}" class="${empty param.sort?'selected':''}">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
    <a href="?sort=comment${pagination.param}" class="${param.sort=='comment'?'selected':''}">人气<span class="glyphicon glyphicon-arrow-down"></span></a>
    <a href="?sort=date${pagination.param}" class="${param.sort=='date'?'selected':''}">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
    <a href="?sort=saleCount${pagination.param}" class="${param.sort=='saleCount'?'selected':''}">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
    <a href="?sort=${param.sort=='price'?'priceInverse':'price'}${pagination.param}" class="${(param.sort=="price"||param.sort=="priceInverse")?'selected':''}">价格<span class="glyphicon glyphicon-resize-vertical"></span></a>
<%--    <span class="price">--%>
<%--        <input type="text" placeholder="￥请输入" class="sortBarPrice beginPrice" id="low_price">--%>
<%--        <span>-</span>--%>
<%--        <input type="text" placeholder="￥请输入" class="sortBarPrice beginPrice" id="high_price">--%>
<%--    </span>--%>
</section>

<%@include file="commonPage.jsp"%>
<%@include file="paginationPage.jsp" %>