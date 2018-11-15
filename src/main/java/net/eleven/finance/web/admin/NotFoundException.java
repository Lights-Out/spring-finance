package net.eleven.finance.web.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by eleven on 28.10.2018.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object not found")
public class NotFoundException extends RuntimeException {
}
