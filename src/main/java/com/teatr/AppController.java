package com.teatr;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Controller
public class AppController implements WebMvcConfigurer {
    @Autowired
    private AdresDAO adresDAO;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_spectator").setViewName("spectator/main_spectator");
    }

    @Controller
    public class DashboardController {
        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request) {
            if(request.isUserInRole("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if(request.isUserInRole("SPECTATOR")) {
                return "redirect:/main_spectator";
            }
            else {
                return "redirect:/index";
            }
        }
    }
    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        List<Adres> adresList = adresDAO.list();
        model.addAttribute("adresList", adresList);
        return "admin/main_admin";
    }
    @RequestMapping(value={"/main_spectator"})
    public String showUserPage(Model model) {
        return "spectator/main_spectator";
    }
}