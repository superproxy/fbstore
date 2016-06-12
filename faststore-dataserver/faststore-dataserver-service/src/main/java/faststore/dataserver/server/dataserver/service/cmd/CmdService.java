package faststore.dataserver.server.dataserver.service.cmd;

public interface CmdService {
    void put(String key, Object object);
    Object get(String key);
}
