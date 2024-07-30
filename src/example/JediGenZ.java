package example;

import genz.GenZContainerElement;
import genz.Main;
import genz.H1;
import genz.Br;
import genz.P;
import genz.Hr;
import genz.Span;
import genz.Img;
import genz.GenZElement;
import genz.Div;

/**
 *
 * @author hexaredecimal
 */
public class JediGenZ {

  public static void main(String[] args) {
    GenZContainerElement e = new Main().
      addChild(
        new P()
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
          ));

    System.out.println("" + e.render());
  }
}
