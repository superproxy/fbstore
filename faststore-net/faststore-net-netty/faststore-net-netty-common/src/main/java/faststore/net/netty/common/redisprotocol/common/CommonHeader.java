package faststore.net.netty.common.redisprotocol.common;

public class CommonHeader {

    public CommonHeader(int cmdCode) {
        this.cmdCode = cmdCode;
    }

    private int cmdCode;

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

}
