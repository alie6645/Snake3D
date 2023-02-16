package display.blob;

import projection.ProjectionCamera;
import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon3D {
    private List<Vector3> points = new ArrayList<>();

    public void addPoint(Vector3 point){
        points.add(point);
    }
    public void draw(Graphics2D g2, ProjectionCamera projection) {
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        int index = 0;
        for (Vector3 point:points){
            Point2D normal = projection.convert(point);
            if (normal != null){
                x[index] = (int) normal.getX();
                y[index] = (int) normal.getY();
                index++;
            } else {
                return;
            }
        }
        g2.fillPolygon(x,y,index);
        g2.setColor(Color.BLACK);
        //g2.drawPolygon(x,y,index);
    }

    public void move(Vector3 distance){
        for (int i=0; i<points.size(); i++){
            points.set(i,VectorMath.add(points.get(i),distance));
        }
    }

    public Vector3 getNormal(){
        return VectorMath.cross(VectorMath.subtract(points.get(0),points.get(1)),VectorMath.subtract(points.get(2),points.get(1)));
    }

    public double getDepth(Vector3 point) {
        Vector3 vertex = VectorMath.multiply(VectorMath.add(points.get(0),points.get(2)),0.5);
        return Math.abs(VectorMath.subtract(vertex,point).magnitude());
    }

    public Vector3 getCenter(){
        Vector3 vertex = VectorMath.multiply(VectorMath.add(points.get(0),points.get(2)),0.5);
        return vertex;
    }
}
