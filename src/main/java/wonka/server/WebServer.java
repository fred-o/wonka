package wonka.server;

import java.io.*;
import java.util.*;
import wonka.html.page.*;
import wonka.html.tag.*;
import static wonka.html.tag.Attribute.*;
import static wonka.html.tag.Tags.*;
import static wonka.server.Dispatchers.*;

public class WebServer {
	public static void main(String argv[]) throws Exception {
		SimpleDispatcherChain chain = new SimpleDispatcherChain();
		chain.add(prefix("/hello", 
						 new StaticPage(new Html(new Body(text("Hello, world!"))))));
		chain.add(prefix("/time", 
						 new Action() {
							 public Response act(Request request) {
								 return new SimpleResponse(
									 new StaticPage(
										 new Html(
											 new Head(stylesheet("/dir/css/style.css")),
											 new Body(new P(text("Today's date and time is: " 
																 + new Date().toString()))))));
							 }
						 }));
		chain.add(dir("/dir", new File("src/test/resources/html")));
		chain.add(prefix("/params", 
						 new Action() {
							 public Response act(Request request) {
								 List<Tag> bodyTags = new ArrayList<Tag>();
								 for(Map.Entry<String, String> entry: request.getParameters().entrySet()) {
								  	 bodyTags.add(text(entry.getKey(), ": ", entry.getValue()));
								 }
								 return new SimpleResponse(
									 new Html(
										 new Head(stylesheet("/dir/css/style.css")),
										 new Body(
											 list(new P("As paragraphs:"))
											 .and(new Div(wrap("Request parameters.").and(bodyTags).in(P.class)))
											 .and(new P("As table"))
//											 .and(table(request.getParameters().entrySet()))
											 )
										 )
									 );
							 }
						 }));
		HttpServer server = new HttpServer();
		server.setDispatcherChain(chain);
		server.run();
	}
}
