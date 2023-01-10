package nnrg.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontStyle {
	public InputStream stream = FontStyle.class.getResourceAsStream("/ka1.ttf");
	public InputStream stream2 = FontStyle.class.getResourceAsStream("/pixeboy.ttf");
	public static Font font,font2;

	public FontStyle() {
		try {

			font = Font.createFont(Font.TRUETYPE_FONT, stream);
			font2 = Font.createFont(Font.TRUETYPE_FONT, stream2);
		} catch (FontFormatException e) {
			System.out.println("Formato n�o suportado" + e.getCause());
		} catch (IOException e) {
			System.out.println("Não foi localizado a fonte" + e.getCause());
			System.out.println(e);
		}
	}

	public static Font getFont(int size, int style) {
		return (font != null ? font.deriveFont((float) size).deriveFont(style) : new Font("arial", style, size));
	}
	public static Font getFont2(int size, int style) {
		return (font2 != null ? font2.deriveFont((float) size).deriveFont(style) : new Font("arial", style, size));
	}
}
