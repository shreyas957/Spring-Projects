package dev.shreyas;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.pattern.PathPatternParser;


/**
 * The DispatcherServlet, as any Servlet, needs to be declared and mapped according to the
 * Servlet specification by using Java configuration or in web.xml.
 * In turn, the DispatcherServlet uses Spring configuration to discover the delegate components it needs for request mapping,
 * view resolution, exception handling, and more.
 * The following example of the Java configuration registers and initializes the DispatcherServlet,
 * which is auto-detected by the Servlet container (see Servlet Config)
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class);

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}


/**
 * @EnableWebMvc imports DelegatingWebMvcConfiguration, which:
 * Provides default Spring configuration for Spring MVC applications
 * Detects and delegates to WebMvcConfigurer implementations to customize that configuration.
 */
@Configuration
@EnableWebMvc  // @EnableWebMvc annotation to enable MVC configuration with programmatic configuration
class WebConfiguration {

}

// Implement WebMvcConfigurer also to customize the configuration provided by @EnableWebMvc
class WebMvcConfig implements WebMvcConfigurer {
    // example
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    // By default, formatters for various number and date types are installed,
    // along with support for customization via @NumberFormat, @DurationFormat, and @DateTimeFormat on fields and parameters.
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    // By default, if Bean Validation is present on the classpath (for example, Hibernate Validator),
    // the LocalValidatorFactoryBean is registered as a global Validator for use with @Valid and @Validated on controller method arguments.
    @Override
    public Validator getValidator() {
        // ...
        return new OptionalValidatorFactoryBean();
    }

    // You can configure how Spring MVC determines the requested media types from the request
    // (for example, Accept header, URL path extension, query parameter, and others).
    //By default, only the Accept header is checked.
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    // You can customize options related to path matching and treatment of the URL.
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class));
    }

    private PathPatternParser patternParser() {
        // ...
        return new PathPatternParser();
    }
}
