package lmu.cmsi281.assignments;

public class Foo {
    int foo;
    
    public Foo(int f) {
        foo = f;
    }
    
    public int bar(Foo f, int d) {
        Foo b = f;
        b.foo = b.foo+d;
        b = new Foo(d);
        f = b;
        f.foo = d+b.foo;
        return b.foo;
    } 
    
    public static void main(String[] args) {
        int d = 100;
        Foo f = new Foo(d);
        int b = f.bar(f, d+10);
        System.out.println(f.foo);
        System.out.println(d);
        System.out.println(b);      
    }
}

