package quickExecutionAppPackage;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class ActionRecorder extends Main implements MouseMotionListener, MouseListener, KeyListener {
	
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
	
	int[][] executionCodes = new int[2000000][7];
	
	int recordedTill = 0;
	
	boolean record = false;
	
	boolean follow = false;
	boolean simul = false;
	boolean ignore = false;
	
	boolean hold = false;
		
	Set<Integer> keysInHoldSet = new HashSet<Integer>();
	Set<Integer> mouseInHoldSet = new HashSet<Integer>();
	
	public ActionRecorder() {}
	
	public void record(int code, int keyCode, int mouseCode, int mouseX, int mouseY, int toPress) {
		executionCodes[recordedTill][0] = code;
		executionCodes[recordedTill][1] = keyCode;
		executionCodes[recordedTill][2] = mouseCode;
		executionCodes[recordedTill][3] = mouseX;
		executionCodes[recordedTill][4] = mouseY;
		executionCodes[recordedTill][5] = toPress;
		
		if(code == 6)
			executionCodes[recordedTill][2] = recordedTill;
		
		recordedTill++;
		
		if(recordedTill > 6)
			scrollAdder = scrollAdder + (int) ((((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)) + 
					(frame.getHeight() / displayerHeight)) - ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
							((ar.recordedTill) * frame.getHeight() / displayerHeight / 6) + scrollAdder));
	}
	
	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		boolean alrHeld = false;
		int prevHeldCode = 0;
		
		if(!ignore)
			record(0, 0, 0, e.getXOnScreen(), e.getYOnScreen(), 0);
		
		if(!hold && !ignore) {
			
			if(mouseInHoldSet.contains(e.getModifiersEx())) {
				mouseInHoldSet.remove(e.getModifiersEx());
				
				record(1, 0, e.getModifiersEx(), 0, 0, 1);
			
			} else {
				record(2, 0, e.getModifiersEx(), 0, 0, 0);
			}
		
		} else if(!ignore) {
			
			if(mouseInHoldSet.contains(e.getModifiersEx())) {
				mouseInHoldSet.remove(e.getModifiersEx());
				alrHeld = true;
			
			} else {
				mouseInHoldSet.add(e.getModifiersEx());
				alrHeld = false;
			}
			
			if(alrHeld) {
				record(1, 0, e.getModifiersEx(), 0, 0, 1);
			
				for(int i = recordedTill - 2; i >= 0; i--) {
					if(executionCodes[i][2] == e.getModifiersEx() && executionCodes[i][0] == 1) {
						prevHeldCode = i;
						break;
					}
				}
				
			} else {
				record(1, 0, e.getModifiersEx(), 0, 0, 2);
			}
			
		}
		
		Integer[] extraExec = new Integer[mouseInHoldSet.size()];
		mouseInHoldSet.toArray(extraExec);
		
		Integer[] extraExecKeys = new Integer[keysInHoldSet.size()];
		keysInHoldSet.toArray(extraExecKeys);
		
		if(simul && !ignore && (!hold || alrHeld)) {
			
			recordFrame.setVisible(false);
			
			ignore = true;
			
			ae.execute(5, 200, 0, 0, 0, 0);
			
			for(int i = 0; i < extraExec.length; i++) {
				ae.execute(1, extraExec[i], 0, 0, 0, 2);
			}
			
			for(int i = 0; i < extraExecKeys.length; i++) {
				ae.execute(3, extraExecKeys[i], 0, 0, 0, 2);
			}
			
			ae.execute(5, 100, 0, 0, 0, 0);
			
			if(alrHeld) {
				ae.execute(executionCodes[prevHeldCode - 1][0], executionCodes[prevHeldCode - 1][1], executionCodes[prevHeldCode - 1][2], 
						executionCodes[prevHeldCode - 1][3], executionCodes[prevHeldCode - 1][4], executionCodes[prevHeldCode - 1][5]);
				
				ae.execute(executionCodes[prevHeldCode][0], executionCodes[prevHeldCode][1], executionCodes[prevHeldCode][2], 
						executionCodes[prevHeldCode][3], executionCodes[prevHeldCode][4], executionCodes[prevHeldCode][5]);
				
				ae.execute(executionCodes[recordedTill - 2][0], executionCodes[recordedTill - 2][1], executionCodes[recordedTill - 2][2], 
						executionCodes[recordedTill - 2][3], executionCodes[recordedTill - 2][4], executionCodes[recordedTill - 2][5]);
			}
			
			ae.execute(executionCodes[recordedTill - 1][0], executionCodes[recordedTill - 1][1], executionCodes[recordedTill - 1][2], 
				executionCodes[recordedTill - 1][3], executionCodes[recordedTill - 1][4], executionCodes[recordedTill - 1][5]);
			
			for(int i = 0; i < extraExec.length; i++) {
				ae.execute(1, extraExec[i], 0, 0, 0, 1);
			}
			
			for(int i = 0; i < extraExecKeys.length; i++) {
				ae.execute(3, extraExecKeys[i], 0, 0, 0, 1);
			}
			
			recordFrame.setVisible(true);
			
			ae.execute(2, 0, InputEvent.BUTTON1_DOWN_MASK, 0, 0, 0);
			
		} else if(ignore) {
			ignore = false;
		}
		
		if(!follow) {
			recordFrame.setVisible(false);
			record = false;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}

	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 192 && follow) {
			follow = false;
			simul = false;
			recordFrame.setVisible(false);
			record = false;
			
		} else if(e.getKeyCode() == 92) {
			
			ar.hold = !ar.hold;
			
			if(ar.hold) {
				buttons.buttonProperties[4][7] = 80;
				buttons.buttonProperties[4][8] = 80;
				buttons.buttonProperties[4][9] = 80;
			
				tm.textProperties[5][2] = 80;
				tm.textProperties[5][3] = 80;
				tm.textProperties[5][4] = 80;
			
			} else {
				buttons.buttonProperties[4][7] = 255;
				buttons.buttonProperties[4][8] = 255;
				buttons.buttonProperties[4][9] = 255;
			
				tm.textProperties[5][2] = 255;
				tm.textProperties[5][3] = 255;
				tm.textProperties[5][4] = 255;
			}
			
		} else if(e.getKeyCode() == KeyEvent.VK_INSERT) {
			ar.record(5, 100, 0, 0, 0, 0);
			
		} else {
		
			boolean alrHeld = false;
			int prevHeldCode = 0;
			
			if(!hold) {
				if(keysInHoldSet.contains(e.getKeyCode())) {
					keysInHoldSet.remove(e.getKeyCode());
					
					record(3, e.getKeyCode(), 0, 0, 0, 1);
				
				} else {
					record(4, e.getKeyCode(), 0, 0, 0, 0);
				}
		
			} else {
			
				if(keysInHoldSet.contains(e.getKeyCode())) {
					keysInHoldSet.remove(e.getKeyCode());
					alrHeld = true;
				
				} else {
					keysInHoldSet.add(e.getKeyCode());
					alrHeld = false;
				}
				
				if(alrHeld) {
					record(3, e.getKeyCode(), 0, 0, 0, 1);
					
					for(int i = recordedTill - 2; i >= 0; i--) {
						if(executionCodes[i][1] == e.getKeyCode() && executionCodes[i][0] == 3) {
							prevHeldCode = i;
							break;
						}
					}
					
				} else {
					record(3, e.getKeyCode(), 0, 0, 0, 2);
				}
					
			}
		
			if(simul && (!hold || alrHeld)) {
				
				recordFrame.setVisible(false);
				
				ae.execute(5, 200, 0, 0, 0, 0);
				
				Integer[] extraExec = new Integer[keysInHoldSet.size()];
				keysInHoldSet.toArray(extraExec);
				
				Integer[] extraExecMouse = new Integer[mouseInHoldSet.size()];
				mouseInHoldSet.toArray(extraExecMouse);
				
				for(int i = 0; i < extraExec.length; i++) {
					ae.execute(3, extraExec[i], 0, 0, 0, 2);
				}
				
				for(int i = 0; i < extraExecMouse.length; i++) {
					ae.execute(1, extraExecMouse[i], 0, 0, 0, 2);
				}
				
				ae.execute(5, 100, 0, 0, 0, 0);
				
				if(alrHeld) {
					ae.execute(executionCodes[prevHeldCode][0], executionCodes[prevHeldCode][1], executionCodes[prevHeldCode][2], 
							executionCodes[prevHeldCode][3], executionCodes[prevHeldCode][4], executionCodes[prevHeldCode][5]);	
				}
				
				ae.execute(executionCodes[recordedTill - 1][0], executionCodes[recordedTill - 1][1], executionCodes[recordedTill - 1][2], 
						executionCodes[recordedTill - 1][3], executionCodes[recordedTill - 1][4], executionCodes[recordedTill - 1][5]);
				
				for(int i = 0; i < extraExec.length; i++) {
					ae.execute(3, extraExec[i], 0, 0, 0, 1);
				}
				
				for(int i = 0; i < extraExecMouse.length; i++) {
					ae.execute(1, extraExecMouse[i], 0, 0, 0, 1);
				}
				
				recordFrame.setVisible(true);
			}
			
			if(!follow) {
				recordFrame.setVisible(false);
				record = false;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
}
