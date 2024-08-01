# JediGenZ

## About
> - JediGenZ is a 100% Java library that aims to allows you to build websites and web applications 100% in java, without writing html and javascript directly. This goal is achieved through the use of java classes are compined together to create an elegent user experience while keeping your projects minimal.
> 
> - JediGenZ also aims to remove the complexity associated with creating well structured and easily maintainable web applications while also minimizing the complexities that rise with complex state management.
> 
> - JediGenZ, as library created by genZ aims to raise awareness of the discrimination directed towards the younger generation, spearheaded by the millenials. Usually demonstrated in the form of stereotypes such as "genz being unemployable as a generation". 

## Features
- Web Server API
- Web UI API
- Builtin HTMX API

## Example
>> You basically create 2 classes. Your program class containing your java entry point. This class registes your Server class.

`Program.java`
```java
package example;

import jedi.Jedi;

public class Program {
    public static void main() {
        Jedi.createServer(HelloWorldServer.class);
    }
}
```
Then follows your server class. The library uses java anotations to find methods used for route paths and for setting up the server.

`HelloWorldServer.java`
```java
package example;

import jedi.Route;
import jedi.WebServer;
import jedi.JediResponse;
import genz.*; 

@WebServer("6900")
public class HelloWorldServer {
    @Route("/")
    public static void home(JediResponse reponse) {
        var page = new Html()
            .addChild(
                new Button("Click Me")
                    .hxPost("/hello")
                    .hxSwap("outerHTML")
            );

        response.sendUiResponse(page);
    }

    @Route("/hello")
    public static void hello(JedResponse response) {
        response.sendUiResponse(new P("Hello, world)); // Send a <p> Hello, world </p>
    } 
}
```
Run your program and visit https://localhost:6900 or whateve port you chose. 
You should have an button with the text `Click me` and if you trigger the click event the text `Hello, World` should replace 
the button. 

## Why
- Remove the complexity associated with creating webapps.
- Allow for reuse of tools and knowledge, hence not making tertiary education completely useless by imposing weird industry standards for frameworks like how ReactJs, Angular etc do.
- Remove the middle man UI designer. Anyone knows how to use java (This statement is made in with my university cs class in mind. Almost everyone knows how to write java applications, but struggle with JS due to a lack of exposure), so why not make the UI in java. Everyone works, everyone wins.
- Remove templates by having the UI written as classes that represent components. Through Inheritence new components can be created.
- Minimize the bridge between the backend and the fontend.

## Star history
[![Star History Chart](https://api.star-history.com/svg?repos=hexaredecimal/JediGenZ&type=Date)](https://star-history.com/#hexaredecimal/JediGenZ&Date)



