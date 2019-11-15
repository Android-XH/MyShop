package shop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import shop.mode.Category;
import shop.mode.SearchHistory;
import shop.service.CategoryService;
import shop.service.SearchHistoryService;
import shop.util.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  加载搜索栏下面的热搜列表
 */
public class CategoryNamesBelowSearchInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    SearchHistoryService searchHistoryService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (!(o instanceof HandlerMethod)) {
            return true;
        }
//        if (request.getServletContext().getAttribute("searchHistories") != null) {
//            return true;
//        }
        Pagination pagination = new Pagination();
        pagination.setSize(6);
        List<SearchHistory> searchHistories = searchHistoryService.list("depth", 1, "pagination", pagination, "recommend_gt",0,"order","recommend desc");
        request.getServletContext().setAttribute("searchHistories", searchHistories);
        return true;
    }
}
