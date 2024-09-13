package blazing;

/**
 *
 * @author hexaredecimal
 */
public class BlazingInfo {
	public static String logo() {
		return String.format(
"""
 ██████╗██╗     █████╗████████████╗   ██╗██████╗██╗    ███████████████╗██╗  ██╗
  ██╔══████║    ██╔══██╚══███╔██████╗  ████╔════╝██║    ████╔════██╔══██╚██╗██╔╝
  ██████╔██║    ███████║ ███╔╝████╔██╗ ████║  █████║ █╗ ███████╗ ██████╔╝╚███╔╝ 
  ██╔══████║    ██╔══██║███╔╝ ████║╚██╗████║   ████║███╗████╔══╝ ██╔══██╗██╔██╗ 
  ██████╔█████████║  █████████████║ ╚████╚██████╔╚███╔███╔█████████████╔██╔╝ ██╗
  ╚═════╝╚══════╚═╝  ╚═╚══════╚═╚═╝  ╚═══╝╚═════╝ ╚══╝╚══╝╚══════╚═════╝╚═╝  ╚═╝
					%s
		https://github.com/hexaredecimal/BlazingWebX
			https://blazingwebx.onrender.com

""", version());	
	}

	public static String version() {
		return "0.2.7";
	}
}
