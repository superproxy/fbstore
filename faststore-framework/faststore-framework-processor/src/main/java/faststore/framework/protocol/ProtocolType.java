package faststore.framework.protocol;

public enum ProtocolType {
    THRIFT(0, "protocol.thrift"), REDIS(1, "protocol.redis"),
    FASTSTORE(2, "protocol.faststore");


    int index;
    String value;

    private ProtocolType(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
