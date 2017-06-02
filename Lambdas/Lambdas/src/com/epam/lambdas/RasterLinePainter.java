package com.epam.lambdas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.epam.lambdas.GraphPanel;

public class RasterLinePainter extends JFrame {	
	private static final long serialVersionUID = -34022707112639322L;
	
	private static final int WIDHT  = 500;
	private static final int HEIGHT = 500;
	
	private static final float DEF_A = 0.25f;
	private static final float DEF_B = 1.3f;
	    
    JPanel pFunc;    
    GraphPanel pGraph;
    JLabel ly;
    JLabel lsign;
    JTextField a_param;
    JTextField b_param;
    JButton go;
	
	public static void main(String[] args) {
      new RasterLinePainter();
	}
	
	public RasterLinePainter(){
		super();
		
		BorderLayout borderlayout = new BorderLayout();
        Container panel = getContentPane();        
        panel.setLayout(borderlayout);
        pFunc = new JPanel(new FlowLayout());
        ly = new JLabel("f(x) = ");
        lsign = new JLabel(" * x + ");                
        a_param = new JTextField(3);
        a_param.setText(String.valueOf(DEF_A));
        b_param = new JTextField(3);
        b_param.setText(String.valueOf(DEF_B));
        go = new JButton("Go!");
        
        go.addActionListener(e -> {
        	  float a = Float.parseFloat(a_param.getText());
        	  float b = Float.parseFloat(b_param.getText());
        	  pGraph.setA(a);
        	  pGraph.setB(b);
        	  pGraph.repaint();
          });
        
        pFunc.add(ly);
        pFunc.add(a_param);
        pFunc.add(lsign);
        pFunc.add(b_param);
        pFunc.add(go);
        
        pGraph = new GraphPanel(DEF_A, DEF_B);
        panel.add(pFunc, BorderLayout.PAGE_START);        
        panel.add(pGraph, BorderLayout.CENTER);
		
        setSize(WIDHT,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);   
        setTitle("Bresenham\'s line algorithm");
                               
        setVisible(true);                
   }        
		

}
