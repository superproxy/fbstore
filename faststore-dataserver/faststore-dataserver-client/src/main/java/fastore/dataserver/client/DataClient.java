package fastore.dataserver.client;

public interface DataClient {
    void put(String key, Object value) throws Exception;

    Object get(String key) throws Exception;

    void stop() throws Exception;
}
