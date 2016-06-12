package faststore.storage.memdb;

import javafx.util.Pair;
import faststore.storage.DbService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemDbService implements DbService {

    private Map<String, Object> db = new HashMap<String, Object>();

    @Override
    public void open() throws Exception {

    }

    @Override
    public void put(String key, Object object) {
        db.put(key, object);
    }

    @Override
    public Object get(String key) {
        return db.get(key);
    }

    @Override
    public void delete(String key) throws Exception {
        db.remove(key);
    }

    @Override
    public void batchPut(List<Pair<String, String>> pairList, Object object) throws Exception {
        for (Pair<String, String> pair : pairList) {
            db.put(pair.getKey(), pair.getValue());
        }
    }
}
