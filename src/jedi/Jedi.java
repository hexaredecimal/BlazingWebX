package jedi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hexaredecimal
 */
public class Jedi {

	/**
	 * A simple way of creating and starting a webserver.
	 * 
	 *<pre>
	 * {@code
	 * 	...
	 * 	Jedi.createServer(WebServer.class);
	 * 	...
	 * }
	 *</pre>
	 * 
	 * The webserver class is expected to be marked with the following: @WebServer and have methods marked with @Route
	 * @see jedi.WebServer
	 * @see jedi.Route
	 * 
	 * @param root_cls The class which represent the web server.
	 */
	public static void createServer(Class<?> root_cls) {
		if (!root_cls.isAnnotationPresent(WebServer.class)) {
			System.err.println("Error: Class " + root_cls.getSimpleName() + " is not a webserver. Add `@WebServer` annotation before the class definition");
			System.exit(1);
			return;
		}

		String _port = root_cls.getAnnotation(WebServer.class).value();
		int port = Integer.parseInt(_port);
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			var methods = root_cls.getMethods();

			List<String> errors = new ArrayList<>();
			for (var method : methods) {
				var isAnnotated = method.isAnnotationPresent(Route.class);
				var isStatic = Modifier.isStatic(method.getModifiers());
				var hasOneArg = method.getParameterCount();
				var paramTypes = method.getParameterTypes();
				if (isAnnotated && isStatic) {
					if (hasOneArg != 1) {
						var msg = String.format(
							"Error: Method `%s` is expected to have only 1 parameter of type `JediResponse`.",
							method.getName()
						);
						errors.add(msg);
						break;
					}

					if (!paramTypes[0].getName().equals(JediResponse.class.getName())) {
						var msg = String.format(
							"Error: Method `%s` has 1 parameter as expected but its not of type `JediResponse`.",
							method.getName()
						);
						errors.add(msg);

					}

					registerRouteMethod(server, method);
				} else if (isAnnotated && !isStatic) {
					var msg = String.format(
						"Error: Method `%s` is annotated as a Route but not defined as static. Define the method as static.",
						method.getName()
					);
					errors.add(msg);
				}
			}

			if (!errors.isEmpty()) {
				for (String error : errors) {
					System.err.println(error);
				}
				System.exit(1);
				return;
			}

			server.setExecutor(null); // Use the default executor
			System.out.println("Starting server :)");
			server.start();
			System.out.printf("Server is running at: localhost:%d\n", port);
		} catch (IOException ex) {
			Logger.getLogger(Jedi.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void registerRouteMethod(HttpServer server, Method method) {
		String path = method.getAnnotation(Route.class).value();
		System.out.printf("Registered a route @ `%s`\n", path);
		server.createContext(path, (HttpExchange he) -> {
			var response = new JediResponse(he);
			try {
				method.invoke(null, response);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(Jedi.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvocationTargetException ex) {
				Logger.getLogger(Jedi.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
}
