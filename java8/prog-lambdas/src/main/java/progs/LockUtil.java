package progs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by satish on 18/05/14.
 */
public class LockUtil {

    public void withLock(Runnable runnable){

        ReentrantLock lock = new ReentrantLock();

        try{
            runnable.run();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockUtil lockUtil = new LockUtil();

        lockUtil.withLock(() -> {
            //do Something
        });
    }
}
