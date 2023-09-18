package quickExecutionAppPackage;

import java.awt.AWTException;
import java.awt.Robot;

public class ActionExecutor extends Main {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 0: move mouse
	 * 1: mouse press / release and hold
	 * 2: mouse click (press and then release)
	 * 3: key press / release and hold
	 * 4: key click (press and then release)
	 * 5: add delay (keyCode is the amount in ms)
	 * 6: add repeater (keyCode is the amount of time to repeat, mouseCode is array number)
	 */
	
	Robot r;
	
	public ActionExecutor() {
		
		try {
			r = new Robot();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
	
	public void execute(int code, int keyCode, int mouseCode, int mouseX, int mouseY, int pressCode) {
		
		boolean press = false;
		
		if(pressCode == 2)
			press = true;
		
		switch(code) {
		
			case 0: {
				r.mouseMove(mouseX, mouseY);
				break;
			}
			
			case 1: {
				if(press)
					r.mousePress(mouseCode);
				else
					r.mouseRelease(mouseCode);
				break;
			}
			
			case 2: {
				r.mousePress(mouseCode);
				r.mouseRelease(mouseCode);
				break;
			}
			
			case 3: {
				
				if(press)
					r.keyPress(keyCode);
				else
					r.keyRelease(keyCode);
				
				break;
			}
			
			case 4: {
				r.keyPress(keyCode);
				r.keyRelease(keyCode);
				break;
			}
			
			case 5: {
				r.delay(keyCode);
				break;
			}
			
			case 6: {
				for(int i = keyCode; i > 0; i--) {
					for(int j = 0; j < mouseCode; j++) {
						ae.execute(ar.executionCodes[j][0], ar.executionCodes[j][1], ar.executionCodes[j][2], ar.executionCodes[j][3],
								ar.executionCodes[j][4], ar.executionCodes[j][5]);
					}
				}
				
			}
			
		}
		
	}
}
