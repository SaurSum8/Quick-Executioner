package quickExecutionAppPackage;

public class Buttons extends Main {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 2nd Array (0 to 6 are denominators):
	 * 0: x axis
	 * 1: x axis subtract
	 * 2: y axis
	 * 3: y axis subtract
	 * 4: button width
	 * 5: button height
	 * 6: button arc width + height
	 * 7, 8, 9, 10 (RGBA): R, G, B, A 
	 * 11: if 0- fill, if 1- draw
	 * 12: Associated Text's Array No.
	 */
	
	boolean[] buttonVisible = new boolean[256];
	float[][] buttonProperties = new float[buttonVisible.length][13];
	
	public Buttons() {
		
		//New Set
		
		buttonProperties[0][0] = 2;
	//	buttonProperties[0][1] = 3.3f;
		buttonProperties[0][2] = 2.66f;
	//	buttonProperties[0][3] = 8;
		buttonProperties[0][4] = 1.65f;
		buttonProperties[0][5] = 4;
		buttonProperties[0][6] = 20;
		buttonProperties[0][7] = 255;
		buttonProperties[0][8] = 255;
		buttonProperties[0][9] = 255;
		buttonProperties[0][10] = 255;
		buttonProperties[0][11] = 1;
		buttonProperties[0][12] = 1;
		
		//Load Set
		
		buttonProperties[1][0] = 2;
	//	buttonProperties[1][1] = 3.3f;
		buttonProperties[1][2] = 1.425f;
	//	buttonProperties[1][3] = 8;
		buttonProperties[1][4] = 1.65f;
		buttonProperties[1][5] = 4;
		buttonProperties[1][6] = 20;
		buttonProperties[1][7] = 255;
		buttonProperties[1][8] = 255;
		buttonProperties[1][9] = 255;
		buttonProperties[1][10] = 255;
		buttonProperties[1][11] = 1;
		buttonProperties[1][12] = 2;
		
		//Add Step
		
		buttonProperties[2][0] = 1.2f;
	//	buttonProperties[2][1] = 3.8f;
		buttonProperties[2][2] = 3.75f;
	//	buttonProperties[2][3] = 8;
		buttonProperties[2][4] = 5;
		buttonProperties[2][5] = 10;
		buttonProperties[2][6] = 20;
		buttonProperties[2][7] = 255;
		buttonProperties[2][8] = 255;
		buttonProperties[2][9] = 255;
		buttonProperties[2][10] = 0;
		buttonProperties[2][11] = 1;
		buttonProperties[2][12] = 3;
		
		//Add Delay
		
		buttonProperties[3][0] = 1.2f;
	//	buttonProperties[3][1] = 3.8f;
		buttonProperties[3][2] = 2.56f;
	//	buttonProperties[3][3] = 8;
		buttonProperties[3][4] = 5;
		buttonProperties[3][5] = 10;
		buttonProperties[3][6] = 20;
		buttonProperties[3][7] = 255;
		buttonProperties[3][8] = 255;
		buttonProperties[3][9] = 255;
		buttonProperties[3][10] = 0;
		buttonProperties[3][11] = 1;
		buttonProperties[3][12] = 4;
		
		//Hold-Release
		
		buttonProperties[4][0] = 1.2f;
	//	buttonProperties[4][1] = 3.8f;
		buttonProperties[4][2] = 1.58f;
	//	buttonProperties[4][3] = 8;
		buttonProperties[4][4] = 5;
		buttonProperties[4][5] = 10;
		buttonProperties[4][6] = 20;
		buttonProperties[4][7] = 255;
		buttonProperties[4][8] = 255;
		buttonProperties[4][9] = 255;
		buttonProperties[4][10] = 0;
		buttonProperties[4][11] = 1;
		buttonProperties[4][12] = 5;
		
		//Add Repeater
		
		buttonProperties[5][0] = 1.2f;
	//	buttonProperties[5][1] = 3.8f;
		buttonProperties[5][2] = 1.96f;
	//	buttonProperties[5][3] = 8;
		buttonProperties[5][4] = 5;
		buttonProperties[5][5] = 10;
		buttonProperties[5][6] = 20;
		buttonProperties[5][7] = 255;
		buttonProperties[5][8] = 255;
		buttonProperties[5][9] = 255;
		buttonProperties[5][10] = 0;
		buttonProperties[5][11] = 1;
		buttonProperties[5][12] = 6;
		
		//Non-Stop Mode
		
		buttonProperties[6][0] = 1.2f;
	//	buttonProperties[6][1] = 3.8f;
		buttonProperties[6][2] = 1.33f;
	//	buttonProperties[6][3] = 8;
		buttonProperties[6][4] = 5;
		buttonProperties[6][5] = 10;
		buttonProperties[6][6] = 20;
		buttonProperties[6][7] = 255;
		buttonProperties[6][8] = 255;
		buttonProperties[6][9] = 255;
		buttonProperties[6][10] = 0;
		buttonProperties[6][11] = 1;
		buttonProperties[6][12] = 7;
		
		//Simultaneous Mode
		
		buttonProperties[7][0] = 1.2f;
		buttonProperties[7][2] = 1.145f;
		buttonProperties[7][4] = 5;
		buttonProperties[7][5] = 10;
		buttonProperties[7][6] = 20;
		buttonProperties[7][7] = 255;
		buttonProperties[7][8] = 255;
		buttonProperties[7][9] = 255;
		buttonProperties[7][10] = 0;
		buttonProperties[7][11] = 1;
		buttonProperties[7][12] = 9;
		
		//Execute
		
		buttonProperties[8][0] = 1.2f;
		buttonProperties[8][2] = 7.2f;
		buttonProperties[8][4] = 5;
		buttonProperties[8][5] = 10;
		buttonProperties[8][6] = 20;
		buttonProperties[8][7] = 255;
		buttonProperties[8][8] = 255;
		buttonProperties[8][9] = 255;
		buttonProperties[8][10] = 0;
		buttonProperties[8][11] = 1;
		buttonProperties[8][12] = 8;
		
		for(int i = 0; i < buttonProperties.length; i++) { //TODO
			
			if(buttonProperties[i][12] != 0) {
				buttonProperties[i][1] = 2 * buttonProperties[i][4];
				buttonProperties[i][3] = 2 * buttonProperties[i][5];
			}
			
		}
	}
	
