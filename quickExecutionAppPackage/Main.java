package quickExecutionAppPackage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements MouseMotionListener, MouseListener, KeyListener, MouseWheelListener, ActionListener {
	
	private static final long serialVersionUID = 1L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	final int width = (int) screenSize.getWidth();
	final int height = (int) screenSize.getHeight();
	
	static JFrame frame = new JFrame("Quick Executioner");
	static JFrame recordFrame = new JFrame("Quick Executioner Action Recorder");
	
	static Buttons buttons;
	static Main m;
	static TextManager tm;
	static SceneProcessor sp;
	static ActionExecutor ae;
	static ActionRecorder ar;
	
	final float displayerX = 16f, displayerY = 3, displayerWidth = 1.7f, displayerHeight = 1.4f, displayerYSUB = 6.5f;
	static int displayerAlpha = -90;
	
	static int scrollAdder;
	static boolean allowScrollDown;
	
	static int[][] recordDisMY;
	
	static boolean allowCustomInput = false;
	static int customInputID = 0;
	
	static boolean showDelOption = false;
	static int showDelOptionID = 0;
	
	Timer moveWindow = new Timer(0, this);
	int toTranslateX, toTranslateY;
	int ogFrameWidth, ogFrameHeight;
	
	int intiX, intiY;
	static boolean changingWindow = false;
	boolean moveFrame = false;
	
	static boolean approveExe = false;
		
	public static void main(String[] args) {
		
		m = new Main();
		buttons = new Buttons();
		tm = new TextManager();
		sp = new SceneProcessor();
		ae = new ActionExecutor();
		ar = new ActionRecorder();
		
		frame.add(m);
		frame.add(buttons);
		frame.add(sp);
		frame.add(ae);
		frame.add(ar);
		frame.addMouseMotionListener(m);
		frame.addMouseListener(m);
		frame.addKeyListener(m);
		frame.addMouseWheelListener(m);
		
		recordDisMY = new int[ar.executionCodes.length][6];
		
		recordFrame.addMouseMotionListener(ar);
		recordFrame.addMouseListener(ar);
		recordFrame.addKeyListener(ar);
		
		buttons.buttonVisible[0] = true;
		buttons.buttonVisible[1] = true;
		
		tm.visibleText[0] = true;
		tm.visibleText[1] = true;
		tm.visibleText[2] = true;
		
		frame.setVisible(true);
		
	}
	
	public Main() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setSize(frame.getMinimumSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setUndecorated(true);
		frame.setOpacity(0.9f);
		frame.setAlwaysOnTop(true);
		
		recordFrame.setSize(width, height);
		recordFrame.setLocationRelativeTo(null);
		recordFrame.setUndecorated(true);
		recordFrame.setOpacity(0.01f);
		recordFrame.setAlwaysOnTop(true);
		recordFrame.setResizable(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(25,29,31,255));
		
	//	g.translate(-6, -30);
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		g.drawString("X", frame.getWidth() - 20, 20);
		
		for(int i = 0; i < buttons.buttonVisible.length; i++) {
			
			if(buttons.buttonVisible[i]) {
				
				g.setColor(new Color((int) buttons.buttonProperties[i][7], (int) buttons.buttonProperties[i][8], 
						(int) buttons.buttonProperties[i][9], (int) buttons.buttonProperties[i][10]));
				
				if(buttons.buttonProperties[i][11] == 0) {
					
					g.fillRoundRect( (int) ((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1])),
							(int) ((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])),
							(int) (frame.getWidth() / buttons.buttonProperties[i][4]),
							(int) (frame.getHeight() / buttons.buttonProperties[i][5]),
							(int) (frame.getWidth() / buttons.buttonProperties[i][6]),
							(int) (frame.getWidth() / buttons.buttonProperties[i][6]));
				
				} else if(buttons.buttonProperties[i][11] == 1) {
					
					g.drawRoundRect( (int) ((frame.getWidth() / buttons.buttonProperties[i][0]) - (frame.getWidth() / buttons.buttonProperties[i][1])),
							(int) ((frame.getHeight() / buttons.buttonProperties[i][2]) - (frame.getHeight() / buttons.buttonProperties[i][3])),
							(int) (frame.getWidth() / buttons.buttonProperties[i][4]),
							(int) (frame.getHeight() / buttons.buttonProperties[i][5]),
							(int) (frame.getWidth() / buttons.buttonProperties[i][6]),
							(int) (frame.getWidth() / buttons.buttonProperties[i][6]));
					
				}
			}
		
			if(tm.visibleText[i]) {
				
				g.setColor(new Color((int) tm.textProperties[i][2], (int) tm.textProperties[i][3], 
						(int) tm.textProperties[i][4], (int) tm.textProperties[i][5]));
				
				tm.font[i] = new Font(tm.font[i].getFontName(), Font.BOLD, (int) ( (frame.getHeight() / 2 + frame.getWidth()) 
						/ (2 * tm.textProperties[i][6])));
				
				g.setFont(tm.font[i]);
				
				final Rectangle2D r = g.getFontMetrics().getStringBounds(tm.text[i], g);
				
				g.drawString(tm.text[i], (int) ((frame.getWidth() / tm.textProperties[i][0]) - (r.getWidth() / 2)),
								(int) ((frame.getHeight() / tm.textProperties[i][1]) + (r.getHeight() / 4)));
				
			}
			
		}
		
		if(buttons.buttonVisible[2] && displayerAlpha > 0) {
			
			g.setColor(new Color(255, 255, 255, displayerAlpha));
				
			g.drawRect((int) (frame.getWidth() / displayerX), 
					(int) ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)), 
					(int) (frame.getWidth() / displayerWidth), (int) (frame.getHeight() / displayerHeight));
			
			if(ar.record) {
				g.drawString("Attention: Recording!", (int) (frame.getWidth() / displayerX), 
						(int) (( 0.92 * frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)));
			
				g.drawRect(1, 1, frame.getWidth() - 2, frame.getHeight() - 2);
				
			} else if(sp.inAnimation && sp.animationID == 8) {
				
				g.drawString("Attention: Executing!", (int) (frame.getWidth() / displayerX), 
						(int) (( 0.92 * frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)));
				
				g.drawRect(1, 1, frame.getWidth() - 2, frame.getHeight() - 2);
				
				approveExe = true;
			}
			
			for(int i = 0; i < ar.recordedTill; i++) {
				
				if((((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
						((i + 1) * frame.getHeight() / displayerHeight / 6) + scrollAdder) < ((frame.getHeight() / displayerY) 
								- (frame.getHeight() / displayerYSUB)) + (frame.getHeight() / displayerHeight))
						
						&& ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
								((i + 1) * frame.getHeight() / displayerHeight / 6) + scrollAdder) > ((frame.getHeight() / displayerY) 
								- (frame.getHeight() / displayerYSUB))) {
				
					g.drawLine((int) (frame.getWidth() / displayerX),
						
							(int) ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
									((i + 1) * frame.getHeight() / displayerHeight / 6) + scrollAdder), 
						
							(int) ((frame.getWidth() / displayerWidth) + (frame.getWidth() / displayerX)), 
						
							(int) ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
									((i + 1) * frame.getHeight() / displayerHeight / 6) + scrollAdder));
					
					if(i == ar.recordedTill - 1) {
						allowScrollDown = false;
						
					if(i > 5)
						scrollAdder = scrollAdder + (int) ((((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)) + 
								(frame.getHeight() / displayerHeight)) - ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
								((i + 1) * frame.getHeight() / displayerHeight / 6) + scrollAdder));
					}
					
				} else {
					
					if(i == ar.recordedTill - 1 && i > 5)
						allowScrollDown = true;
					
				}
				
				String toDisplay = "";
				
				String textInputLine = "";
				
				if(allowCustomInput && i == customInputID)
					textInputLine = "|";
				else
					textInputLine = "";
					
				switch(ar.executionCodes[i][0]) {
				
					case 0: {
						toDisplay = "Move Mouse To:   X : " + ar.executionCodes[i][3] + "   Y : " + ar.executionCodes[i][4];
						break;
					}
					
					case 1: {
						if(ar.executionCodes[i][5] == 2)
							toDisplay = "Hold Mouse - " + MouseEvent.getModifiersExText(ar.executionCodes[i][2]) + ", Code : " + ar.executionCodes[i][2];
						
						else
							toDisplay = "Release Mouse - " + MouseEvent.getModifiersExText(ar.executionCodes[i][2]) + ", Code : " + ar.executionCodes[i][2];
							
						break;
					}
					
					case 2: {
						toDisplay = "Click Mouse - " + MouseEvent.getModifiersExText(ar.executionCodes[i][2]) + ", Code : " + ar.executionCodes[i][2];
						break;
					}
					
					case 3: {
						if(ar.executionCodes[i][5] == 2)
							toDisplay = "Hold Key - " + KeyEvent.getKeyText(ar.executionCodes[i][1]) + " ; Code : " + ar.executionCodes[i][1];
						
						else
							toDisplay = "Release Key - " + KeyEvent.getKeyText(ar.executionCodes[i][1]) + " ; Code : " + ar.executionCodes[i][1];
							
						break;
					}
					
					case 4: {
						toDisplay = "Click Key - " + KeyEvent.getKeyText(ar.executionCodes[i][1]) + " ; Code : " + ar.executionCodes[i][1];
						break;
					}
					
					case 5: {
						toDisplay = "Delay : Sleep For " + ar.executionCodes[i][1] + textInputLine + " ms";
						break;
					}
				
					case 6: {
						toDisplay = "Repeater : Repeat All Above Commands " + ar.executionCodes[i][1] + textInputLine + " Time(s)";
						break;
					}
				}
				
				final Rectangle2D r = g.getFontMetrics().getStringBounds(toDisplay, g);
				
				int yMiddle = 0;
				
				if(i == 0) {
					yMiddle = (int) ((((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)) 
							+ ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
									(frame.getHeight() / displayerHeight / 6))) / 2);
					
				} else {
					yMiddle = (int) (((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
							((i + 1) * frame.getHeight() / displayerHeight / 6) + (frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB) + 
							(i * frame.getHeight() / displayerHeight / 6)) / 2);
				}
				
				if((yMiddle + (r.getHeight() / 4) + scrollAdder < ((frame.getHeight() / displayerY) - (frame.getHeight() / displayerYSUB)) + 
						(frame.getHeight() / displayerHeight)) && (yMiddle - (r.getHeight() / 4) + scrollAdder > ((frame.getHeight() / displayerY) 
								- (frame.getHeight() / displayerYSUB)))) {
						
					g.drawString(toDisplay, (int) ((((frame.getWidth() / displayerX) + ((frame.getWidth() / displayerWidth) + 
								(frame.getWidth() / displayerX))) / 2) - (r.getWidth() / 2)), (int) (yMiddle + (r.getHeight() / 4) + scrollAdder));
					
					final Rectangle2D s = g.getFontMetrics().getStringBounds(Integer.toString(i + 1) + ".", g);
					
					g.drawString(Integer.toString(i + 1) + ".", (int) ((frame.getWidth() / (2 * displayerX)) - (s.getWidth() / 2)), 
							(int) (yMiddle + (r.getHeight() / 4) + scrollAdder));
					
					final Rectangle2D t = g.getFontMetrics().getStringBounds("/\\", g);
					
					g.drawString("\\/", (int) ((frame.getWidth() / (0.65 * displayerX)) - (t.getWidth() / 2)), 
							(int) (yMiddle + (r.getHeight() / 4) + scrollAdder));
					
					g.drawString("/\\", (int) ((frame.getWidth() / (0.82 * displayerX)) - (t.getWidth() / 2)), 
							(int) (yMiddle + (r.getHeight() / 4) + scrollAdder));
					
					recordDisMY[i][0] = (int) (yMiddle + scrollAdder - (r.getHeight() / 4));
					recordDisMY[i][1] = (int) (yMiddle + scrollAdder + (r.getHeight() / 4));
					
					recordDisMY[i][2] = (int) (frame.getWidth() / (0.65 * displayerX) + (t.getWidth() / 2));
					recordDisMY[i][3] = (int) ((frame.getWidth() / (0.65 * displayerX)) - (t.getWidth() / 2));
					
					recordDisMY[i][4] = (int) (frame.getWidth() / (0.82 * displayerX) + (t.getWidth() / 2));
					recordDisMY[i][5] = (int) ((frame.getWidth() / (0.82 * displayerX)) - (t.getWidth() / 2));
					
				}
				
			}
			
		}
		 
		if(!sp.inAnimation && !changingWindow)
			ae.r.delay(25);
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX(), y = e.getY();
		
		if(x >= (frame.getWidth() / 1.05) && x <= frame.getWidth() && y <= (frame.getHeight() / 22)) {
			System.exit(0);
		}
		
		ogFrameWidth = frame.getWidth();
		ogFrameHeight = frame.getHeight();
		
		moveFrame = true;
		
		buttons.checkHover(x, y);
		
		intiX = x;
		intiY = y;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(!ar.record)
			buttons.clickManager(e.getX(), e.getY());
		
		moveFrame = false;
		
		changingWindow = false;
		moveWindow.stop();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int x = e.getX(), y = e.getY();
		
		buttons.checkHover(x, y);
		
		toTranslateX = x - intiX;
		toTranslateY = y - intiY;
		
		if(!moveWindow.isRunning()) {
			moveWindow.start();
			changingWindow = true;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX(), y = e.getY();
		
		buttons.checkHover(x, y);
		
		if(x >= (frame.getWidth() / 1.05) && x <= frame.getWidth() && y > (frame.getHeight() / 21) && y < (frame.getHeight() / 1.05)) {
			
			Cursor c = new Cursor(Cursor.E_RESIZE_CURSOR);
			
			frame.setCursor(c);
		
		} else if(y >= (frame.getHeight() / 1.05) && y <= frame.getHeight() && 
				x < (frame.getWidth() / 1.05)) {
			
			Cursor c = new Cursor(Cursor.S_RESIZE_CURSOR);
			
			frame.setCursor(c);
			
		} else if(y >= (frame.getHeight() / 1.05) && y <= frame.getHeight() && 
				x >= (frame.getWidth() / 1.05)) {
			
			Cursor c = new Cursor(Cursor.SE_RESIZE_CURSOR);
			
			frame.setCursor(c);
			
		} else {
			
			Cursor c = new Cursor(Cursor.DEFAULT_CURSOR);
			
			frame.setCursor(c);
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	//	int key = e.getKeyCode();
		char ckey = e.getKeyChar();
		
		// 192 = ` ~
		
	/*	if(key == 27) {
			System.exit(0);
		}*/
		
		if(ckey == 'P' || ckey == 'p') {
			sp.inAnimation = true;
			sp.animationID = 8;
		}
		
		if(allowCustomInput) {
			
			if(e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
				
				ar.executionCodes[customInputID][1] = 10 * ar.executionCodes[customInputID][1] + (e.getKeyCode() - KeyEvent.VK_0);
				
			} else if((e.getKeyCode() >= KeyEvent.VK_NUMPAD0 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9)) {
				
				ar.executionCodes[customInputID][1] = 10 * ar.executionCodes[customInputID][1] + (e.getKeyCode() - KeyEvent.VK_NUMPAD0);
				
			} else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				
				ar.executionCodes[customInputID][1] = (int) (ar.executionCodes[customInputID][1] / 10);
				
			} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				
				allowCustomInput = false;
				
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(showDelOption && e.getKeyCode() == KeyEvent.VK_DELETE) {
			
			if(ar.executionCodes[showDelOptionID][5] == 2 && ar.keysInHoldSet.contains(ar.executionCodes[showDelOptionID][1])
					&& ar.executionCodes[showDelOptionID][0] == 3) {
				
				for(int i = ar.recordedTill - 1; i > showDelOptionID; i--) {
					
					if(ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 3 
							&& ar.executionCodes[showDelOptionID][1] == ar.executionCodes[i][1]) {
						
						break;
						
					}
					
					if(i == showDelOptionID + 1 || ar.executionCodes[i][5] == 1 && ar.executionCodes[i][0] == 3 
							&& ar.executionCodes[showDelOptionID][1] == ar.executionCodes[i][1])
						ar.keysInHoldSet.remove(ar.executionCodes[showDelOptionID][1]);
				}
				
				if(showDelOptionID == ar.recordedTill - 1)
					ar.keysInHoldSet.remove(ar.executionCodes[showDelOptionID][1]);
				
			} else if(ar.executionCodes[showDelOptionID][5] == 1 && !ar.keysInHoldSet.contains(ar.executionCodes[showDelOptionID][1])
					&& ar.executionCodes[showDelOptionID][0] == 3) {
				
				for(int i = ar.recordedTill - 1; i > showDelOptionID; i--) {
					
					if(ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 3 
							&& ar.executionCodes[showDelOptionID][1] == ar.executionCodes[i][1]) {
						
						break;
						
					}
					
					if(i == showDelOptionID + 1 || ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 3 
							&& ar.executionCodes[showDelOptionID][1] == ar.executionCodes[i][1])
						ar.keysInHoldSet.add(ar.executionCodes[showDelOptionID][1]);
				}
				
				if(showDelOptionID == ar.recordedTill - 1)
					ar.keysInHoldSet.add(ar.executionCodes[showDelOptionID][1]);
				
			} else if(ar.executionCodes[showDelOptionID][5] == 2 && ar.mouseInHoldSet.contains(ar.executionCodes[showDelOptionID][2])
					&& ar.executionCodes[showDelOptionID][0] == 1) {
				
				for(int i = ar.recordedTill - 1; i > showDelOptionID; i--) {
					
					if(ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 1 
							&& ar.executionCodes[showDelOptionID][2] == ar.executionCodes[i][2]) {
						
						break;
						
					}
					
					if(i == showDelOptionID + 1 || ar.executionCodes[i][5] == 1 && ar.executionCodes[i][0] == 1 
							&& ar.executionCodes[showDelOptionID][2] == ar.executionCodes[i][2])
						ar.mouseInHoldSet.remove(ar.executionCodes[showDelOptionID][2]);
					
				}
				
				if(showDelOptionID == ar.recordedTill - 1)
					ar.mouseInHoldSet.remove(ar.executionCodes[showDelOptionID][2]);
				
			} else if(ar.executionCodes[showDelOptionID][5] == 1 && !ar.mouseInHoldSet.contains(ar.executionCodes[showDelOptionID][2])
					&& ar.executionCodes[showDelOptionID][0] == 1) {
				
				for(int i = ar.recordedTill - 1; i > showDelOptionID; i--) {
					
					if(ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 1 
							&& ar.executionCodes[showDelOptionID][2] == ar.executionCodes[i][2]) {
						
						break;
						
					}
					
					if(i == showDelOptionID + 1 || ar.executionCodes[i][5] == 2 && ar.executionCodes[i][0] == 1 
							&& ar.executionCodes[showDelOptionID][2] == ar.executionCodes[i][2])
						ar.mouseInHoldSet.add(ar.executionCodes[showDelOptionID][2]);
				}
				
				if(showDelOptionID == ar.recordedTill - 1)
					ar.mouseInHoldSet.add(ar.executionCodes[showDelOptionID][2]);
				
			}
			
			for(int i = showDelOptionID; i < ar.recordedTill - 1; i++) {
				
				for(int j = 0; j < 7; j++) {
					
					ar.executionCodes[i][j] = ar.executionCodes[i + 1][j];
					
				}
				
			}
			
			ar.recordedTill -= 1;
			showDelOption = false;
			
			if(ar.recordedTill <= 6)
				scrollAdder = 0;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_END) {
			
			ar.recordedTill = 0;
			
			ar.keysInHoldSet.clear();
			ar.mouseInHoldSet.clear();
			
			scrollAdder = 0;
			
		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if(scrollAdder - 15 * e.getWheelRotation() <= 0 && e.getWheelRotation() < 0)
			scrollAdder -= 15 * e.getWheelRotation();
			
		else if(scrollAdder - 15 * e.getWheelRotation() <= 0 && allowScrollDown)
			scrollAdder -= 15 * e.getWheelRotation();
		
		else if(allowScrollDown)
			scrollAdder = 0;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(moveWindow.isRunning()) {
			if(toTranslateX + intiX >= (frame.getWidth() / 1.05) && toTranslateX + intiX <= frame.getWidth() && 
					toTranslateY + intiY > (frame.getHeight() / 21) && toTranslateY + intiY < (frame.getHeight() / 1.05)) {
				
				frame.setSize(ogFrameWidth + toTranslateX, frame.getHeight());
				moveFrame = false;
			
			} else if(toTranslateY + intiY >= (frame.getHeight() / 1.05) && toTranslateY + intiY <= frame.getHeight() && 
					toTranslateX + intiX < (frame.getWidth() / 1.05)) {
				
				frame.setSize(frame.getWidth(), ogFrameHeight + toTranslateY);
				moveFrame = false;
				
			} else if(toTranslateY + intiY >= (frame.getHeight() / 1.05) && toTranslateY + intiY <= frame.getHeight() && 
					toTranslateX + intiX >= (frame.getWidth() / 1.05)) {
				
				frame.setSize(ogFrameWidth + toTranslateX, ogFrameHeight + toTranslateY);
				moveFrame = false;
				
			} else if(moveFrame) {
				frame.setLocation(frame.getX() + toTranslateX, frame.getY() + toTranslateY);
			}
		}
		
	}

}