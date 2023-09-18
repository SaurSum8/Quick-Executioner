package quickExecutionAppPackage;

import java.awt.Font;

public class TextManager extends Main {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 0: x denominator
	 * 1: y denominator
	 * 2: R
	 * 3: G
	 * 4: B
	 * 5: A
	 * 6: Font Size denominator
	 */
	
	String[] text = new String[buttons.buttonVisible.length];
	
	boolean[] visibleText = new boolean[buttons.buttonVisible.length];
	float[][] textProperties = new float[buttons.buttonVisible.length][7];
	Font[] font = new Font[buttons.buttonVisible.length];
	
	public TextManager() {
		
		text[0] = "Quick Executioner";
		textProperties[0][0] = 2;
		textProperties[0][1] = 9;
		textProperties[0][2] = 255;
		textProperties[0][3] = 255;
		textProperties[0][4] = 255;
		textProperties[0][5] = 255;
		textProperties[0][6] = 12;
		
		font[0] = new Font("TimesRoman", Font.BOLD, (int) textProperties[0][6]);
		
		text[1] = "Create A Set";
	//	textProperties[1][1] = buttons.buttonProperties[0][2] / 1.04f;
		textProperties[1][2] = 255;
		textProperties[1][3] = 255;
		textProperties[1][4] = 255;
		textProperties[1][5] = 255;
		textProperties[1][6] = 17;
		
		font[1] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[1][6]);
		
		text[2] = "Exit";
	//	textProperties[2][1] = buttons.buttonProperties[1][2] / 1.03f;
		textProperties[2][2] = 255;
		textProperties[2][3] = 255;
		textProperties[2][4] = 255;
		textProperties[2][5] = 255;
		textProperties[2][6] = 17;
		
		font[2] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[2][6]);
		
		text[3] = "Add Step";
	//	textProperties[3][1] = buttons.buttonProperties[2][2] / 0.825f;
		textProperties[3][2] = 255;
		textProperties[3][3] = 255;
		textProperties[3][4] = 255;
		textProperties[3][5] = 0;
		textProperties[3][6] = 22;
		
		font[3] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[3][6]);
		
		text[4] = "Add Delay";
	//	textProperties[4][1] = buttons.buttonProperties[3][2] / 0.87f;
		textProperties[4][2] = 255;
		textProperties[4][3] = 255;
		textProperties[4][4] = 255;
		textProperties[4][5] = 0;
		textProperties[4][6] = 22;
		
		font[4] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[4][6]);
		
		text[5] = "Hold-Release Toggle";
	//	textProperties[5][1] = buttons.buttonProperties[4][2] / 0.895f;
		textProperties[5][2] = 255;
		textProperties[5][3] = 255;
		textProperties[5][4] = 255;
		textProperties[5][5] = 0;
		textProperties[5][6] = 33;
		
		font[5] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[5][6]);
		
		text[6] = "Add Repeater";
	//	textProperties[6][1] = buttons.buttonProperties[5][2] / 0.916f;
		textProperties[6][2] = 255;
		textProperties[6][3] = 255;
		textProperties[6][4] = 255;
		textProperties[6][5] = 0;
		textProperties[6][6] = 24;
		
		font[6] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[6][6]);
		
		text[7] = "Non-Stop Mode";
		textProperties[7][2] = 255;
		textProperties[7][3] = 255;
		textProperties[7][4] = 255;
		textProperties[7][5] = 0;
		textProperties[7][6] = 30;
			
		font[7] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[7][6]);
		
		text[8] = "Execute";
		textProperties[8][2] = 255;
		textProperties[8][3] = 255;
		textProperties[8][4] = 255;
		textProperties[8][5] = 0;
		textProperties[8][6] = 18;
				
		font[8] = new Font("TimesRoman", Font.BOLD, (int) textProperties[8][6]);
		
		text[9] = "Simultaneous Mode";
		textProperties[9][2] = 255;
		textProperties[9][3] = 255;
		textProperties[9][4] = 255;
		textProperties[9][5] = 0;
		textProperties[9][6] = 31;
				
		font[9] = new Font("TimesRoman", Font.PLAIN, (int) textProperties[9][6]);
		
		for(int i = 0; i < buttons.buttonVisible.length; i++) {//TODO
			
			if(buttons.buttonProperties[i][12] != 0) {
				textProperties[(int) buttons.buttonProperties[i][12]][0] =
					(2 * buttons.buttonProperties[i][0] * buttons.buttonProperties[i][1] * buttons.buttonProperties[i][4]) /
					((2 * buttons.buttonProperties[i][1] * buttons.buttonProperties[i][4])
					- (2 * buttons.buttonProperties[i][0] * buttons.buttonProperties[i][4])
					+ (buttons.buttonProperties[i][0] * buttons.buttonProperties[i][1]));
			
				textProperties[(int) buttons.buttonProperties[i][12]][1] =
						(2 * buttons.buttonProperties[i][2] * buttons.buttonProperties[i][3] * buttons.buttonProperties[i][5]) /
						((2 * buttons.buttonProperties[i][3] * buttons.buttonProperties[i][5])
						- (2 * buttons.buttonProperties[i][2] * buttons.buttonProperties[i][5])
						+ (buttons.buttonProperties[i][2] * buttons.buttonProperties[i][3]));
				
			}
			
		}
	}
}