package com.teatr;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Pobranie statusu błędu
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            // Logowanie kodu statusu błędu
            logger.error("Wykryto błąd o statusie: {}", statusCode);

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                logger.error("Błąd 403: Forbidden - Użytkownik nie ma dostępu do tej strony.");
                return "errors/403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                logger.error("Błąd 404: Not Found - Strona nie została znaleziona.");
                return "errors/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                logger.error("Błąd 500: Internal Server Error - Wystąpił problem na serwerze.");
                return "errors/500";
            } else if (statusCode == HttpStatus.GATEWAY_TIMEOUT.value()) {
                logger.error("Błąd 504: Gateway Timeout - Przekroczenie limitu czasu połączenia.");
                return "errors/504";
            } else {
                logger.error("Inny błąd o kodzie: {}", statusCode);
                return "errors/other";
            }
        }
        logger.error("Nieznany błąd - status nie został określony.");
        return "errors/other";
    }
}
