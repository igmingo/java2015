package app;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JEmailField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isValidEmail() {
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return super.getText().matches(emailreg);
	}
	
	public String getEmail() {
		if(isValidEmail() == true){
			return super.getText().toLowerCase();
		} else {
			return null;
		}
	}

	public JEmailField() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c>='a' && c<='z' || c>='0' && c<='9' || c=='@' || c=='.') {
					e.setKeyChar(c);
				} else {
					e.setKeyChar('\u0000');
				}
			}
		});
	}
	
}
