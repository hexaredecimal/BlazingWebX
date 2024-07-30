package blazehtml;

import blaze.BlazeElement;
import blaze.Div;
import blaze.*;

/**
 *
 * @author hexaredecimal
 */
public class BlazeHTML {

	public static void main(String[] args) {
		BlazeContainerElement e = new P()
			.addAttribute("class", "text-white")
			.addChild(
				new H1("hello")
					.className("text-lg")
					.addChild(new Br())
					.addChild(
						new Div()
							.addAttribute("style", "border: 1px solid red")
							.addChild(
								new Img()
									.src("/path/to/imag")
									.alt("A picture of my cat")
									.className("w-full h-full")
							)
					)
					.addChild(
						new Span()
							.addAttribute("temp", "12344")
							.addAttribute("uuid", "0x0001")
					)
					.addChild(new Hr())
			);

		BlazeContainerElement container = new Main()
				.addChild(e);

		System.out.println("" + container.render());
	}
}
