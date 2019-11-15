<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main class="search">
    <div class="items">
        <c:forEach items="${alimamaList}" var="alimama" varStatus="vs">
            <a href="${alimama.product_coupn_link}">
                <div class="border" price="${alimama.price}">
                    <div class="item">
                        <div class="content">
                            <img class="item-img" src="${alimama.image}">
                            <div class="item-price">
                                ￥${alimama.price}
                            </div>
                            <div class="item-title">
                                    ${alimama.name}
                            </div>
                            <div class="item-shop">
                            </div>
                            <div class="item-bottom">
                        <span>
                            月成交<em>${alimama.sell_count}笔</em>
                        </span>
                                <span>
                            优惠券剩余<a href="${alimama.coupon_link}" >${alimama.coupon_count}</a>
                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</main>
