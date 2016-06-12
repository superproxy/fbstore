package faststore.framework.storage;

public enum StorageType {
    MEM_DB(0, "storage.mem"), LEVEL_DB(1, "storage.leveldb");
    int index;
    String value;

    private StorageType(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
