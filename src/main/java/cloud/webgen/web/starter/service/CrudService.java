package cloud.webgen.web.starter.service;


import cloud.webgen.web.starter.exeptions.HttpException;
import cloud.webgen.web.starter.models.BaseAuditModel;
import cloud.webgen.web.starter.repository.AuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class CrudService<T extends BaseAuditModel> implements ICrudService<T>{

    private final AuditRepository<T> repository;
    private ObjectMapper mapper;
    private final Class<T> tClass;

    public CrudService(AuditRepository<T> repository, Class<T> tClass) {
        this.repository = repository;
        this.mapper = new ObjectMapper();
        this.tClass = tClass;
    }


    @Override
    public List<T> getAll() {
        return (List<T>) this.repository.findAll();
    }

    @Override
    public T getOne(String id) throws HttpException {
        return findElement(id);
    }

    @Override
    public T save(Object element) throws HttpException {
        T data = parseToClass(element);
        return this.repository.save(data);
    }

    @Override
    public T update(Object element) throws HttpException {
        T data = parseToClass(element);

        this.findElement(data.getId());

        this.repository.save(data);

        return data;
    }

    @Override
    public T delete(String id) throws HttpException {
        T foundElement = this.findElement(id);

        this.repository.deleteById(id);

        return foundElement;
    }

    private T findElement(String id) throws HttpException{
        if( id == null) throw new HttpException("No id provided", HttpStatus.BAD_REQUEST);
        Optional<T> foundElement = this.repository.findById(id);

        if(foundElement.isEmpty()){
            throw new HttpException("Not found", HttpStatus.NOT_FOUND);
        }

        return foundElement.get();
    }

    private T parseToClass(Object data) throws HttpException {
        try {
            return this.mapper.convertValue(data, tClass);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new HttpException("Invalid data", HttpStatus.BAD_REQUEST);
        }

    }

}
