package com.ld.multithread.ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
	
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
	
	public BounceFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start",event -> addBall());
		addButton(buttonPanel,"Close",event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
		
		public void addButton(Container c,String title,ActionListener listener){
			JButton button = new JButton(title);
			c.add(button);
			button.addActionListener(listener);
		}
		
		public void addBall(){
				Ball ball = new Ball();
				comp.add(ball);
				
				Runnable r = () -> {
					for(int i = 1;i<=STEPS;i++){
						ball.move(comp.getBounds());
						comp.paint(comp.getGraphics());
						try {
							Thread.sleep(DELAY);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};	
				Thread thread = new Thread(r);
				thread.start();
			}
}
	
