package shop.exception;


import shop.controller.RequestCommon;

public class AuthException extends Exception {
    private RequestCommon requestCommon;
    public AuthException(RequestCommon message) {
        super(message.toString());
        this.requestCommon=message;
    }
    public RequestCommon getRequestCommon() {
        return requestCommon;
    }
}
