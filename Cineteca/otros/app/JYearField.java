package app;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JYearField extends JTextField {
	
	
/*	
 * 1. First create your own input verifier:
 * public class MyInputVerifier extends InputVerifier {
	    @Override
	    public boolean verify(JComponent input) {
	        String text = ((JTextField) input).getText();
	        try {
	            BigDecimal value = new BigDecimal(text);
	            return (value.scale() <= Math.abs(4)); 
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	}
	2. Then assign an instance of that class to your text field. (In fact any JComponent can be verified)

	myTextField.setInputVerifier(new MyInputVerifier());*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isValidYear() {
		String emailReg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return super.getText().matches(emailReg);
	}
	
	public String getYear() {
		if(isValidYear() == true){
			return super.getText().toLowerCase();
		} else {
			return null;
		}
	}

	public JYearField() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c>='0' && c<='9' && getText().length()<4 ) {
					e.setKeyChar(c);
				} else {
					e.setKeyChar('\u0000');
				}
			}
		});
	}
	
}
