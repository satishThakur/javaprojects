package lambdas;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by satish on 13/05/14.
 */
public interface Collection2<E> extends Collection<E> {

    default void forEachIf(Consumer<E> consumer, Predicate<E> predicate){
        forEach(e -> {
            if(predicate.test(e))
                consumer.accept(e);
        });

    }
}
