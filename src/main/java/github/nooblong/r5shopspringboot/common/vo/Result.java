package github.nooblong.r5shopspringboot.common.vo;

public class Result {

    private int code;
    private String message;
    private String data;

    public Result() {
    }

    public Result(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(String data){
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static Result failed(){
        return new Result(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), null);
    }

    public static Result unauthorized(){
        return new Result(ResultCode.UNAUTHORIZED.getCode(), ResultCode.FAIL.getMessage(), null);
    }

    public static Result forbidden(){
        return new Result(ResultCode.FORBIDDEN.getCode(), ResultCode.FAIL.getMessage(), null);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public enum ResultCode{
        SUCCESS(200,"成功"),
        FAIL(400,"失败"),
        UNAUTHORIZED(401,"未认证"),
        FORBIDDEN(403,"无权限");
        public int code;
        public String message;
        ResultCode(int code, String message){
            this.code = code;
            this.message = message;
        }
        public int getCode(){
            return code;
        }
        public String getMessage(){
            return message;
        }
    }

}
