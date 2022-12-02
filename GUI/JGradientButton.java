package GUI;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

final class JGradientButton extends JButton{

	private Color mDarker;
	private Color mLighter;
	
	public JGradientButton(Color darker, Color lighter){
        mDarker = darker;
        mLighter = lighter;
        setContentAreaFilled(false);
    }

	 @Override
     protected void paintComponent(Graphics g) {
         final Graphics2D g2 = (Graphics2D) g.create();
         g2.setPaint(new GradientPaint(
                 new Point(0, 0), 
                 mDarker, 
                 new Point(0, getHeight()/3), 
                 mLighter));
         g2.fillRect(0, 0, getWidth(), getHeight()/3);
         g2.setPaint(new GradientPaint(
        		 new Point(0, getHeight()/2), 
        		 mLighter, 
                 new Point(0, getHeight()), 
                 mDarker));
         g2.fillRect(0, getHeight()/2, getWidth(), getHeight());
         g2.dispose();

         super.paintComponent(g);
     }

}
