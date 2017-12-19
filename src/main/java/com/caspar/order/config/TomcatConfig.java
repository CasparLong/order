package com.caspar.order.config;

import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
//@Configuration
public class TomcatConfig {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcatServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatServletContainerFactory.addContextCustomizers((TomcatContextCustomizer) context -> {
            SecurityConstraint constraint = new SecurityConstraint();
            SecurityCollection collection = new SecurityCollection();
            // http-method
            collection.addMethod("PUT");
            collection.addMethod("DELETE");
            collection.addMethod("HEAD");
            collection.addMethod("OPTIONS");
            collection.addMethod("TRACE");
            // url-pattern
            collection.addPattern("/*");
            constraint.addCollection(collection);
            constraint.setAuthConstraint(true);
            context.addConstraint(constraint);
        });
        return tomcatServletContainerFactory;
    }

}
