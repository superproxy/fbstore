package faststore.configserver.service.common;

import java.util.List;

public interface GenericService<T> {
    List<T> find(Object... objects) throws Exception;

    void add(T t) throws Exception;

    void delete(T t) throws Exception;
}
