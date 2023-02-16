package projection;

public class VectorMath {
    public static double dot(Vector3 a, Vector3 b){
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vector3 cross(Vector3 a, Vector3 b){
        return new Vector3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public static Vector3 norm(Vector3 a){
        double magnitude = a.magnitude();
        return new Vector3(a.x/magnitude,a.y/magnitude,a.z/magnitude);
    }

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b){
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 multiply(Vector3 a, double num){
        return new Vector3(a.x * num, a.y * num, a.z * num);
    }
}
