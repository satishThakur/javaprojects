package lambdas;

/**
 * Created by satish on 13/05/14.
 */

@FunctionalInterface
public interface RunnableEx{

    public void run() throws Exception;

    public static Runnable unchecked(RunnableEx runnable){
        return () -> {
          try{
              runnable.run();
          }catch(Exception ex){

          }
        };

    }

}
