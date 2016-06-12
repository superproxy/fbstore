package faststore.configserver.api;

import java.io.Serializable;

public class RequestInfo<T> implements Serializable {

    private String version;
    private String group;

    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public RequestInfo() {
    }

    public RequestInfo(String group, String version) {
        this.group = group;
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
