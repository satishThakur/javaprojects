package domain;

/**
 * Created by satish on 06/05/14.
 */
enum Sex{
    MALE, FEMALE
}

public class Person {
    public final String name;

    public final int age;

    public final Sex sex;

    public Person(String name, int age, Sex sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public boolean isMale(){
        return Sex.MALE == this.sex;
    }

    public boolean canVote(){
        return age >= 18;
    }

    public static boolean canPersonVote(Person p){
        return p.canVote();
    }

    public static boolean isMaleStatic(Person p){
        return p.isMale();
    }



    @Override
    public String toString() {
        return "Name: " + name + ", age: " + age;
    }
}
