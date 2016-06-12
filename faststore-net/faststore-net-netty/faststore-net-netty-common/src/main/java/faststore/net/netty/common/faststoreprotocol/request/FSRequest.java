package faststore.net.netty.common.faststoreprotocol.request;

import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonHeader;

public class FSRequest {
    FSCommonHeader FSCommonHeader;
    FSCommonBody FSCommonBody;

    public FSCommonBody getFSCommonBody() {
        return FSCommonBody;
    }

    public void setFSCommonBody(FSCommonBody FSCommonBody) {
        this.FSCommonBody = FSCommonBody;
    }

    public FSCommonHeader getFSCommonHeader() {
        return FSCommonHeader;
    }

    public void setFSCommonHeader(FSCommonHeader FSCommonHeader) {
        this.FSCommonHeader = FSCommonHeader;
    }
}
