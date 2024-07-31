# JediGenZ
> A java library for creating webserves and web based user interfaces, while promoting GenZ's abilites and the HATEOAS principle.  

# Example
#### Web server
>> Coming Soon
>

#### UI creation
```java
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

```

<<<<<<< HEAD
#### HATEOAS Examples
>> Coming Soon


