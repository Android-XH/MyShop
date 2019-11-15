package shop.controller.front;

import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

public class ShowBaseController extends BaseController {
    /**
     * 全局异常处理
     * @return
     */
    @ExceptionHandler
    public String handleException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return "500";
    }
}
