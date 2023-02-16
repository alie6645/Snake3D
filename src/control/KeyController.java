package control;

import control.nodes.MovingCube;
import projection.ProjectionCamera;
import projection.Vector3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    MovingCube node;
    public KeyController(MovingCube node){
        this.node = node;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            node.setDirection(new Vector3(0,-0.1,0));
        }
        if (e.getKeyChar() == 'a') {
            node.setDirection(new Vector3(0,0,0));
        }
        if (e.getKeyChar() == 's') {
            node.setDirection(new Vector3(0,0,0));
        }
        if (e.getKeyChar() == 'd') {
            node.setDirection(new Vector3(0,0.1,0));
        }
    }
}
