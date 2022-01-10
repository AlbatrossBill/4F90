package none;

public class MyVar {
//    public int weight = 160;
//    private double gpa = 3.3;
    public String address;
    public MyObj myObj;

//    @Override
//    public String toString() {
//        return "MyVar{" +
//                "weight=" + weight +
//                ", gpa=" + gpa +
//                ", address='" + address + '\'' +
//                ", myObj=" + myObj +
//                '}';
//    }


    @Override
    public String toString() {
        return "MyVar{" +
                "address='" + address + '\'' +
                ", myObj=" + myObj +
                '}';
    }
}
