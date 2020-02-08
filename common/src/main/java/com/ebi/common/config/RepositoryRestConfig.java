package com.ebi.common.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.regex.Pattern;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

  @Override
  public void configureConversionService(ConfigurableConversionService conversionService) {
    conversionService.addConverter(String.class, LocalDate.class, LocalDate::parse);
    conversionService.addConverter(String.class, LocalDateTime.class, LocalDateTime::parse);
  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    exposeIdForDomainObjects(config);
  }

  private void exposeIdForDomainObjects(RepositoryRestConfiguration config) {
    final ClassPathScanningCandidateComponentProvider provider =
        new ClassPathScanningCandidateComponentProvider(false);

    provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

    final Set<BeanDefinition> beans = provider.findCandidateComponents("com.ebi.*");

    beans.forEach(beanDefinition -> {
      Class<?> idExposedClasses;
      try {
        idExposedClasses = Class.forName(beanDefinition.getBeanClassName());
        config.exposeIdsFor(Class.forName(idExposedClasses.getName()));
      } catch (ClassNotFoundException ignored) {
      }
    });
  }

  @Bean
  public HateoasPageableHandlerMethodArgumentResolver customResolver(
      HateoasPageableHandlerMethodArgumentResolver pageableResolver
  ) {
    pageableResolver.setOneIndexedParameters(true);

    return pageableResolver;
  }
}
