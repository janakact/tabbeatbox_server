package org.tapbeatbox.server.filters;

import org.tapbeatbox.server.config.AppConfig;
import org.tapbeatbox.server.resources.CustomMessageResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

/**
 * This class will mediate each request and check for the validity of the student KEY.
 *
 * @author Chathura Widanage
 */
@Provider
public class AuthRequestFilter implements ContainerRequestFilter {
    private Logger logger = LogManager.getLogger(AuthRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> auth = requestContext.getHeaders().get("Authorization");
        String studentKey;
        if (auth == null || (studentKey = auth.get(0)) == null) {
            logger.error("A request received with an empty student key.");
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new CustomMessageResource("Please set a valid student key in the request Authorization header.")).build()
            );
            return;
        } else if (!studentKey.equals(AppConfig.STUDENT_KEY)) {
            logger.error("A request received with an invalid student key.");
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new CustomMessageResource("The student key is invalid.")).build()
            );
            return;
        }
    }
}
