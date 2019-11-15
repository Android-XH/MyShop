package shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.controller.BaseController;
import shop.controller.RequestBean;
import shop.controller.RequestCommon;
import shop.exception.AuthException;
import shop.exception.RequestException;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("api/")
public class ApiBaseController extends BaseController {

    /**
     * 全局异常处理
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public RequestBean ApihandleExceptions(HttpServletRequest request, Exception exception) {
        if(exception instanceof RequestException){
            RequestException parameterException= (RequestException) exception;
           return RequestBean.Err(parameterException.getRequestCommon());
        }else if(exception instanceof AuthException){
            AuthException authException= (AuthException) exception;
            return RequestBean.Err(authException.getRequestCommon());
        }else{
            System.out.println(exception);
            return RequestBean.Err(RequestCommon.SYSTEM_ERROR);
        }
    }
}
