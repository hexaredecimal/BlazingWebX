package blazing;

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
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author hexaredecimal
 */
public class Blazing {

	/**
	 * A simple way of creating and starting a webserver.
	 *
	 * <pre>
	 * {@code
	 * ...
	 * Blazing.createServer(WebServer.class);
	 * ...
	 * }
	 * </pre>
	 *
	 * The webserver class is expected to be marked with the following: @WebServer
	 * and have methods marked with @Route
	 *
	 * @see blazing.WebServer
	 * @see blazing.Route
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

			if (root_cls.isAnnotationPresent(StaticMarks.class)){
				var annotations = root_cls.getAnnotationsByType(Static.class);
				for (var annotation: annotations) {
					String static_path = annotation.value(); 
					System.out.println("Registering static context path " + static_path);
					server.createContext(static_path, new StaticContext(static_path.replace('/', ' ').trim()));
				}
			}
			
			var methods = root_cls.getMethods();

			System.out.println("Searching for @Initializer methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Initializer.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasNoArg = method.getParameterCount() == 0;
					return isAnnotated && isStatic && hasNoArg;
				})
				.map(method -> {
					System.out.printf("Initializing the server with %s".indent(0), method.getName());
					try {
						method.invoke(null);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
					return method;
				})
				.collect(Collectors.toList());

			System.out.println("Searching for @Route methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Route.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).map(method -> {
				String path = method.getAnnotation(Route.class).value();
				System.out.printf("Registered a route using @Route(`%s`)\n", path);
				server.createContext(path, (HttpExchange he) -> {
					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
				});
				return method;
			}).collect(Collectors.toList());

			System.out.println("Searching for @Post methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Post.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).map(method -> {
				String type = "POST";
				String path = method.getAnnotation(Post.class).value();
				System.out.printf("Registered a %s route @Post(`%s`)\n", type.toLowerCase(), path);
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
				});
				return method;
			}).collect(Collectors.toList());

			System.out.println("Searching for @Put methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Put.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).map(method -> {
				String type = "PUT";
				String path = method.getAnnotation(Put.class).value();
				System.out.printf("Registered a %s route @Put(`%s`)\n", type.toLowerCase(), path);
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
				});
				return method;
			}).collect(Collectors.toList());

			System.out.println("Searching for @Get methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Get.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).map(method -> {
				String type = "GET";
				String path = method.getAnnotation(Get.class).value();
				System.out.printf("Registered a %s route @Get(`%s`)\n", type.toLowerCase(), path);
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
				});
				return method;
			}).collect(Collectors.toList());

			System.out.println("Searching for @Delete methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Delete.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).map(method -> {
				String type = "DELETE";
				String path = method.getAnnotation(Delete.class).value();
				System.out.printf("Registered a %s route @Post(`%s`)\n", type.toLowerCase(), path);
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
					}
				});
				return method;
			}).collect(Collectors.toList());

			System.out.println("Done initializing server\n");

			server.setExecutor(null); // Use the default executor
			System.out.println("Starting server :)");
			server.start();
			System.out.printf("Server is running at: localhost:%d\n", port);
		} catch (IOException ex) {
			Logger.getLogger(Blazing.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
