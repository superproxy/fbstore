package faststore.net.netty.common.faststoreprotocol.response;

public class ResponseBody {

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "message='" + message + '\'' +
                '}';
    }
}
