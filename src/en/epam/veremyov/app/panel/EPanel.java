package en.epam.veremyov.app.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import en.epam.veremyov.core.elevator.Elevator;
import en.epam.veremyov.core.elevator.console.Console;
import en.epam.veremyov.core.person.Person;

@SuppressWarnings("serial")
public class EPanel extends JPanel implements ActionListener{
	// timer
    private Timer timer;
    // settings
    private int SCALE;
	private int WIDTH;
	private int HIGHT;
	private int SPEED;
	// other settings
	private int maxFlr; // max floor in house
	// elevator
	private Console elevator;
	// floor 
	private List<Person>[] floor;
	
	public List<Person>[] getFloor() {
		return floor;
	}
	public void setFloor(List<Person>[] floor) {
		this.floor = floor;
	}

	@SuppressWarnings("unchecked")
	public EPanel(int sCALE, int wIDTH, int hIGHT, int sPEED, 
			int maxFlr, int maxPersFlr, int maxPersElevtr) {
		super();
		this.SCALE = sCALE;
		this.WIDTH = wIDTH;
		this.HIGHT = hIGHT;
		this.SPEED = sPEED;
		this.maxFlr = maxFlr;
		this.elevator = new Elevator(maxFlr, maxPersElevtr, 1, 1);
		this.floor = new List[maxFlr];
		for (int i = 0; i < floor.length; i++) {
			floor[i] = new ArrayList<Person>();
		}
		timer = new Timer(1000/SPEED, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		elevator.move(floor);
		
		
		repaint();
		
	}
	
	@Override
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setColor(new Color(25, 25, 70));
    	g.fillRect(0, 0, WIDTH*SCALE, HIGHT*SCALE);

    	// column
    	g.setColor(new Color(255,216,0));
    	for(int xx=0; xx <= WIDTH*SCALE; xx+=SCALE){
    		g.drawLine(xx, 0, xx, HIGHT*SCALE);
        }
    	// line
    	for(int yy=0; yy <= HIGHT*SCALE; yy+=SCALE){
    		g.drawLine(1, yy,WIDTH*SCALE, yy );
        }
    	
    	// panel
    	g.setColor(Color.black);
    	g.fillRect(0, 0, SCALE*(WIDTH+maxFlr+1), SCALE);
    	g.fillRect(0, SCALE*(maxFlr+1)+1, SCALE*(WIDTH+maxFlr), SCALE*(maxFlr+1));
    	for (int i = 0, k = 3; i <= maxFlr; i++) {
    		g.fillRect(0,i*SCALE+1, SCALE, SCALE);
    		g.fillRect(k*SCALE+1,i*SCALE, SCALE*(maxFlr*WIDTH), SCALE+1);
			
		}
    	
    	g.setColor(Color.red);
    	g.drawString(elevator.getResult(), 10, 20);
    	
    	// elevator
    	g.setColor(new Color(20,216,0));
		g.fillRect(elevator.getX()*SCALE+1,elevator.getY()*SCALE+1, SCALE-1, SCALE-1);
		g.setColor(Color.red);
		// write number people on elevator
		//g.drawString("9",elevator.getX()*SCALE+10,elevator.getY()*SCALE+25);
		
		// floor number
		g.setColor(Color.red);
		for (int i = maxFlr, j = 10, k = 20; i >= 1; i--, k+=33) {
			g.drawString(""+i, j, 1*SCALE+k);
					
		}
		
		// floor people
		g.setColor(Color.yellow);
		g.drawString( "people in floor(start, end):", 4*SCALE+10, 20);
		
		for (int i = maxFlr, j = 3, k = 20; i >= 1; i--, k+=33) {
			if(floor[maxFlr-i] != null)
				g.drawString( floor[maxFlr-i].size()+", "+floor[maxFlr-i].toString() , j*SCALE+10, 1*SCALE+k);
			
		}
		
		g.setColor(Color.green);
		g.drawString( "Elevator: numper, info - person " , 1*SCALE+2, (maxFlr+2)*SCALE);
		for (int j = 0, k = 1; j < elevator.getAllPeople().size(); j++, k++) {
			g.drawString( k+") "+elevator.getAllPeople().get(j).toString(), 1*SCALE+10, (maxFlr+2+k)*SCALE+1);
			
		}
		
    }
	
}
