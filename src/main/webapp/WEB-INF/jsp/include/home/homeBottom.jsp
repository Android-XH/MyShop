<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>


<main class="index">
    <c:forEach items="${recommends}" var="c" varStatus="vs">
            <div class="products">
                <div class="title-bar">
                    <i class="color-mark"></i>
                    <span class="category-title">${c.name}</span>
                </div>

                <div class="product-items">
                    <c:forEach items="${c.products}" var="p" varStatus="vs">
                        <div class="item">
                            <a href="product?id=${p.id}">
                                <img src="${p.pict_url}">
                                <div class="item-title">${p.short_title}</div>
                                <div class="item-price">￥${p.price}</div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
    </c:forEach>
    <img src="img/end.png" class="end-png" id="endpng">
</main>