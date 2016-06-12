package faststore.configserver.service.zk;

import java.util.List;

public interface ZkGenericService<T> {
    <T> void add(String path, T object) throws Exception;

    void delete(String path) throws Exception;

    <T> List<T> find(String path, String path2, Class<T> t) throws Exception;
}
