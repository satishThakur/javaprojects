package generator;

import com.sun.tools.javac.jvm.Gen;

import java.util.Random;
import java.util.function.Function;

/**
 * Created by satish on 22/05/14.
 */
public interface Generator<T> {

    public T generate();

    default <U> Generator<U> map(Function<T,U> mapper){
        return () -> mapper.apply(this.generate());
    }

    default <U> Generator<U> flatMap(Function<T, Generator<U>> mapper){
        return () -> mapper.apply(this.generate()).generate();
    }
}




