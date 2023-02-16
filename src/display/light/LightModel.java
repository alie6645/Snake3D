package display.light;

import projection.Vector3;

import java.util.ArrayList;
import java.util.List;

public class LightModel {
    List<Light> lights = new ArrayList<>();

    public void addLight(Light light){
        lights.add(light);
    }

    public double getLight(Vector3 pos, Vector3 norm){
        double total = 0;
        for (Light light:lights){
            total += light.getLight(pos, norm);
        }
        return total;
    }

}
