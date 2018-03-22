public class Main {
    public static String testTriangle(int a,int b,int c){
        if(a<=0 || b<=0 || c<=0){
            return "illegal triangle";
        }else if(a+b>c && a+c>b && b+c>a){
            if(a==b && a==c){
                return "equilateral triangle";
            }else if(a==b || b==c || c==a){
                return "isosceles triangle";
            }else{
                return "scalene triangle";
            }
        }else{
            return "illegal triangle";
        }
    }
}
