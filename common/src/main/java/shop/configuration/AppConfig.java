    package shop.configuration;
    
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.EnableAspectJAutoProxy;

    @Configuration
    @ComponentScan("shop")
    @EnableAspectJAutoProxy
    public class AppConfig {
    }
