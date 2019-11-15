<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div >
    <main class="search">
        <div class="items">
            <c:forEach items="${products}" var="a" varStatus="vs">
                <a href="product?id=${a.id}">
                    <div class="border" price="${a.price}">
                        <div class="item">
                            <div class="content">
                                <img class="item-img" src="${a.pict_url}">
                                <div class="item-price">
                                    <span class="line">￥${a.price}</span>
                                    <span > <fmt:formatNumber value='${a.price-a.coupon.coupon_amount}' pattern='#0.0'/></span>
                                </div>
                                <div class="item-title">
                                        ${a.short_title}
                                </div>
                                <div class="item-bottom">
                                    <span>月成交
                                        <div><em>${a.volume}笔</em></div>
                                    </span>
                                    <span>优惠券剩余
                                        <div>
                                            <em>${a.coupon.coupon_remain_count}</em>
                                        </div>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
            <c:if test="${empty products}">
                <div class="no-match">没有满足条件的产品</div>
            </c:if>
        </div>
    </main>
</div>

