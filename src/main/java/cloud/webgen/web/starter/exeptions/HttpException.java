package cloud.webgen.web.starter.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

/**
 * Clase de excepción personalizada para representar errores HTTP.
 *
 * @author Camilo Muñoz
 * @see HttpStatusCode
 * @see Throwable
 * @since 1.3-SNAPSHOT
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpException extends Throwable {
    private String message;
    private HttpStatusCode httpCode;
}
