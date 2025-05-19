package lt.biblioteka.biblioteka.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api") // Access via /api/readers
public class ApplicationConfig extends Application {
}
