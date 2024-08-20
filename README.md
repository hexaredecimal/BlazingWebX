# BlazingWebx

## About
> - BlazingWebx is a Java library that aims to allows you to build single page web applications (SPAs) 100% in Java, without writing html and JavaScript directly. This goal is achieved through the use of Java classes which are combined together to create an elegant user experience leveraging HTMX for client and server communication while keeping your projects minimal.
> 
> - BlazingWebx also aims to simplify server side programming and how the client and server pass data to each other. 

## Features
- [X] Web Server API
- [X] Web UI API (Implements All HTML5 elements)
- [X] Builtin HTMX API
- [X] Static content Server
- [X] Support for most used HTTP methods
- [ ] SQL intergration
- [ ] SQL Integration
- [ ] SQL Data Binding

## Example
>> You basically create 2 classes. Your program class containing your java entry point. This class registers your Server class.

`Program.java`
```java
package example;

import blazing.Blazing;

public class Program {
    public static void main() {
        Blazing.createServer(HelloWorldServer.class);
    }
}
```
Then follows your server class. The library uses java annotations to find methods used for route paths and for setting up the server.

`HelloWorldServer.java`
```java
package example;

import blazing.Route;
import blazing.WebServer;
import blazing.JediResponse;
import webx.*; 

@WebServer("6900")
@Static("/images")
public class HelloWorldServer {
    
    @Initializer
    public static void init() {
      // NOTE: Space for loading/connecting server resources such as databased. 
    }
    
    @Route("/")
    public static void home(BlazingResponse reponse) {
        var page = new Html()
            .addChild(
                new Button("Click Me")
                    .hxPost("/hello")
                    .hxSwap("outerHTML")
            );

        response.sendUiResponse(page);
    }

    @Route("/hello")
    public static void hello(BlazingResponse response) {
        response.sendUiResponse(new P("Hello, world)); // Send a <p> Hello, world </p>
    } 
}
```
Run your program and visit https://localhost:6900 or whatever port you chose. 
You should have an button with the text `Click me` and if you trigger the click event the text `Hello, World` should replace 
the button. 

## Components
You can extend the builtin library elements to create advanced components. Here is an example of a simple digital clock component. 

`ClockComponent.java`
```java
package clockserver;

import webx.Br;
import webx.Div;
import webx.GenZElement;
import webx.H1;
import webx.Html;
import webx.P;
import java.util.Calendar;

public class ClockComponent extends WebXElement {
	private int hour;
	private int minute;
	private int second;
	private String amPm;

	@Override
	public String render() {
		hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);
		second = Calendar.getInstance().get(Calendar.SECOND);

		var _hour = hour < 10 ? "0" + hour : String.valueOf(hour);
		var _minute = minute < 10 ? "0" + minute : String.valueOf(minute);
		var _second = second < 10 ? "0" + second : String.valueOf(second);

		if (hour >= 0 && hour < 12) {
			amPm = "AM";
		} else {
			amPm = "PM";
		}

		return new Div()
			.addChildren(
				new P(_hour)
					.hxTrigger("every 1s")
					.hxGet("/hour"),
				new P(":"),
				new P(_minute)
					.hxTrigger("every 1s")
					.hxGet("/minute"),
				new P(":"),
				new P(_second)
					.hxTrigger("every 1s")
					.hxGet("/second"),
				new P(amPm)
					.hxTrigger("every 1s")
					.hxGet("/ampm")
			)
			.className("flex flex-row text-lx")
			.render();
	}
}
```
You can then use this component normally like any other element. 

```java
...
new Div()
    .addChild(
        new ClockComponent()
    ); 
...

@Route("/hour")
public static void hour(BlazingRespose reponse) {
    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    response.sendResponse(String.format("%d", hour)); // 
}

```
This allows you to create complex components that abstract away their implementation, your server only has to respond to the requests send by the components. 
This results in a simple project structure, one with zero configuration outside of adding BlazingWebx to your build system as a dependency.  

## Why
- Remove the complexity associated with creating web applications.
- Remove templates by having the UI written as classes that represent components. Through inheritance new components can be created.
- Minimize the bridge between the backend and the frontend.

## Requirements
- Java 21 or greater
- Your favourite IDE/TextEditor


## Download
- [latest](https://github.com/hexaredecimal/BlazingWebX/releases)
- [documentation](https://blazingwebx.onrender.com/javadoc/index.html)


## Star history
[![Star History Chart](https://api.star-history.com/svg?repos=hexaredecimal/BlazingWebX&type=Date)](https://star-history.com/#hexaredecimal/BlazingWebX&Date)

## Reference
- [htmx](https://htmx.org/)
- [awesomecss](https://github.com/troxler/awesome-css-frameworks?tab=readme-ov-file)

