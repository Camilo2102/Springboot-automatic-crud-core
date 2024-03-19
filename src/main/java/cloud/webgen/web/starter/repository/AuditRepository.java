package cloud.webgen.web.starter.repository;


import cloud.webgen.web.starter.models.BaseAuditModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuditRepository<T extends BaseAuditModel> extends CrudRepository<T, String> {

}
