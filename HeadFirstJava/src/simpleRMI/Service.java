package simpleRMI;

import java.io.Serializable;

import javax.swing.JPanel;

public interface Service extends Serializable{
	public JPanel getGuiPanel();
}
