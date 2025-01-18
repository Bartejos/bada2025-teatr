package com.teatr;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Configuration
@Controller
public class AppController implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);


    @Autowired
    private AdresDAO adresDAO;

    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("Dodawanie kontrolerów widoków...");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_spectator").setViewName("spectator/main_spectator");
        registry.addViewController("/performances").setViewName("spectator/performances_spectator");
        registry.addViewController("/our_theatre").setViewName("spectator/our_theatre_spectator");
        registry.addViewController("/my_account").setViewName("spectator/my_account_spectator");
    }

    @Controller
    public class DashboardController {
        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request) {
            logger.info("Po zalogowaniu sprawdzam role użytkownika...");
            if (request.isUserInRole("ADMIN")) {
                logger.info("Użytkownik ma rolę ADMIN, przekierowywanie do /main_admin");
                return "redirect:/main_admin";
            } else if (request.isUserInRole("SPECTATOR")) {
                logger.info("Użytkownik ma rolę SPECTATOR, przekierowywanie do /main_spectator");
                return "redirect:/main_spectator";
            } else {
                logger.info("Brak roli, przekierowywanie do /index");
                return "redirect:/index";
            }
        }
    }

    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        logger.info("Wyświetlanie strony admina...");
        List<Adres> adresList = adresDAO.list();
        model.addAttribute("adresList", adresList);
        return "admin/main_admin";
    }

    @RequestMapping(value={"/main_spectator"})
    public String showUserPage(Model model) {
        logger.info("Wyświetlanie strony widza...");
        return "spectator/main_spectator";
    }

    @RequestMapping(value = {"/index", "/"})
    public String showIndexPage(Model model, HttpServletRequest request) {
        boolean isLoggedIn = request.getUserPrincipal() != null;
        logger.info("Czy użytkownik jest zalogowany: {}", isLoggedIn);
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            model.addAttribute("username", request.getUserPrincipal().getName());
            logger.info("Zalogowany użytkownik: {}", request.getUserPrincipal().getName());
        }
        return "index";
    }
}