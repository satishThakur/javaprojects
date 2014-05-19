package progs;


import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by satish on 18/05/14.
 */
public class LogUtil {

    public static void logIf(Level logLevel, Supplier<String> logInfo){
        Logger logger = Logger.getLogger(LogUtil.class.getName());

        if(logger.isLoggable(logLevel)){
            logger.log(logLevel, logInfo.get());
        }
    }


    public static void main(String[] args) {
        logIf(Level.FINEST, () -> "log me" + " info");
    }
}
