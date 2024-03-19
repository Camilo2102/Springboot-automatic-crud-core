package cloud.webgen.web.base.starter.config;


import cloud.webgen.web.base.starter.annotations.AutoControlable;
import cloud.webgen.web.base.starter.repository.AuditRepository;
import cloud.webgen.web.base.starter.service.CrudService;
import cloud.webgen.web.base.starter.utils.BeanLocator;
import cloud.webgen.web.base.starter.utils.BeanProjectFinder;
import cloud.webgen.web.base.starter.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * Configuración para la creación automática de servicios CRUD basados en anotaciones @AutoControlable.
 */
@Slf4j
@Configuration
public class AutoControllableFactoryConfig {

    private final BeanLocator beanLocator;
    private final BeanProjectFinder beanProjectFinder;

    /**
     * Constructor para AutoControllableFactoryConfig.
     *
     * @param beanLocator       El BeanLocator utilizado para localizar beans.
     * @param beanProjectFinder El BeanProjectFinder utilizado para encontrar repositorios.
     */
    @Autowired
    public AutoControllableFactoryConfig(BeanLocator beanLocator, BeanProjectFinder beanProjectFinder) {
        this.beanLocator = beanLocator;
        this.beanProjectFinder = beanProjectFinder;
    }

    /**
     * Encuentra e inicializa automáticamente los repositorios y servicios CRUD basados en anotaciones @AutoControlable.
     *
     * @throws Exception Si ocurre un error durante la creación de los beans.
     */
    @PostConstruct
    private void findAutoControllableRepositories() throws Exception {
        DefaultListableBeanFactory beanFactory = this.beanLocator.getListableBean();
        Map<String, Object> models = this.beanLocator.getBeansWithAnnotation(AutoControlable.class);

        for (Map.Entry<String, Object> entry : models.entrySet()) {
            String key = entry.getKey().split("Model")[0];
            Object bean = entry.getValue();
            Class<?> beanClass = bean.getClass();

            AutoControlable autoControlableAnnotation = beanClass.getAnnotation(AutoControlable.class);
            String repositoryName = autoControlableAnnotation.repositoryName();

            String actualRepositoryName = !Objects.equals(repositoryName, "")
                    ? repositoryName
                    : StringUtils.firstLowerLetter(key) + "Repository";

            AuditRepository<?> repository = this.beanProjectFinder.findRepository(actualRepositoryName);

            BeanDefinition crudClass = BeanDefinitionBuilder
                    .genericBeanDefinition(CrudService.class)
                    .addConstructorArgValue(repository)
                    .addConstructorArgValue(beanClass)
                    .getBeanDefinition();

            beanFactory.registerBeanDefinition(key + "Service", crudClass);
            log.info("Service created '{}' for repository '{}'", key + "Service", actualRepositoryName);
        }
    }
}
