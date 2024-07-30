# JediGenZ
> A java library for creating webserves and web based user interfaces, while promoting GenZ's abilites and the HATEOAS principle.  

## Why 
>> At the center of this are real reasons that prompted me as a GenZ (born in 2002) to start this project. Those reasons are:
- *Gate Keeping*
- [X] Boomers and `Some(GenX)` like to openly discriminate againg the new generation by stereotyping.
- [X] Boomers and `Some(GenX)` have gatekept the industry using frameworks that solve problems that nobody has (Looking at you `ReactJS`)
- [X] Boomers and `Some(GenX)` have swept HATEAOS under the rug for years inorder to gate keep the industry to their specialized knowledge (Looking at you `Senior React Dev`).
- *Innovation*
- [X] Boomers and `Some(GenX)` have re-invented the wheel every year, since the days of JQuery.
- [X] By re-inventing the wheel one last time, in our gen-z image we can show them that `WE CAN DO ANYTHING THEY CAN`.
- *Competition*
- [X] Frameworks like springboot have plagued the backend scene, gate keeping the environment to outsiders. Having alternative frameworks allows us to have a choice when writing backend services such as servers in java.
- [X] We intend to stop the worship of frameworks like `Vaadin` and `SpringBoot`.

>> As it currently stands, the industry treats frameworks as means to get into heaven. It's mostly like `Use React and get your sins forgiven` or `Use spring boot to avoid the devil`. You know who the devil is? the people who start projects and center their lives around frameworks and libraries.... yes, I'm taking to you Boomer React dev, to you GenX Spring Boot enjoyer and to you GenZ follower of hype trends.

# Example
### Web server
>> Coming Soon

### UI creation
```java
BlazeContainerElement container = new Main()
    .addChild(
        new P().className("text-white")
            .addChild(
                new H1("hello").className("text-lg")
                    .addChild(new Br())
                    .addChild(
                        new Div().style("border: 1px solid red")
                            .addChild(
                                new Img().src("/path/to/image")
                                    .alt("A picture of my cat")
                                    .className("w-full h-full")
                            )
                    )
                    .addChild(
                        new Span().attr("temp", "12344")
                            .attr("uuid", "0x0001")
                    )
                    .addChild(new Hr())
            )
    );
```
### HATEOAS Examples
>> Coming Soon



 

