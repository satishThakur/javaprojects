package lambdas;

/**
 * Created by satish on 13/05/14.
 */

interface J{

    default void f(){

    }

}

interface I{
    default void f(){

    }
}

class Text implements I,J{
    public void f(){
        I.super.f();
    }
}

public class MultipleInheritance {

    public static void main(String[] args) {

    }
}
