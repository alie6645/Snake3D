package display.light;

import projection.Vector3;
import projection.VectorMath;

public class PointSource implements Light{
    Vector3 location;
    double distance;
    public PointSource(Vector3 location, double distance ){
        this.location = location;
        this.distance = distance;
    }

    @Override
    public double getLight(Vector3 pos, Vector3 norm) {
        Vector3 direction = VectorMath.subtract(pos, location);
        double intensity = (distance - direction.magnitude()) / distance;
        double modifier = Math.abs(VectorMath.dot(VectorMath.norm(norm),VectorMath.norm(direction))) * intensity;
        return modifier;
    }
}
