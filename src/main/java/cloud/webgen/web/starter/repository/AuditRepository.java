package cloud.webgen.web.base.starter.repository;

import cloud.webgen.web.base.starter.models.BaseAuditModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuditRepository<T extends BaseAuditModel> extends CrudRepository<T, String> {

}
