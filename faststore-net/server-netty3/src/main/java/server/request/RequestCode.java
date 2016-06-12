package faststore.net.netty.common.faststoreprotocol.request;

public enum RequestCode {
    SET(1, "set"), HELLO(0, "hello");

    private int code;
    private String desc;

    private RequestCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
