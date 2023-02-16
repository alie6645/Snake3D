package control;

import display.ColorModifier;
import display.blob.Blob3D;
import display.blob.Polygon3D;
import display.light.AmbientSource;
import display.light.DirectionalSource;
import display.light.LightModel;
import display.light.PointSource;
import display.shape.Line3D;
import display.shape.Shape3D;
import projection.ProjectionCamera;
import projection.Vector3;
import projection.VectorMath;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Panel extends JComponent {

    ArrayList<Shape3D> shapes = new ArrayList<Shape3D>();
    ArrayList<Blob3D> polygons = new ArrayList<>();
    ArrayList<Updatable> updates = new ArrayList<Updatable>();
    ProjectionCamera projection = new ProjectionCamera();
    LightModel lighting = new LightModel();

    public void add(Shape3D shape){
        shapes.add(shape);
    }

    public void add(Blob3D blob){
        if (blob instanceof Updatable){
            updates.add((Updatable) blob);
        }
        polygons.add(blob);
    }

    public void addPointLight(Vector3 location, double distance){
        lighting.addLight(new PointSource(location,distance));
    }

    public void addAmbient(double intensity){
        lighting.addLight(new AmbientSource(intensity));
    }

    public void addDirectionalLight(Vector3 direction){
        lighting.addLight(new DirectionalSource(direction));
    }

    public void update(){
        for (Updatable node:updates){
            node.update();
            repaint();
        }
    }

    public void sortBlobs(Vector3 cam){
        polygons.sort(new Comparator<Blob3D>() {
            @Override
            public int compare(Blob3D o1, Blob3D o2) {
                Vector3 vec1 = VectorMath.subtract(o1.getCenter(),cam);
                Vector3 vec2 = VectorMath.subtract(o2.getCenter(),cam);
                return (int) (vec2.magnitude() - vec1.magnitude());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GRAY);
        g2.fillRect(0,0,1000,1000);
        for (Shape3D shape:shapes){
            List<Line3D> lines = shape.getLines();
            g2.setColor(shape.getColor());
            for (Line3D line:lines){
                line.draw(g2, projection);
            }
        }
        sortBlobs(projection.camera);
        for (Blob3D blob:polygons){
            blob.depthSort(projection.camera);
            Color main = blob.getColor();
            g2.setColor(blob.getColor());
            List<Polygon3D> surfaces = blob.getPolygons();
            for (Polygon3D poly:surfaces){
                //double modifier = Math.abs(VectorMath.dot(VectorMath.norm(poly.getNormal()),VectorMath.norm(projection.normal)));
                double modifier = lighting.getLight(poly.getCenter(),poly.getNormal());
                g2.setColor(ColorModifier.multiply(main,modifier));
                poly.draw(g2, projection);
            }
        }
    }
}
