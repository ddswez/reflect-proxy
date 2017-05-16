package reflect;


class Box {
    class Inner {
    }
}

public class ReflectTest0 {
    public static void main(String[] args) throws Exception {
        // Ordinary class:
        System.out.println(Box.class.getCanonicalName());
        System.out.println(Box.class.getName());
        // Inner class:
        System.out.println(Box.Inner.class.getCanonicalName());
        System.out.println(Box.Inner.class.getName());
        // Array type:
        System.out.println(args.getClass().getCanonicalName());
        System.out.println(args.getClass().getName());
    }
}

/*
* reflect.Box
reflect.Box
reflect.Box.Inner
reflect.Box$Inner
java.lang.String[]
[Ljava.lang.String;
*/