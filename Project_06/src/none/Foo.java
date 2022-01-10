package none;

import java.util.ArrayList;
import java.util.List;

public class Foo{
    private int age;
    public String name;
    protected double grade;
    private ObjVar objVar;
    private MyVar myVar;
    private MyObj myObj;
    private List<MyObj> mys;


    @Override
    public String toString() {
        return "Foo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", objVar=" + objVar +
                ", myVar=" + myVar +
                ", myObj=" + myObj +
                ", mys=" + mys +
                '}';
    }

    public static Foo getInstance () {
        Foo f = new Foo();
        f.name = "Bill";
        f.grade = 80;
        f.age = 23;
        f.objVar = new ObjVar();
        f.objVar.height = 182;

        f.objVar.myVar = new MyVar();
        f.objVar.myVar.myObj = new MyObj();
        f.objVar.myVar.myObj.aDouble = 333;
        f.objVar.myVar.myObj.aString = "f.objVar.myVar.myObj.aString";

        f.myVar = new MyVar();
        f.myVar.myObj = new MyObj();
        f.myVar.myObj.aDouble = 444;
        f.myVar.myObj.aString = "f.myVar.myObj.aString";
        f.myVar.address = "Thorold";

        f.myObj = new MyObj();
        f.myObj.anInt = 1;
        f.myObj.aString = "aString";


        f.mys = new ArrayList<>();
        MyObj m1 = new MyObj();
        m1.anInt = 1;
        m1.aDouble = 1.1;
        m1.aString = "aString1";
        f.mys.add(m1);

        MyObj m2 = new MyObj();
        m2.anInt = 2;
        m2.aDouble = 2.2;
        m2.aString = "aString2";
        f.mys.add(m2);

        MyObj m3 = new MyObj();
        m3.anInt = 3;
        m3.aDouble = 3.3;
        m3.aString = "aString3";
        f.mys.add(m3);

        return f;
    }
}

