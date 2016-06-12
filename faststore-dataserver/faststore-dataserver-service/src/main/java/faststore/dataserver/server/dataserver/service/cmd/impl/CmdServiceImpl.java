package faststore.dataserver.server.dataserver.service.cmd.impl;

import faststore.dataserver.server.dataserver.config.DataServerConfig;
import faststore.dataserver.server.dataserver.service.cmd.CmdService;
import faststore.storage.DbService;
import faststore.storage.DbServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdServiceImpl implements CmdService {

    private final static Logger logger = LoggerFactory.getLogger(CmdServiceImpl.class);

    private DbService dbService = DbServiceFactory.getDbService(DataServerConfig.getInstance().getStorageType());

    public CmdServiceImpl() {
        try {
            dbService.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Object object) {

        long start = System.currentTimeMillis();

        logger.info("key:{}, object{}", key, object);

        try {
            dbService.put(key, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("put time ms", System.currentTimeMillis() - start);
    }

    @Override
    public Object get(String key) {
        long start = System.currentTimeMillis();
        Object value;
        try {
            value = dbService.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.debug("get time ms", System.currentTimeMillis() - start);
        return value;

    }
}