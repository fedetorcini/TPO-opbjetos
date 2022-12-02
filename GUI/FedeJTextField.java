package GUI;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FedeJTextField extends JTextField {

	private String DEFAULT_VALUE;

	public FedeJTextField (int x, int y, int width, int height , String defaultValue)
	{
			super();
			DEFAULT_VALUE = defaultValue;
			setFont(new Font("TimesRoman", Font.ITALIC, 15));
			setBounds( x, y, width, height);
			setHorizontalAlignment(JTextField.CENTER);
		
			setText(defaultValue);
			addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	setText("");
	        }
	        @Override
	        public void focusLost(FocusEvent e){
	        	String res = getText();
	        	if (res.equals(""))
	        	{	        		
	        		setText(defaultValue);
	        	}
	        }
	    });
	}

	public void reset() 
	{
		setText(DEFAULT_VALUE);
	}
	
}
