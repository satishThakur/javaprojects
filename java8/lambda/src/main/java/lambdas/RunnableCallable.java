package lambdas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by satish on 25/05/14.
 */
public class RunnableCallable {

    ExecutorService ex = Executors.newCachedThreadPool();

    public void executeInCallable(){
        ex.submit(() -> {
           System.out.println("In callable...");
            TimeUnit.MILLISECONDS.sleep(5);
            return null;
        });
    }


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            System.out.println("Running...");

        });

        t.join();




    }
}
