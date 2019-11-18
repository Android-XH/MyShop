<%--
  Created by IntelliJ IDEA.
  User: xen
  Date: 2017/12/3
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<div class="pagination_bar">
    <div class="container text-center">
        <nav aria-label="...">
            <ul class="pagination">
                <li class="page-item ${pagination.hasPrevious ? '':'disabled'}">
                    <a class="page-link" href="?page=1${pagination.param}" tabindex="-1">«</a>
                </li>
                <li class="page-item ${pagination.hasPrevious ? '':'disabled'}">
                    <a class="page-link" href="?page=${pagination.page-1<0?0:pagination.page-1}${pagination.param}"
                       tabindex="-1">‹</a>
                </li>
<%--                <div>${((pagination.page>9)?(pagination.page-5):1)}</div>--%>
                <c:forEach begin="${((pagination.page>9)?(pagination.page-5):0)}" end="${pagination.page>9?((pagination.page+5)>=pagination.totalPage?pagination.totalPage:pagination.page+5):(pagination.totalPage>9?9:pagination.totalPage)}" varStatus="vs">
                    <li class="page-item ${((pagination.page>9)?(pagination.page-5):0)+vs.count==pagination.page? 'active':''}">
                        <a class="page-link" href="?page=${((pagination.page>9)?(pagination.page-5):0)+vs.count}${pagination.param}">
                                ${((pagination.page>9)?(pagination.page-5):0)+vs.count}
                        </a>
                    </li>
                </c:forEach>


                <li class="page-item ${pagination.hasNext ? '':'disabled'}" >
                    <a class="page-link" href="?page=${pagination.page+1}${pagination.param}&sort=${param.sort}">›</a>
                </li>
                <li class="page-item ${pagination.hasNext ? '':'disabled'}" >
                    <a class="page-link" href="?page=${pagination.lastPage}${pagination.param}">»</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
