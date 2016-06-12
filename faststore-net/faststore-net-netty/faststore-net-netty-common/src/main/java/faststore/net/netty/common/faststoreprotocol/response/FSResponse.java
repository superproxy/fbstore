package faststore.net.netty.common.faststoreprotocol.response;

import faststore.net.netty.common.faststoreprotocol.common.FSCommonBody;
import faststore.net.netty.common.faststoreprotocol.common.FSCommonHeader;

public class FSResponse {
    protected FSCommonHeader fsRequestHeaer;
    protected FSCommonBody fsRequestBody;

    public FSCommonBody getFsRequestBody() {
        return fsRequestBody;
    }

    public void setFsRequestBody(FSCommonBody fsRequestBody) {
        this.fsRequestBody = fsRequestBody;
    }

    public FSCommonHeader getFsRequestHeaer() {
        return fsRequestHeaer;
    }

    public void setFsRequestHeaer(FSCommonHeader fsRequestHeaer) {
        this.fsRequestHeaer = fsRequestHeaer;
    }


    @Override
    public String toString() {
        return "Response{" +
                "fsRequestBody=" + fsRequestBody +
                ", fsRequestHeaer=" + fsRequestHeaer +
                '}';
    }
}
