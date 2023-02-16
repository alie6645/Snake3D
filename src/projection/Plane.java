package projection;

public class Plane {
    double a;
    double b;
    double c;
    double d;

    public Plane(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void update(Vector3 norm, Vector3 point){
        this.a = norm.x;
        this.b = norm.y;
        this.c = norm.z;
        this.d = VectorMath.dot(norm,point)*200;
    }

    public void move(Vector3 norm, Vector3 point){
        this.a = norm.x;
        this.b = norm.y;
        this.c = norm.z;
        this.d = VectorMath.dot(norm,point);
    }

    public String toString(){
        return "a: " + a + ", b: " + b + ", c: " + c + ", d: " + d;
    }
}
