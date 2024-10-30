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
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jdk.jfr.Description;

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
			BlazingLog.severe(
				String.format("Error: Class `%s` is not a webserver. Add `@WebServer` annotation before the class definition",
					root_cls.getSimpleName())
			);
			System.exit(1);
			return;
		}

		
		BlazingLog.fine("\n".repeat(2).concat(BlazingInfo.logo()));

		String _port = root_cls.getAnnotation(WebServer.class).value();
		int port = Integer.parseInt(_port);
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

			var annotations = root_cls.getAnnotationsByType(Static.class);
			for (var annotation : annotations) {
				String static_path = annotation.value();
				BlazingLog.info(String.format("Registering static context path %s", static_path));
				String clean_path = static_path.replaceFirst("/", "");
				server.createContext(static_path, new StaticContext(clean_path.trim()));
			}

			var methods = root_cls.getMethods();

			BlazingLog.info("Searching for @Initializer methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Initializer.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasNoArg = method.getParameterCount() == 0;
					return isAnnotated && isStatic && hasNoArg;
				})
				.forEach(method -> {
					BlazingLog.info(String.format("Initializing the server with %s", method.getName()));
					try {
						method.invoke(null);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						Throwable cause = ex.getCause();
						BlazingLog.severe(cause.toString());
					}
				});

			BlazingLog.info("Searching for @Route methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Route.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String path = method.getAnnotation(Route.class).value();
				BlazingLog.info(String.format("Registered a route using @Route(`%s`)", path));
				server.createContext(path, (HttpExchange he) -> {
					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});

			BlazingLog.info("Searching for @Post methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Post.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String type = "POST";
				String path = method.getAnnotation(Post.class).value();
				BlazingLog.info(String.format("Registered a %s route @Post(`%s`)", type.toLowerCase(), path));
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}
					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});

			BlazingLog.info("Searching for @Put methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Put.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String type = "PUT";
				String path = method.getAnnotation(Put.class).value();
				BlazingLog.info(String.format("Registered a %s route @Put(`%s`)", type.toLowerCase(), path));
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});

			BlazingLog.info("Searching for @Get methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Get.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String type = "GET";
				String path = method.getAnnotation(Get.class).value();
				BlazingLog.info(String.format("Registered a %s route @Get(`%s`)", type.toLowerCase(), path));
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});


			BlazingLog.info("Searching for @Patch methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Patch.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String type = "PATCH";
				String path = method.getAnnotation(Put.class).value();
				BlazingLog.info(String.format("Registered a %s route @Patch(`%s`)", type.toLowerCase(), path));
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});


			BlazingLog.info("Searching for @Head methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Head.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				}).forEach(method -> {
				String type = "HEAD";
				String path = method.getAnnotation(Put.class).value();
				BlazingLog.info(String.format("Registered a %s route @Head(`%s`)", type.toLowerCase(), path));
				server.createContext(path, (HttpExchange he) -> {
					String requestMethod = he.getRequestMethod();
					if (!requestMethod.equals(type)) {
						return;
					}

					var response = new BlazingResponse(he);
					try {
						method.invoke(null, response);
					} catch (IllegalAccessException | InvocationTargetException ex) {
						BlazingLog.severe(ex.toString());
					}
				});
			});

			
			BlazingLog.info("Searching for @Delete methods");
			Stream.of(methods)
				.filter(method -> {
					var isAnnotated = method.isAnnotationPresent(Delete.class);
					var isStatic = Modifier.isStatic(method.getModifiers());
					var hasOneArg = method.getParameterCount() == 1;
					var paramTypes = method.getParameterTypes();
					return isAnnotated && isStatic && hasOneArg && paramTypes[0].getName().equals(BlazingResponse.class.getName());
				})
				.forEach(method -> {
					String type = "DELETE";
					String path = method.getAnnotation(Delete.class).value();
					BlazingLog.info(String.format("Registered a %s route @Post(`%s`)", type.toLowerCase(), path));
					server.createContext(path, (HttpExchange he) -> {
						String requestMethod = he.getRequestMethod();
						if (!requestMethod.equals(type)) {
							return;
						}

						var response = new BlazingResponse(he);
						try {
							method.invoke(null, response);
						} catch (IllegalAccessException | InvocationTargetException ex) {
							BlazingLog.severe(ex.toString());
						}
					});
				});

			var shutdownListener = new Thread() {
				public void run() {
					BlazingLog.info("Searching for @Destructor methods");
					Stream.of(methods)
						.filter(method -> {
							var isAnnotated = method.isAnnotationPresent(Destructor.class);
							var isStatic = Modifier.isStatic(method.getModifiers());
							var hasNoArg = method.getParameterCount() == 0;
							return isAnnotated && isStatic && hasNoArg;
						})
						.forEach(method -> {
							BlazingLog.info(String.format("Calling De-initializer named: %s".indent(0), method.getName()));
							try {
								method.invoke(null);
							} catch (IllegalAccessException | InvocationTargetException ex) {
								Throwable cause = ex.getCause();
								BlazingLog.severe(cause.toString());
							}
						});
					BlazingLog.info("Stopping server server :)");
					server.stop(0);
				}
			};
			Runtime.getRuntime().addShutdownHook(shutdownListener);

			BlazingLog.info("Done initializing server!");

			server.setExecutor(null); // Use the default executor
			BlazingLog.info("Starting server :)");
			server.start();
			BlazingLog.info(String.format("Server is running at: localhost:%d", port));
		} catch (IOException ex) {
			BlazingLog.severe(ex.toString());
		}
	}

}
