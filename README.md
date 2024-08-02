# JediGenZ

## About
> - JediGenZ is a Java library that aims to allows you to build websites and web applications 100% in java, without writing html and javascript directly. This goal is achieved through the use of java classes which are combined together to create an elegent user experience while keeping your projects minimal.
> 
> - JediGenZ also aims to remove the complexity associated with creating well structured and easily maintainable web applications while also minimizing the complexities that rise with complex state management.

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

## Components
You can extend the builtin library elements to create advanced components. Here is an example of a simple digital clock component. 

`ClockComponent.java`
```java
package clockserver;

import genz.Br;
import genz.Div;
import genz.GenZElement;
import genz.H1;
import genz.Html;
import genz.P;
import java.util.Calendar;

public class ClockComponent extends GenZElement {
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
			.addChild(
				new P(_hour)
					.hxTrigger("every 1s")
					.hxGet("/hour")
			)
			.addChild(
				new P(":")
			)
			.addChild(
				new P(_minute)
					.hxTrigger("every 1s")
					.hxGet("/minute")
			)
			.addChild(
				new P(":")
			)
			.addChild(
				new P(_second)
					.hxTrigger("every 1s")
					.hxGet("/second")
			)
			.addChild(
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
            .className("text-2xl") // Use tailwind on the component
    ); 
...

@Route("/hour")
public static void hour(JediRespose reponse) {
    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    response.sendResponse(String.format("%d", hour)); // 
}

```
This allows you to create complex components that abstract away their implementation, your server only has to respond to the requests send by the components. 
This results in a simple project structure, one with zero configuration outside of adding jediGenZ to your build system as a dependency.  

## Why
- Remove the complexity associated with creating webapps.
- Allow for reuse of tools and knowledge, hence not making tertiary education completely useless by imposing weird industry standards for frameworks like how ReactJs, Angular etc do.
- Remove the middle man UI designer. Anyone knows how to use java (This statement is made in with my university cs class in mind. Almost everyone knows how to write java applications, but struggle with JS due to a lack of exposure), so why not make the UI in java. Everyone works, everyone wins.
- Remove templates by having the UI written as classes that represent components. Through Inheritence new components can be created.
- Minimize the bridge between the backend and the fontend.

## Todo
- Server initialization Anotation `@Initializer` which will allow any method in the server taged with it to run before the server starts
- SQL Intergration
- SQL Data Binding
- 
##### API example (THIS DOES NOT EXIST YET)
```java
      @DataBind
      class Person {
        String name;
        int age;
        double height;

        public Person(String name, int age, double height) {
           this.name = name;
           this.age = age;
           this.height = height;
        }
      }
  
      @WebServer
      public class Server {
        private static JediSql connection;

        @Initilializer
        public static void init() {
          connection = new JediSqlConnection("sqllite", "Main.db");
        }

       @Route("/users")
       public static void users() {
          JediQueryResult result = connection
              .select(Query.List("names", "age", "height"))
              .from("Users")
              .as(Person.class); 
       }
      }
      
  ```


## Star history
[![Star History Chart](https://api.star-history.com/svg?repos=hexaredecimal/JediGenZ&type=Date)](https://star-history.com/#hexaredecimal/JediGenZ&Date)



