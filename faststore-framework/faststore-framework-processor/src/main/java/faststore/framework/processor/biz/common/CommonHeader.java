package faststore.framework.processor.biz.common;

public class CommonHeader {

    public CommonHeader(int cmdCode) {
        this.cmdCode = cmdCode;
    }

    private int cmdCode;
    private int resultCode;
    private String resultMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(int cmdCode) {
        this.cmdCode = cmdCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CommonHeader that = (CommonHeader) object;

        if (cmdCode != that.cmdCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cmdCode;
    }

    @Override
    public String toString() {
        return "CommonHeader{" +
                "cmdCode=" + cmdCode +
                '}';
    }
}
