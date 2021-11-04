package Utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DisplayUtil {
	private static DisplayUtil instance;
	
	public static DisplayUtil getInstance() {
		if(instance == null)
			instance = new DisplayUtil();
		
		return instance;
	}
	
	public Dimension getScreenSize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		return size;
	}

}
