package faststore.storage;

import faststore.storage.leveldb.LevelDbService;
import faststore.storage.memdb.MemDbService;
import faststore.framework.storage.StorageType;

public class DbServiceFactory {
    private static final LevelDbService levelDbService = new LevelDbService();
    private static final MemDbService memDbService = new MemDbService();

    public static DbService getDbService(StorageType storageType) {
        switch (storageType) {
            case MEM_DB:
                return memDbService;
            case LEVEL_DB:
                return levelDbService;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
