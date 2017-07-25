package en.epam.veremyov.app;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import en.epam.veremyov.app.panel.EPanel;
import en.epam.veremyov.core.generator.Generator;

public class Main {
	// settings
	private static final int maxFlr = 9; // max floor in house
	private static final int maxPersFlr = 3; // max people on 1 floor
	private static final int maxPersElevtr = 5;// max people on elevator
	
	private static final int SCALE = 32;
	private static int WIDTH = 3*maxFlr;//40
	private static int HIGHT = 2*maxFlr;
	private static final int SPEED = 1000;
	
	public static void main(String[] args) {
		JFrame elevator = new JFrame();
		EPanel panel = new EPanel(SCALE, WIDTH, HIGHT, SPEED, maxFlr, maxPersFlr, maxPersElevtr);
		GUI(elevator, panel);
		
		Generator generator = new Generator(maxFlr, maxPersFlr, panel);
		Random rand = new Random();
		while (true) {
			Thread thread = new Thread(generator);
			thread.start();
			
			try {
				Thread.sleep(rand.nextInt(SPEED));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private static void GUI(JFrame jFrame, EPanel panel) {
		jFrame.setTitle("Elevator");
		jFrame.dispose();//-
		//jFrame.setUndecorated(true);//-
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		jFrame.setSize(WIDTH*SCALE+8, HIGHT*SCALE-1);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setLayout(new BorderLayout());
		jFrame.add(panel, BorderLayout.CENTER);
		
		
	}
	
}
