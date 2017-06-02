package com.epam.lambdas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int PWIDHT;
	private int PHEIGHT; 	
    private static final int SCALE  = 10; 
    
    BasicStroke axis = new BasicStroke(3);
    BasicStroke other = new BasicStroke(1);
    
    int cX;
    int cY;
    
    private float a;
    private float b;
    
    public GraphPanel(float a, float b) {
    	super();    	
    	setA(a);
    	setB(b);
    }
    
    @Override
    public Dimension getPreferredSize() {
		return new Dimension(500,400);    	
    }
    
    public void setA(float a) {
    	this.a = a;
    }
    
    public void setB(float b) {
    	this.b = b;
    }

	
	@Override
    protected void paintComponent(Graphics g) {	   
	   super.paintComponent(g);
	   int corSize = (this.getWidth() / SCALE) % 2 != 0 ? ((this.getWidth() / SCALE) + 1) * SCALE : this.getWidth();   
	   PWIDHT = corSize;
	   corSize = (this.getHeight() / SCALE) % 2 != 0 ? ((this.getHeight() / SCALE) + 1) * SCALE : this.getHeight();
   	   PHEIGHT = corSize;
	   Graphics2D g2 = (Graphics2D) g; 
	   drawMatrix(g2);
	   drawFunction(g2);	   	   	   
    }
		   
    private void drawFunction(Graphics2D g2) {
       int[] cx = new int[] {(-cX + SCALE) / SCALE, (cX - SCALE) / SCALE};             
       double[] cy = Arrays.stream(cx)    		           
	                   .mapToDouble( i -> Double.valueOf((a * (Integer)i) + b))
	                   .toArray();            
       
       int[] calcY =  Arrays.stream(cy)
    		            .mapToInt( i -> {
    		            	int iy = Double.valueOf((i * 10)).intValue();
    		            	return Double.valueOf(i).compareTo(Double.valueOf(0)) < 1 ? cY + Math.abs(iy) : cY - Math.abs(iy);
    		            }).toArray();
              
       g2.drawLine(SCALE, calcY[0], PWIDHT - SCALE, calcY[1]);      
	   drawRaster(g2, cx[0], cx[1], cy[0], cy[1]);
    }
   
   private void drawRaster(Graphics2D g2, int x1, int x2, Double y1, Double y2) {
	   int deltax = Math.abs(x2 - x1);
	   double deltay = Math.abs(y2.doubleValue() - y1.doubleValue());
	   double[] error = new double[] {0};
	   double deltaerr = deltay/deltax;
	   double[] cy = new double[] {y1.doubleValue()};	   	   
	   IntStream
	      .iterate(x1, i -> i + 1)
	      .limit(Math.abs(x2)+Math.abs(x1)+1)
	      .forEachOrdered(i -> {	    	  	          	         
	    	  fillRect(g2, i, cy[0], true);
	    	  error[0] += deltaerr;
	    	  if (error[0] >= 0.5) {
	    		cy[0] = a < 0 ? cy[0]-1 : cy[0]+1;
	    		error[0] = error[0] - 1;
	    	  }	    	  
	      });
   }
   
   private void fillRect(Graphics2D g2, int cx, double cy, boolean bSign) {
	  int calcX = cx < 0 ? cX-Math.abs(cx) * SCALE : cX + cx * SCALE;
	  int calcY = cy < 0 ? cY + Math.abs(Double.valueOf(cy).intValue())*10 : (cY - Double.valueOf(cy).intValue()*10);
	  g2.fillRect(calcX, cy < 0 ? calcY : calcY-SCALE, SCALE, SCALE);	 
   }
   
   private void drawMatrix(Graphics2D g2) {
	   IntStream
	      .iterate(1, i -> i + 1)
	      .limit(PWIDHT/SCALE)
	      .map(i -> i * SCALE)	      
	      .forEach(i -> drawXLine(g2, i));
	   
	   IntStream
	      .iterate(1, i -> i + 1)
	      .limit(PHEIGHT/SCALE)
	      .map(i -> i * SCALE)	      
	      .forEach(i -> drawYLine(g2, i));
	   	  
   }
   
   private void drawXLine(Graphics2D g2, int cx) {
	   g2.setColor(Color.BLACK);	   
	   if (Math.abs(((PWIDHT / SCALE) * SCALE)/2 - cx) == 0) {
		   g2.setStroke(axis);
		   cX = cx;
	   } else {
		   g2.setStroke(other);
	   }
	   g2.drawLine(cx, 1, cx, PHEIGHT-1);
   }
   
   private void drawYLine(Graphics2D g2, int cy) {
	   g2.setColor(Color.BLACK);
	   boolean isCenter = Math.abs(((PHEIGHT / SCALE) * SCALE)/2 - cy) == 0;			   
	   if (isCenter) {
		   g2.setStroke(axis);
		   cY = cy;
	   } else {
		   g2.setStroke(other);
	   }
	   g2.drawLine(1, cy, PWIDHT-1, cy);
   }

}
