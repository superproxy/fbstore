package faststore.framework.processor.biz.common;

public enum RequestCode {
    UNSUPPORTED(-1, "unsupported"),
    HELLO(0, "hello"),
    SET(1, "set"),
    GET(2, "get");


    private int code;
    private String desc;

    private RequestCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int value() {
        return this.code;
    }

    public static RequestCode value(int code) {
        RequestCode[] values = RequestCode.values();
        for (RequestCode v : values) {
            if (v.code == code) {
                return v;
            }
        }
        return null;
    }


}
