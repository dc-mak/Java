// Ex 16.15: Colour chooser using a HashMap.

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class ColourPalette {
	private final Color[] colors = 
		{Color.BLACK,	Color.BLUE,			Color.CYAN,		Color.DARK_GRAY,
		 Color.GREEN,	Color.LIGHT_GRAY,	Color.MAGENTA,	Color.ORANGE,
		 Color.PINK,	Color.RED,			Color.WHITE,	Color.YELLOW};
	private final String[] names =
		{"Black", "Blue", "Cyan", "Dark Gray", "Green",
			"Light Gray", "Magenta", "Orange", "Pink", "Red"};
	private final Map<String, Color> map = new HashMap<>(12);
	public ColourPalette() {
		for (int i = 0; i < names.length; i++)
			map.put(names[i], colors[i]);
	}
	public Color getColor(String name) { return map.get(name); }
}
// or just return Collections.unmodifiableMap(map);
