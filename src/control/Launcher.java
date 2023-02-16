package control;

import control.nodes.MovingCube;
import display.blob.Mesh;
import projection.Vector3;
import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400,400));
        Panel panel = new Panel();



        Mesh board = new Mesh(new Vector3(-20,30,10),new Vector3(30,0,0),new Vector3(0,0,30),10,10);
        panel.add(board);

        MovingCube node = new MovingCube(new Vector3(0,20,10),10);
        panel.add(node);

        panel.addPointLight(new Vector3(0,0,100),500);
        panel.addPointLight(new Vector3(50,10,100), 500);
        panel.addPointLight(new Vector3(-20,-20,100),1000);
        panel.addAmbient(0.2);

        frame.add(panel);
        panel.projection.rotateScreen(0,0,0);

        frame.setVisible(true);

        KeyController controller = new KeyController(node);
        frame.addKeyListener(controller);

        Timer timer = new Timer(50, e -> {
            panel.update();

        });

        timer.start();
    }
}
