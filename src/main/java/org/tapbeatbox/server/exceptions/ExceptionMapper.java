package org.tapbeatbox.server.exceptions;

import org.tapbeatbox.server.config.AppConfig;
import org.tapbeatbox.server.resources.CustomMessageResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.net.ConnectException;

/**
 * This class will handel application wide un handled exceptions.
 *
 * @author Chathura Widanage
 */
@Provider
public class ExceptionMapper implements ExtendedExceptionMapper<Exception> {
    private final Logger logger = LogManager.getLogger(ExceptionMapper.class);

    @Override
    public boolean isMappable(Exception exception) {
        return !(exception instanceof WebApplicationException);
    }

    @Override
    public Response toResponse(Exception ex) {
        logger.error("Error occurred.", ex);

        //customizing error messages to give a proper feedback
        String customMessage = "";
        if (ex.getCause() instanceof ConnectException) {
            logger.error("Can't connect to the API server");
            customMessage = "Your application failed when connecting to the API server. Please check whether AppConfig.REMOTE_HOST ("+ AppConfig.REMOTE_HOST+") is working properly.";
        }

        return Response.status(500)
                .entity(new CustomMessageResource("Error occurred : {} {}", ex.getMessage(), customMessage))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
