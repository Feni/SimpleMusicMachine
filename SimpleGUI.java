import javax.swing.*;
import java.awt.*;

public class SimpleGUI extends JFrame implements Runnable{
	public SimpleGUI(){
		setVisible(true);
		setBounds(100,100,325,200);
		createBufferStrategy(2);
		new Thread(this).start();
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(100);

				Graphics g = getBufferStrategy().getDrawGraphics();
				g.fillRect(10,10,100,100);
				//g.dispose();
				getBufferStrategy().show();
			}
			catch(Exception e){
				System.out.println(e); 
			}
		}
	}
}