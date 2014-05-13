package lambdas;

/**
 * Created by satish on 13/05/14.
 */
public class UncheckedRunnableTest {

    //This really acts as higher order function. It does take functional interface
    //as argument and return a lambda which can be passed for functional interface.

    public static Runnable andThen(Runnable r1, Runnable r2){
        return () -> {
            r1.run();
            r2.run();
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread( RunnableEx.unchecked( () -> {
            System.out.println( "Zzz");
            Thread.sleep( 1000); }));

        t.start();
        t.join();

        andThen(() -> System.out.println("First one"),
                () -> System.out.println("second one...")).run();
    }
}
