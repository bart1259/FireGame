package coreEngine;

import java.util.HashMap;

import misc.Vector2;

public class Input{
	
	public static HashMap<Integer, Boolean> keyPressDict;
	public static HashMap<Integer, Boolean> mousePressDownDict;
	public static HashMap<Integer, Boolean> mousePressUpDict;
	
	private static Vector2 mousePosition = new Vector2(0,0);
	
	//Keyboard keys
	public enum KeyCode{
		
		A(65),
		B(66),
		C(67),
		D(68),
		E(69),
		F(70),
		G(71),
		H(72),
		I(73),
		J(74),
		K(75),
		L(76),
		M(77),
		N(78),
		O(79),
		P(80),
		Q(81),
		R(82),
		S(83),
		T(84),
		U(85),
		V(86),
		W(87),
		X(88),
		Y(89),
		Z(90),
		Space(32),
		Tab(9),
		Shift(16),
		Ctrls(17),
		Alt(18),
		Alpha0(48),
		Alpha1(49),
		Alpha2(50),
		Alpha3(51),
		Alpha4(52),
		Alpha5(53),
		Alpha6(54),
		Alpha7(55),
		Alpha8(56),
		Alpha9(57),
		LeftArrow(37),
		UpArrow(38),
		RightArrow(39),
		DownArrow(40),
		Escape(27);
		
		int value;
		private KeyCode(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	//The mouse codes
	public enum MouseCode{
		
		left(16),
		right(4),
		middle(8);
		
		int value;
		private MouseCode(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}

	
	public static void Initialize() {
		keyPressDict = new HashMap<Integer, Boolean>();
		mousePressDownDict = new HashMap<Integer, Boolean>();
		mousePressUpDict = new HashMap<Integer, Boolean>();
	}
	
	//Clears the dictionaries
	public static void Update() {
		mousePressDownDict.clear();
		mousePressUpDict.clear();
	}
	
	//Return whether or not the key was pressed
	public static boolean GetKey(KeyCode key) {
		if(keyPressDict.containsKey(key.getValue()) && keyPressDict.get(key.getValue()) == true) {
			return true;
		}
		return false;
	}
	
	/**
	 Don't call this method unless you are trying to emulate mouse movement
	 x,y where the mouse was moved to
	 */
	public static void MouseMoved(float x, float y) {
		mousePosition = new Vector2(x,y);
	}
	
	//Returns the position of the mouse
	public static Vector2 getMousePosition() {
		return mousePosition;
	}
	
	//Return whether or not the mouse button was pressed
	public static boolean getMouseButtonDown(MouseCode mouseCode) {
		if(mousePressDownDict.containsKey(mouseCode.getValue()) && mousePressDownDict.get(mouseCode.getValue()) == true) {
			return true;
		}
		return false;
	}
	
	//Return whether or not the mouse button was released
	public static boolean getMouseButtonUp(MouseCode mouseCode) {
		if(mousePressUpDict.containsKey(mouseCode.getValue()) && mousePressUpDict.get(mouseCode.getValue()) == true) {
			return true;
		}
		return false;
	}
	
	/**
	 Don't call this method unless you are trying to emulate a mouse button presses
	 @param code The code of the button code pressed
	 */
	public static void MousePressed(int code, boolean pressed) {
		if(pressed)
			mousePressDownDict.put(code, true);
		else
			mousePressUpDict.put(code, true);
	}
	
	/**
	 Don't call this method unless you are trying to emulate a key stroke
	 @param code The code of the key pressed
	 */
	public static void KeyPressed(int code) {
		keyPressDict.put(code, true);
	}
	
	/**
	 Don't call this method unless you are trying to emulate a key stroke
	 @param code The code of the key released
	 */
	public static void KeyReleased(int code) {
		keyPressDict.put(code, false);
	}
	
}
