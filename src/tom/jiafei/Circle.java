package tom.jiafei;

public class Circle {
    double  Radius;
    public  Circle(){
            Radius=1;
    }

    public double getRadius() {
        return Radius;
    }

    public void setRadius(double radius) {
        Radius = radius;
    }
    public double circleArea(){
        return Math.PI*Radius*Radius;
    }
    public double circlLength(){
        return Math.PI*Radius*2.0;
    }
}
