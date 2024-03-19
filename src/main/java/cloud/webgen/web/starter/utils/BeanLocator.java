package cloud.webgen.web.starter.utils;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class BeanLocator {
    private final ApplicationContext applicationContext;

    public BeanLocator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> T getBeanByString(String beanName, Class<T> targetType) {
        return applicationContext.getBean(beanName, targetType);
    }

    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotation){
        return this.applicationContext.getBeansWithAnnotation(annotation);
    }

    public DefaultListableBeanFactory getListableBean() {
        return (DefaultListableBeanFactory) this.applicationContext.getAutowireCapableBeanFactory();
    }



}
