package cloud.webgen.web.starter.utils;


import cloud.webgen.web.starter.exeptions.HttpException;
import cloud.webgen.web.starter.repository.AuditRepository;
import cloud.webgen.web.starter.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BeanProjectFinder {

    private final BeanLocator beanLocator;

    @Autowired
    public BeanProjectFinder(BeanLocator beanLocator) {
        this.beanLocator = beanLocator;
    }

    public CrudService<?> findCrudServiceBeanByName(String serviceName) throws HttpException {
        try {
            return this.beanLocator.getBeanByString(serviceName + "Service", CrudService.class);
        } catch (Exception e) {
            throw new HttpException("Not found", HttpStatus.NOT_FOUND);
        }
    }

    public AuditRepository<?> findRepository(String repositoryName) throws Exception {
        try {
            return this.beanLocator.getBeanByString(repositoryName, AuditRepository.class);
        } catch (Exception e) {
            throw new Exception("Not found");
        }
    }
}
