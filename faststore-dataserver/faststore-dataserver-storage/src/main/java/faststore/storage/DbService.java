package faststore.storage;

import javafx.util.Pair;

import java.util.List;

public interface DbService {

    void open() throws Exception;

    void put(String key, Object object) throws Exception;

    Object get(String key) throws Exception;

    void delete(String key) throws Exception;

    void batchPut(List<Pair<String, String>> pairList, Object object) throws Exception;
}
