package cloud.webgen.web.starter.service;



import cloud.webgen.web.starter.exeptions.HttpException;
import cloud.webgen.web.starter.models.BaseAuditModel;

import java.util.List;

/**
 * Interfaz base para servicios CRUD (Create, Read, Update, Delete) que trabajan con modelos que extienden AuditModel.
 *
 * @param <T> Tipo del modelo que extiende AuditModel.
 * @author Camilo Muñoz
 * @see HttpException
 * @since 1.3-SNAPSHOT
 */
public interface ICrudService<T extends BaseAuditModel> {

    /**
     * Obtiene todos los elementos del servicio.
     *
     * @return Lista de elementos.
     */
    List<T> getAll();

    /**
     * Obtiene un elemento por su identificador único.
     *
     * @param id Identificador único del elemento.
     * @return Elemento encontrado.
     * @throws HttpException Excepción lanzada si no se encuentra el elemento.
     */
    T getOne(String id) throws HttpException;

    /**
     * Guarda un nuevo elemento en el servicio.
     *
     * @param element Elemento a ser guardado.
     * @return Elemento guardado.
     */
    T save(Object element) throws HttpException;

    /**
     * Actualiza un elemento existente en el servicio.
     *
     * @param element Elemento con las actualizaciones.
     * @return Elemento actualizado.
     * @throws HttpException Excepción lanzada si no se encuentra el elemento para actualizar.
     */
    T update(Object element) throws HttpException;

    /**
     * Elimina un elemento del servicio por su identificador único.
     *
     * @param id Identificador único del elemento a ser eliminado.
     * @return Elemento eliminado.
     * @throws HttpException Excepción lanzada si no se encuentra el elemento para eliminar.
     */
    T delete(String id) throws HttpException;
}
