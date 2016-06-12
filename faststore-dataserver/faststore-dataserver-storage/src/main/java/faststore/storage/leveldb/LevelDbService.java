package faststore.storage.leveldb;

import faststore.storage.DbService;
import javafx.util.Pair;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public class LevelDbService implements DbService {
    private DBFactory factory;
    private DB db;
    private Charset charset;
    String path = "data/leveldb";

    public LevelDbService() {
    }

    public LevelDbService(String path) {
        this.path = path;
    }

    public void open() throws Exception {
        Charset charset = Charset.forName("utf-8");
        open(path, charset);
    }

    public void open(String path, Charset charset) throws Exception {
        this.charset = charset;
        factory = Iq80DBFactory.factory;
        File dir = new File(path);
        //如果数据不需要reload，则每次重启，尝试清理磁盘中path下的旧数据。
        boolean cleanup = true;
        if (cleanup) {
            factory.destroy(dir, null);//清除文件夹内的所有文件。
        }

        Options options = new Options().createIfMissing(true);
        //重新open新的db
        db = factory.open(dir, options);
    }

    public void close() throws Exception {
        db.close();
    }


    @Override
    public void batchPut(List<Pair<String, String>> pairList, Object object) throws Exception {
        //batch write；
        WriteBatch writeBatch = db.createWriteBatch();
        try {
            for (Pair<String, String> pair : pairList) {
                writeBatch.put(pair.getKey().getBytes(charset), pair.getValue().getBytes(charset));
            }
            db.write(writeBatch);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writeBatch.close();
        }
    }

//    @Override
//    public void get() {
//        //iterator，遍历，顺序读
//
//        //读取当前snapshot，快照，读取期间数据的变更，不会反应出来
//        Snapshot snapshot = db.getSnapshot();
//        //读选项
//        ReadOptions readOptions = new ReadOptions();
//        readOptions.fillCache(false);//遍历中swap出来的数据，不应该保存在memtable中。
//        readOptions.snapshot(snapshot);//默认snapshot为当前。
//        DBIterator iterator = db.iterator(readOptions);
//        while (iterator.hasNext()) {
//            Map.Entry<byte[], byte[]> item = iterator.next();
//            String key = new String(item.getKey(), charset);
//            String value = new String(item.getValue(), charset);//null,check.
//            System.out.println(key + ":" + value);
//        }
//        iterator.close();//must be
//    }


    @Override
    public void delete(String key) {
        //delete
        db.delete(key.getBytes(charset));
        //compaction，手动
//        db.compactRange("key-".getBytes(charset), null);
    }

    @Override
    public void put(String key, Object value) {
        //write
        db.put(key.getBytes(charset), ((String) value).getBytes(charset));

//        //write后立即进行磁盘同步写
//        WriteOptions writeOptions = new WriteOptions().sync(true);//线程安全
//        db.put("key-02".getBytes(charset), "value-02".getBytes(charset), writeOptions);
    }

    @Override
    public Object get(String key) {
        //read
        byte[] bv = db.get(key.getBytes(charset));
        if (bv != null && bv.length > 0) {
            String value = new String(bv, charset);
            return value;
        }
        return null;
    }
}