	public void checkHover(int x, int y) {
		
		for(int i = 0; i < buttonVisible.length; i++) {
			
			if(buttonVisible[i]) {
				
				if(x > ((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1]))
						&& x < (((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1])) + 
						(frame.getWidth() / buttons.buttonProperties[i][4]))
						
						&&
						
						y > ((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])) 
						&& y < (((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])) +
								(frame.getHeight() / buttons.buttonProperties[i][5]))
						
						&& !sp.inAnimation) {
					
					if(i == 4) {
						buttonProperties[4][7] = 50;
						buttonProperties[4][8] = 50;
						buttonProperties[4][9] = 50;
						
						tm.textProperties[5][2] = 50;
						tm.textProperties[5][3] = 50;
						tm.textProperties[5][4] = 50;
					}
					
					buttonProperties[i][7] = 100;
					buttonProperties[i][8] = 100;
					buttonProperties[i][9] = 100;
					
					tm.textProperties[(int) buttonProperties[i][12]][2] = 100;
					tm.textProperties[(int) buttonProperties[i][12]][3] = 100;
					tm.textProperties[(int) buttonProperties[i][12]][4] = 100;
					
				} else {
					buttonProperties[i][7] = 255;
					buttonProperties[i][8] = 255;
					buttonProperties[i][9] = 255;
					
					tm.textProperties[(int) buttonProperties[i][12]][2] = 255;
					tm.textProperties[(int) buttonProperties[i][12]][3] = 255;
					tm.textProperties[(int) buttonProperties[i][12]][4] = 255;
					
					if(i == 4 && ar.hold) {
						buttonProperties[4][7] = 80;
						buttonProperties[4][8] = 80;
						buttonProperties[4][9] = 80;
						
						tm.textProperties[5][2] = 80;
						tm.textProperties[5][3] = 80;
						tm.textProperties[5][4] = 80;
					}
				}
				
			}
			
		}
		
	}
	
	public void clickManager(int x, int y) {
		
		for(int i = 0; i < buttonVisible.length; i++) {
			
			if(buttonVisible[i] && !sp.inAnimation) {
				
				if(x > ((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1]))
						&& x < (((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1])) + 
						(frame.getWidth() / buttons.buttonProperties[i][4]))
						
						&&
						
						y > ((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])) 
						&& y < (((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])) +
								(frame.getHeight() / buttons.buttonProperties[i][5]))) {
					
					sp.animationID = i;
					sp.inAnimation = true;
					 
					if(i == 0 || i == 1 || i == 3 || i == 5)
						checkHover(0, 0);
					
					break;
					
				}
				
			}
			
		}
		
		int[] temp = new int[7];
		
		boolean isInside = false;
		
		for(int i = 0; i < ar.recordedTill; i++) {
			
			if(y >= 0.96 * recordDisMY[i][0] && y <= 1.04 * recordDisMY[i][1]) {
				
				if(x >= recordDisMY[i][3] && x <= recordDisMY[i][2] && i != ar.recordedTill - 1) { //Down
					
					for(int j = 0; j < 7; j++) {
						
						temp[j] = ar.executionCodes[i][j];
						ar.executionCodes[i][j] = ar.executionCodes[i + 1][j];
						ar.executionCodes[i + 1][j] = temp[j];
						
					}
					
				} else if(x >= recordDisMY[i][5] && x <= recordDisMY[i][4] && i != 0) { //Up
					
					for(int j = 0; j < 7; j++) {
						
						temp[j] = ar.executionCodes[i][j];
						ar.executionCodes[i][j] = ar.executionCodes[i - 1][j];
						ar.executionCodes[i - 1][j] = temp[j];
						
					}
					
				} else if(x >= recordDisMY[i][2] && x <= (int) ((frame.getWidth() / displayerX) + (frame.getWidth() / displayerWidth))) {
					
					isInside = true;
					
					showDelOption = true;
					showDelOptionID = i;
					
					if(ar.executionCodes[i][0] == 5 || ar.executionCodes[i][0] == 6) {
						allowCustomInput = true;
						customInputID = i;
					}
				}
				
			} else if(!isInside) {
				allowCustomInput = false;
				showDelOption = false;
			}
			
		}
		
	}

}