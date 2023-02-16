package control.nodes;

import control.Updatable;
import display.blob.Cube;
import display.blob.Polygon3D;
import projection.Vector3;
import projection.VectorMath;

public class MovingCube extends Cube implements Updatable {

    Vector3 direction = new Vector3(0.1,0.1,0);

    public MovingCube(Vector3 pos, double length) {
        super(pos, length);
    }

    public void move(Vector3 distance){
        for (Polygon3D poly:getPolygons()){
            poly.move(distance);
        }
    }


    @Override
    public void update() {
        move(direction);
    }

    public void setDirection(Vector3 direction){
        this.direction = direction;
    }
}
