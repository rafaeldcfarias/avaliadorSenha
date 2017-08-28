package br.com.db1.avaliadorSenha;

import org.omg.CORBA.INTERNAL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@SpringBootApplication
public class AvaliadorSenhaApplication extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        return internalResourceViewResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(AvaliadorSenhaApplication.class, args);
    }
}
