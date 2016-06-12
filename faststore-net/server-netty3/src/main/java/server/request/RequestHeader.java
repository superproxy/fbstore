package faststore.net.netty.common.faststoreprotocol.request;

public class RequestHeader {

    public RequestHeader(RequestCode requestCode) {
        this.requestCode = requestCode;
    }

    private RequestCode requestCode;

    public RequestCode getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(RequestCode requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        RequestHeader that = (RequestHeader) object;

        if (requestCode != that.requestCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return requestCode.hashCode();
    }
}
