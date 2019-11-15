<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>


<section class="head-bar">
    <div class="c-menu">
        <span class="glyphicon glyphicon-th-list icon"></span>
        <span>商品分类</span>
    </div>
<%--    <div class="r-menu">--%>
<%--&lt;%&ndash;        <a href="#nowhere"><img src="img/chaoshi.png"></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <a href="#nowhere"><img src="img/guoji.png"></a>&ndash;%&gt;--%>
<%--        <c:forEach items="${categories}" var="c" varStatus="vs">--%>
<%--            <c:if test="${vs.count>=1 and vs.count<=4}">--%>
<%--                <a href="category?id=${c.id}">${c.name}</a>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
</section>

<section class="carousel">
    <div data-ride="carousel" class="carousel-of-product carousel slide" id="carousel-of-product" >
        <div role="listbox" class="carousel-inner">
            <div class="item active">
                <a href="search" target="_blank">
                <img src="img/home_01.png" class="carousel carousel-image">
                </a>
            </div>

        </div>

        <div class="m-menu">
            <ul>
            <c:forEach items="${categories}" var="c" varStatus="vs">
                <li cid="${c.id}"><a>${c.name}</a></li>
            </c:forEach>
            </ul>
        </div>
        <c:forEach items="${categories}" var="c" varStatus="vs">
        <div class="d-menu" cid="${c.id}" style="display: none">
            <c:forEach items="${c.categories}" var="item" varStatus="vs">
                <li>
                    <a href="category?categoryID=${item.category_id}">${item.name}</a>
                </li>

                <div class="tag">
                    <c:forEach items="${item.categoryItems}" var="items" varStatus="vs">
                        <a href="category?categoryID=${item.category_id}&categoryItemID=${items.category_item_id}">${items.category_name}</a>
                    </c:forEach>
                </div>

            </c:forEach>
        </div>
        </c:forEach>
    </div>

</section>
