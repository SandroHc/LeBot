package sandrohc.ircbot.utils;

public class TextEffectUtil {
	public static enum EFFECT { BOLD("\u0002"), ITALIC(""), UNDERLINE(""), PLAIN("\u000F"), COLOR("\u0003");
		private String unicodeChar;

		EFFECT(String unicodeChar) {
			this.unicodeChar = unicodeChar;
		}

		public String getChar() {
			return unicodeChar;
		}
	}
	public static enum COLOR { WHITE(0), BLACK(1), BLUE(2), GREEN(3), LIGHT_GREEN(9), LIGHT_RED(4), BROWN(5), PURPLE(6), ORANGE(7), YELLOW(8), CYAN(10), LIGHT_CYAN(11), LIGHT_BLUE(12), PINK(13), GREY(14), LIGHT_GREY(15);
		private byte colorCode;

		COLOR(int colorCode) {
			this.colorCode = (byte) colorCode;
		}

		public byte getColor() {
			return colorCode;
		}
	}

	public static String applyEffect(String str, EFFECT effect) {
		return applyEffect(str, effect, EFFECT.PLAIN);
	}

	public static String applyEffect(String str, EFFECT effect, EFFECT defaultEffect) {
		return effect.getChar() + str + defaultEffect.getChar();
	}

	public static String applyColor(String str, COLOR foreground, COLOR background) {
		return EFFECT.COLOR.getChar() + foreground.getColor() + ',' + background.getColor() + str + EFFECT.PLAIN.getChar();
	}

	public static String applyColor(String str, COLOR foreground, COLOR background, COLOR defaultForeground, COLOR defaultBackground) {
		return EFFECT.COLOR.getChar() + foreground.getColor() + ',' + background.getColor() + str + EFFECT.COLOR.getChar() + defaultForeground.getColor() + ',' + defaultBackground.getColor();
	}
}
