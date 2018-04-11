package RehabStar.Project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dmter on 11/9/2017.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index2");
        registry.addViewController("/").setViewName("index2");
        registry.addViewController("/following").setViewName("following");
        registry.addViewController("/signup").setViewName("signup");
    }
}
