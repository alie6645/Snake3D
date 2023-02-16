package display.blob;

import projection.Vector3;

import java.awt.*;
import java.util.List;

public interface Blob3D {
    public Color getColor();
    public List<Polygon3D> getPolygons();
    public void depthSort(Vector3 cam);

    public Vector3 getCenter();
}
