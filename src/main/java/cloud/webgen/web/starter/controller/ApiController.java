package cloud.webgen.web.starter.controller;

import cloud.webgen.web.starter.exeptions.HttpException;
import cloud.webgen.web.starter.service.CrudService;
import cloud.webgen.web.starter.utils.BeanProjectFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
/**
 *
 */
public class ApiController {
    private final BeanProjectFinder beanServiceFinder;

    /**
     * Constructor del controlador ApiController.
     *
     * @param beanLocator El localizador de beans utilizado para encontrar servicios CRUD.
     */
    @Autowired
    public ApiController(BeanProjectFinder beanLocator) {
        this.beanServiceFinder = beanLocator;
    }

    /**
     * Maneja las solicitudes GET para obtener todos los datos de un tipo específico.
     *
     * @param variable La variable de la ruta que identifica el tipo de datos.
     * @return Una respuesta HTTP con la lista de todos los datos y el estado correspondiente.
     * @throws HttpException Si ocurre un error al procesar la solicitud.
     */
    @RequestMapping(value = "/{variable}", method = RequestMethod.GET)
    public ResponseEntity<?> getMethod(@PathVariable("variable") String variable) throws HttpException {
        CrudService<?> crudService = this.beanServiceFinder.findCrudServiceBeanByName(variable);
        List<?> allData = crudService.getAll();
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    /**
     * Maneja las solicitudes GET para obtener un dato específico por su ID.
     *
     * @param variable La variable de la ruta que identifica el tipo de datos.
     * @param id El ID del dato a recuperar.
     * @return Una respuesta HTTP con el dato recuperado y el estado correspondiente.
     * @throws HttpException Si ocurre un error al procesar la solicitud.
     */
    @RequestMapping(value = "/{variable}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMethod(@PathVariable("variable") String variable, @PathVariable("id") String id) throws HttpException {
        CrudService<?> crudService = this.beanServiceFinder.findCrudServiceBeanByName(variable);
        Object foundData = crudService.getOne(id);
        return new ResponseEntity<>(foundData, HttpStatus.OK);
    }

    /**
     * Maneja las solicitudes POST para crear nuevos datos.
     *
     * @param variable La variable de la ruta que identifica el tipo de datos.
     * @param body El cuerpo de la solicitud que contiene los datos a guardar.
     * @return Una respuesta HTTP con el objeto guardado y el estado correspondiente.
     * @throws HttpException Si ocurre un error al procesar la solicitud.
     */
    @RequestMapping(value = "/{variable}", method = RequestMethod.POST)
    public ResponseEntity<?> postMethod(@PathVariable("variable") String variable, @RequestBody Object body) throws HttpException {
        CrudService<?> crudService = this.beanServiceFinder.findCrudServiceBeanByName(variable);
        Object savedData = crudService.save(body);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    /**
     * Maneja las solicitudes PUT para actualizar datos existentes.
     *
     * @param variable La variable de la ruta que identifica el tipo de datos.
     * @param body El cuerpo de la solicitud que contiene los datos actualizados.
     * @return Una respuesta HTTP con el objeto actualizado y el estado correspondiente.
     * @throws HttpException Si ocurre un error al procesar la solicitud.
     */
    @RequestMapping(value = "/{variable}", method = RequestMethod.PUT)
    public ResponseEntity<?> putMethod(@PathVariable("variable") String variable, @RequestBody Object body) throws HttpException {
        CrudService<?> crudService = this.beanServiceFinder.findCrudServiceBeanByName(variable);
        Object updatedData = crudService.update(body);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    /**
     * Maneja las solicitudes DELETE para eliminar datos existentes.
     *
     * @param variable La variable de la ruta que identifica el tipo de datos.
     * @param id El ID del dato a eliminar.
     * @return Una respuesta HTTP con el objeto eliminado y el estado correspondiente.
     * @throws HttpException Si ocurre un error al procesar la solicitud.
     */
    @RequestMapping(value = "/{variable}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMethod(@PathVariable("variable") String variable, @PathVariable("id") String id) throws HttpException {
        CrudService<?> crudService = this.beanServiceFinder.findCrudServiceBeanByName(variable);
        Object deletedData = crudService.delete(id);
        return new ResponseEntity<>(deletedData, HttpStatus.NO_CONTENT);
    }

}
