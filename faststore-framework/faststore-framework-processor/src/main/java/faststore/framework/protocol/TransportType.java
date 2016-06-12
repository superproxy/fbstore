package faststore.framework.protocol;

public enum TransportType {
    THRIFT(0, "thrift"), NETTY(1, "netty");

    int index;
    String value;

    private TransportType(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
