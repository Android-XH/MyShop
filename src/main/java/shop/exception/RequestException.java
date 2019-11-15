package shop.exception;


import shop.controller.RequestCommon;

public class RequestException extends Exception {
    private RequestCommon requestCommon;
    public RequestException(RequestCommon requestCommon) {
        super(requestCommon.toString());
        this.requestCommon=requestCommon;
    }

    public RequestCommon getRequestCommon() {
        return requestCommon;
    }
}
