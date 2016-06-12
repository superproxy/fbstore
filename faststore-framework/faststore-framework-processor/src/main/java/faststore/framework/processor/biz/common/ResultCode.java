package faststore.framework.processor.biz.common;

public enum ResultCode {
    SUCCESS(0, "success");


    private int code;
    private String desc;

    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int value() {
        return this.code;
    }

    public static ResultCode value(int code) {
        ResultCode[] values = ResultCode.values();
        for (ResultCode v : values) {
            if (v.code == code) {
                return v;
            }
        }
        return null;
    }


}
