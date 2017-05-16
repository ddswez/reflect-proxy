package reflect;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lpf on 17/4/26.
 */
public class HashSetTest {
    public static void main(String[] args) {

        Collection list = new HashSet();
        Person p1 = new Person(18, "aa");
        Person p2 = new Person(19, "bb");
        Person p3 = new Person(19, "bb");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        p1.age = 10;
        list.remove(p1);
        System.out.println(list.size());

    }
}

class Person {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
