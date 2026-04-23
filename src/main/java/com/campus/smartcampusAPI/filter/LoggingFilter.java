package com.campus.filter;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    
    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        logger.info(String.format("→ %s %s", 
            request.getMethod(), 
            request.getUriInfo().getRequestUri()));
    }
    
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        logger.info(String.format("← %d %s", 
            response.getStatus(), 
            request.getUriInfo().getRequestUri()));
    }
}