<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/product.js"></script>

<section class="property">
    <div class="gallery">
        <div class="pic-border">
            <img src="${product.pict_url}" id="big-show">
        </div>
        <div class="pic-ls" id="pic-ls">
            <img src="${product.pict_url}" big-image="${product.pict_url}" class="sm-image selected">
            <C:forEach items="${product.productImages}" var="img" varStatus="vs">
                <img src="${img.pic_url}" big-image="${img.pic_url}" class="sm-image ${vs.count == 1?'selected':''}">
            </C:forEach>
        </div>
        <div id="div4load" style="display:none;"></div>
    </div>
    <div class="detail">
        <div class="product-title">
            ${product.title}
        </div>
        <div class="price">
            <div class="sales">
                商品来源：${product.shop.user_type==0?"淘宝":"天猫"}
            </div>
            <div class="middle-line">
                <span class="s-price left">优惠券</span>
                <span class="s-price-num">${product.coupon.coupon_info}</span>
            </div>
            <div class="top-line">
                <span class="o-price left">价格</span>
                <span class="o-price-num">￥${product.price}</span>
                <span class="o-price-num-noline"><fmt:formatNumber value='${product.price-product.coupon.coupon_amount}' pattern='#0.0'/></span>
            </div>
        </div>
        <div class="history">
            <span class="m-sales item">月销量 <em>${product.volume}</em></span>
            <span class="c-num item">开始日期 <em>${product.coupon.coupon_start_time}</em></span>
            <span class="c-num item">截止日期 <em>${product.coupon.coupon_end_time}</em></span>
        </div>
        <div class="product-num">
            <span class="inventory">总量${product.coupon.coupon_total_count}张</span>
            <span class="inventory">库存${product.coupon.coupon_remain_count}张</span>
        </div>
        <div class="buy">
            <a href="${product.coupon.coupon_share_url}" class="display:none;"> <button class="buy-button">领券购买</button></a>
            <a href="${product.url}" class="display:none;">
                <button class="car-button"><span class="glyphicon glyphicon-shopping-cart"></span>立即购买</button>
            </a>
        </div>
    </div>
</section>
