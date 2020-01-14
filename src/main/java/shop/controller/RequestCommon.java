package shop.controller;



public enum RequestCommon {

    SUCCESS_TEST(200,"接口调用成功！"),

    SUCCESS_ENSHRINE(200,"商品已加入收藏夹！"),
    SUCCESS_DELETE(200,"删除成功！"),
    SUCCESS_ADD(200,"添加成功！"),

    SUCCESS_UPDATA(200,"服务器更新中...！"),

    NULL_OBJ(201,"对象为空"),
    ERROR_PARAMETER(202,"参数错误"),
    SYSTEM_ERROR(203,"系统异常，请稍后再试...."),

    ERROR_ADD_USER(300,"添加用户失败"),
    ERROR_ADD_PHONE(301,"手机号码错误"),
    ERROR_PRODUCT_NOT_FOUND(302,"该商品不存在或已下架！"),
    ERROR_PARAM_NOT_FOUND(303,"访问参数不正确！"),
    ERROR_ID_NOT_FOUND(304,"该对象不存在或已删除！"),

    ERR_AUTH(401,"非法访问，没有权限"),
    AUTH_ERR(400,"Token无效"),
    AUTH_RATE(402,"用户权限不足，请联系管理员！"),
    AUTH_MISSING(403,"用户未登陆，请先登录！"),
    AUTH_TIME_OUT(403,"用户Token已失效！"),
    USER_MISSING(404,"该用户不存在！"),
    USER_IS_EXIST(406,"用户名已存在！"),
    ERR_USER_PASS_WORD(405,"用户名或密码错误！");



    private int code;
    private String message;

    private RequestCommon(int code, String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestCommon{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
