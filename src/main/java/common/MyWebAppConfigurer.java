package common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter  {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//    	MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//        MediaType media = new MediaType("text", "plain", Charset.forName("UTF-8"));
//        supportedMediaTypes.add(media);
//        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
//        return jsonConverter;
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    	converters.add(new ByteArrayHttpMessageConverter());
//        super.configureMessageConverters(converters);
//    }
    
}
