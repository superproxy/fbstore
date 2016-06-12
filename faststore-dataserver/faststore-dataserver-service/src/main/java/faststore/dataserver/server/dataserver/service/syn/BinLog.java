package faststore.dataserver.server.dataserver.service.syn;

import java.util.Date;

public class BinLog {
    //    如果需要从某个点恢复到某个点，用以下操作
    private Date startDt;
    private Date endDt;
    private long startPosition;
    private long endPosition;
    private String cmd;
    private Object object;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }
}
