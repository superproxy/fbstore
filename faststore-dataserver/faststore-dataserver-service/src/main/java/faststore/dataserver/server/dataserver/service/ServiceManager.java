package faststore.dataserver.server.dataserver.service;

import faststore.dataserver.server.dataserver.service.cmd.CmdService;
import faststore.dataserver.server.dataserver.service.cmd.impl.CmdServiceImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {
    private static Map<Class, Object> classTMap = new ConcurrentHashMap<Class, Object>();


    public static void put(Class c, Object t) {
        classTMap.put(c, t);
    }

    public static <T> T get(Class<T> c) {
        return (T) classTMap.get(c);
    }

    static  {
        put(CmdService.class, new CmdServiceImpl());
    }

}
