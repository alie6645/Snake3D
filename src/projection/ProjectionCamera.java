package projection;

import java.awt.geom.Point2D;

public class ProjectionCamera {
    public Vector3 camera = new Vector3(10,10,-10);
    public Vector3 rotation = new Vector3(0, 0, 0);
    Plane screen = new Plane(0,0,0,200);
    public Vector3 normal = new Vector3(0,0,1);

    public Vector3 intersect(Vector3 line, Plane plane){
        return intersect(camera, line, plane);
    }

    public Vector3 intersect(Vector3 start, Vector3 line, Plane plane){
        double a = plane.a;
        double b = plane.b;
        double c = plane.c;
        double x = line.x;
        double y = line.y;
        double z = line.z;
        double d = plane.d;
        double t = d - a * start.x - b * start.y - c * start.z;
        t = t / (a * x - a * start.x + b * y - b * start.y + c * z - c * start.z);
        if (t<0){
            return null;
        }
        return new Vector3(t * (x - start.x), t * (y - start.y), t * (z - start.z));
    }

    public void move(double x, double y, double z){
        Vector3 side = new Vector3(1,0,0);
        Vector3 up = new Vector3(0,1,0);
        Vector3 in = new Vector3(0,0,1);

        side = rotate(side);
        up = rotate(up);
        in = rotate(in);

        camera = VectorMath.add(camera,VectorMath.multiply(side,x));
        camera = VectorMath.add(camera,VectorMath.multiply(up,y));
        camera = VectorMath.add(camera,VectorMath.multiply(in,z));
    }

    public Vector3 rotateZ(Vector3 point, double angle){
        if (angle != 0) {
            double x = point.x;
            double y = point.y;
            return new Vector3(x*Math.cos(angle)-y*Math.sin(angle), x*Math.sin(angle)+y*Math.cos(angle), point.z);
            //point.x = x*Math.cos(angle)-y*Math.sin(angle);
            //point.y = x*Math.sin(angle)+y*Math.cos(angle);
        } else {
            return point;
        }
    }

    public Vector3 rotateX(Vector3 point, double angle){
        if (angle != 0) {
            double z = point.z;
            double y = point.y;
            return new Vector3(point.x, y*Math.cos(angle)-z*Math.sin(angle), y*Math.sin(angle)+z*Math.cos(angle));
            //point.y = y*Math.cos(angle)-z*Math.sin(angle);
            //point.z = y*Math.sin(angle)+z*Math.cos(angle);
        } else {
            return point;
        }
    }

    public Vector3 rotateY(Vector3 point, double angle){
        if (angle != 0) {
            double x = point.x;
            double z = point.z;
            return new Vector3(x*Math.cos(angle)+z*Math.sin(angle), point.y, z*Math.cos(angle)-x*Math.sin(angle));
            //point.x = x*Math.cos(angle)+z*Math.sin(angle);
            //point.z = z*Math.cos(angle)-x*Math.sin(angle);
        } else {
            return point;
        }
    }

    public Vector3 rotate(Vector3 point, double x, double y, double z){
        Vector3 result = new Vector3(point);
        result = rotateX(point, x);
        result = rotateY(result, y);
        result = rotateZ(result, z);
        return result;
    }

    public Vector3 rotate(Vector3 point){
        return rotate(point, rotation.x, rotation.y, rotation.z);
    }

    public Vector3 rotateReverse(Vector3 point){
        Vector3 result = new Vector3(point);
        result = rotateX(point, -rotation.x);
        result = rotateY(result, -rotation.y);
        result = rotateZ(result, -rotation.z);
        return result;
    }

    public void rotateScreen(double x, double y, double z){
        normal = rotate(normal, x, y, z);
        rotation.x += x;
        rotation.y += y;
        rotation.z += z;
        screen.update(normal, normal);
    }

    public Point2D convert(Vector3 point){
        Vector3 line = VectorMath.subtract(point,camera);
        Vector3 intersection = intersect(line,screen);
        if (line.magnitude()>50){
            //return null;
        }
        if (intersection==null){
            return null;
        }
        intersection = rotateReverse(intersection);
        return new Point2D.Double(intersection.x + 200, intersection.y + 200);
    }
}
