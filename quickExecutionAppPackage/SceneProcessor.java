package quickExecutionAppPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SceneProcessor extends Main implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Timer t = new Timer(4, this);
	
	boolean inAnimation = false;
	int animationID = 0;
	
	public SceneProcessor() {
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(inAnimation) {
			
			frame.setResizable(false);
			
			switch(animationID) {
				
				case 0: { //NEW SET
					
					if((int) ((frame.getHeight() / buttons.buttonProperties[0][2]) - (frame.getHeight() / buttons.buttonProperties[0][3]))
							+ (int) (frame.getHeight() / buttons.buttonProperties[0][5] / 1.75f) > (int) (frame.getHeight() / tm.textProperties[0][1])) {
						
						buttons.buttonProperties[0][2] += 0.15;
						tm.textProperties[(int) buttons.buttonProperties[0][12]][1] += 0.15;
						
						tm.visibleText[3] = true;
						tm.visibleText[4] = true;
						tm.visibleText[5] = true;
						tm.visibleText[6] = true;
						tm.visibleText[7] = true;
						tm.visibleText[8] = true;
						tm.visibleText[9] = true;
						
						buttons.buttonVisible[2] = true;
						buttons.buttonVisible[3] = true;
						buttons.buttonVisible[4] = true;
						buttons.buttonVisible[5] = true;
						buttons.buttonVisible[6] = true;
						buttons.buttonVisible[7] = true;
						buttons.buttonVisible[8] = true;
						
						if(tm.textProperties[3][5] + 15 <= 255) {
							tm.textProperties[3][5] += 15;
							tm.textProperties[4][5] += 15;
							tm.textProperties[5][5] += 15;
							tm.textProperties[6][5] += 15;
							tm.textProperties[7][5] += 15;
							tm.textProperties[8][5] += 15;
							tm.textProperties[9][5] += 15;
							
							displayerAlpha += 15;
							
							buttons.buttonProperties[2][10] += 15;
							buttons.buttonProperties[3][10] += 15;
							buttons.buttonProperties[4][10] += 15;
							buttons.buttonProperties[5][10] += 15;
							buttons.buttonProperties[6][10] += 15;
							buttons.buttonProperties[7][10] += 15;
							buttons.buttonProperties[8][10] += 15;
							
						} else {
							tm.textProperties[3][5] = 255;
							tm.textProperties[4][5] = 255;
							tm.textProperties[5][5] = 255;
							tm.textProperties[6][5] = 255;
							tm.textProperties[7][5] = 255;
							tm.textProperties[8][5] = 255;
							tm.textProperties[9][5] = 255;
							
							displayerAlpha = 255;
							
							buttons.buttonProperties[2][10] = 255;
							buttons.buttonProperties[3][10] = 255;
							buttons.buttonProperties[4][10] = 255;
							buttons.buttonProperties[5][10] = 255;
							buttons.buttonProperties[6][10] = 255;
							buttons.buttonProperties[7][10] = 255;
							buttons.buttonProperties[8][10] = 255;
						}
					
					} else {
						frame.setResizable(true);
						inAnimation = false;
					}
					
					if(tm.textProperties[0][5] > 0 || tm.textProperties[2][5] > 0
							|| buttons.buttonProperties[0][10] > 0 || buttons.buttonProperties[1][10] > 0) {
						
						if(tm.textProperties[0][5] - 15 >= 0) {
							tm.textProperties[0][5] -= 15;
							tm.textProperties[2][5] -= 15;
						
							buttons.buttonProperties[0][10] -= 15;
							buttons.buttonProperties[1][10] -= 15;
						
						
						} else {
							buttons.buttonProperties[1][10] = 0;
							buttons.buttonProperties[0][10] = 0;
							
							tm.textProperties[2][5] = 0;
							tm.textProperties[0][5] = 0;
						}
					
					} else {
						tm.visibleText[0] = false;
						tm.visibleText[2] = false;
						
						buttons.buttonVisible[0] = false;
						buttons.buttonVisible[1] = false;
						
					}
					
					break;
					
				}
				
				case 1: { //Exit
					System.exit(0);
					break;
				}
				
				case 2: { //ADD STEP
					ar.record = true;
					recordFrame.setVisible(true);
					frame.setResizable(true);
					inAnimation = false;
					break;
				}
				
				case 3: { //ADD DELAY
					ar.record(5, 100, 0, 0, 0, 0);
					frame.setResizable(true);
					inAnimation = false;
					break;
				}
				
				case 4: { //HOLD-RELEASE
					ar.hold = !ar.hold;
					
					if(ar.hold) {
						buttons.buttonProperties[4][7] = 80;
						buttons.buttonProperties[4][8] = 80;
						buttons.buttonProperties[4][9] = 80;
					
						tm.textProperties[5][2] = 80;
						tm.textProperties[5][3] = 80;
						tm.textProperties[5][4] = 80;
					
					} else {
						buttons.buttonProperties[4][7] = 100;
						buttons.buttonProperties[4][8] = 100;
						buttons.buttonProperties[4][9] = 100;
					
						tm.textProperties[5][2] = 100;
						tm.textProperties[5][3] = 100;
						tm.textProperties[5][4] = 100;
					}
						
					inAnimation = false;
					frame.setResizable(true);
					break;
				}
				
				case 5: { //ADD REPEATER
					ar.record(6, 1, 0, 0, 0, 0);
					inAnimation = false;
					frame.setResizable(true);
					break;
				}
				
				case 6: { //Non-Stop Mode
					ar.record = true;
					ar.follow = true;
					recordFrame.setVisible(true);
					frame.setResizable(true);
					inAnimation = false;
					break;
				}
				
				case 7: { //Simultaneous Mode
					ar.record = true;
					ar.follow = true;
					ar.simul = true;
					recordFrame.setVisible(true);
					frame.setResizable(true);
					inAnimation = false;
					break;
				}
				
				case 8: { //Execute
					
					for(int j = 0; j < ar.recordedTill && approveExe; j++) {
						ae.execute(ar.executionCodes[j][0], ar.executionCodes[j][1], ar.executionCodes[j][2], ar.executionCodes[j][3], 
								ar.executionCodes[j][4], ar.executionCodes[j][5]);
					}
					
					if(approveExe) {
						inAnimation = false;
						approveExe = false;
					}
					
					break;
				}
			
			}
			
		}
		
	}
	
}