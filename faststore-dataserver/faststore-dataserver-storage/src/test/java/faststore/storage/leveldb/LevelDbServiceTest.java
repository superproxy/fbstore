package faststore.storage.leveldb;

import org.testng.annotations.Test;

/**
 * Created by 14120295 on 2016/6/8.
 */
public class LevelDbServiceTest {

    LevelDbService levelDbService;

    @Test
    public void test() throws Exception  {

        levelDbService = new LevelDbService("/data/leveltest");
        levelDbService.open();
        doPut();
        doGet();

    }

    private void doPut() {
        int n = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            levelDbService.put("key-" + i, "value-" + i);
        }

        System.out.println("write total-ms:" + (System.currentTimeMillis() - start));
        System.out.println("write:tps" + n / ((System.currentTimeMillis() - start) / 1000));
    }


    private void doGet() throws Exception {
        int n = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Object objet = levelDbService.get("key-" + i);
            if (objet != null) {
//                System.out.println(objet.toString());
            }
        }

        long  time = (System.currentTimeMillis() - start);
        System.out.println("read total-ms:" + time);
        System.out.println("read:tps" + n / (time / 1000));
    }

}