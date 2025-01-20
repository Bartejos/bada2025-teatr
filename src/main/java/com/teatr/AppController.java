package com.teatr;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Controller
public class AppController implements WebMvcConfigurer {
    private final AdresDAO adresDAO;
    private final TeatrDAO teatrDAO;
    private final SalaDAO salaDAO;
    private final PracownikDAO pracownikDAO;
    private final SpektaklDAO spektaklDAO;

    public AppController(AdresDAO adresDAO, TeatrDAO teatrDAO, SalaDAO salaDAO, PracownikDAO pracownikDAO, SpektaklDAO spektaklDAO) {
        this.adresDAO = adresDAO;
        this.teatrDAO = teatrDAO;
        this.salaDAO = salaDAO;
        this.pracownikDAO = pracownikDAO;
        this.spektaklDAO = spektaklDAO;
    }

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

    @GetMapping("/")
    public String showIndexPage(Model model) {
        List<Spektakl> spektaklList = spektaklDAO.list();
        List<Teatr> teatrList = teatrDAO.list();
        List<Sala> salaList = salaDAO.list();

        Map<BigInteger, Teatr> teatrMap = teatrList.stream()
                        .collect(Collectors.toMap(Teatr::getIdTeatru, teatr -> teatr));
        Map<BigInteger, Sala> salaMap = salaList.stream()
                        .collect(Collectors.toMap(Sala::getIdSali, sala -> sala));

        model.addAttribute("spektaklList", spektaklList);
        model.addAttribute("teatrList", teatrList);
        model.addAttribute("teatrMap", teatrMap);
        model.addAttribute("salaMap", salaMap);
        return "index";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView mav = new ModelAndView("errors/other");
        mav.addObject("message", "Nie można usunąć rekordu, ponieważ istnieją powiązane dane.");
        return mav;
    }

    // TABELA ADRES
    @RequestMapping("/main_admin/adres")
    public String showAdresPage(Model model) {
        List<Adres> adresList = adresDAO.list();
        model.addAttribute("adresList", adresList);
        return "admin/adres/adres";
    }
    @RequestMapping("/main_admin/adres/new")
    public String showNewAdresForm(Model model) {
        model.addAttribute("adres", new Adres());
        return "admin/adres/new_adres";
    }
    @RequestMapping(value = "/main_admin/adres/save", method = RequestMethod.POST)
    public String saveAdres(@ModelAttribute("adres") Adres adres) {
        adresDAO.save(adres);
        return "redirect:/main_admin/adres";
    }
    @RequestMapping("/main_admin/adres/edit/{id}")
    public ModelAndView showEditAdresForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/adres/edit_adres");
        Adres adres = adresDAO.get(id);
        mav.addObject("adres", adres);
        return mav;
    }
    @RequestMapping(value = "/main_admin/adres/update", method = RequestMethod.POST)
    public String updateAdres(@ModelAttribute("adres") Adres adres) {
        adresDAO.update(adres);
        return "redirect:/main_admin/adres";
    }
    @RequestMapping("/main_admin/adres/delete/{id}")
    public String deleteAdres(@PathVariable(name = "id") int id, Model model) {
        try {
            adresDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Nie można usunąć adresu, ponieważ jest on powiązany z innymi tabelami.");
            return "errors/other";
        }
        return "redirect:/main_admin/adres";
    }

    // TABELA TEATR
    @RequestMapping("/main_admin/teatr")
    public String showTeatrPage(Model model) {
        List<Teatr> teatrList = teatrDAO.list();
        model.addAttribute("teatrList", teatrList);
        return "admin/teatr/teatr";
    }
    @RequestMapping("/main_admin/teatr/new")
    public String showNewTeatrForm(Model model) {
        model.addAttribute("teatr", new Teatr());
        List<Adres> adresList = adresDAO.list();
        if (adresList == null || adresList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać teatru, ponieważ jest brak adresów do przypisania w bazie danych.");
            return "errors/other";
        }
        model.addAttribute("adresList", adresList);
        return "admin/teatr/new_teatr";
    }
    @RequestMapping(value = "/main_admin/teatr/save", method = RequestMethod.POST)
    public String saveTeatr(@ModelAttribute("teatr") Teatr teatr) {
        teatrDAO.save(teatr);
        return "redirect:/main_admin/teatr";
    }
    @RequestMapping("/main_admin/teatr/edit/{id}")
    public ModelAndView showEditTeatrForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/teatr/edit_teatr");
        Teatr teatr = teatrDAO.get(id);
        mav.addObject("teatr", teatr);
        mav.addObject("adresList", adresDAO.list());
        return mav;
    }
    @RequestMapping(value = "/main_admin/teatr/update", method = RequestMethod.POST)
    public String updateTeatr(@ModelAttribute("teatr") Teatr teatr) {
        teatrDAO.update(teatr);
        return "redirect:/main_admin/teatr";
    }
    @RequestMapping("/main_admin/teatr/delete/{id}")
    public String deleteTeatr(@PathVariable(name = "id") int id, Model model) {
        try {
            teatrDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Nie można usunąć teatru, ponieważ jest on powiązany z innymi tabelami.");
            return "errors/other";
        }
        return "redirect:/main_admin/teatr";
    }

    // TABELA SALA
    @RequestMapping("/main_admin/sala")
    public String showSalaPage(Model model) {
        List<Sala> salaList = salaDAO.list();
        model.addAttribute("salaList", salaList);
        return "admin/sala/sala";
    }
    @RequestMapping("/main_admin/sala/new")
    public String showNewSalaForm(Model model) {
        model.addAttribute("sala", new Sala());
        List<Teatr> teatrList = teatrDAO.list();
        if (teatrList == null || teatrList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać sali, ponieważ nie istnieje żaden teatr do przypisania w bazie danych.");
            return "errors/other";
        }
        model.addAttribute("teatrList", teatrList);
        return "admin/sala/new_sala";
    }
    @RequestMapping(value = "/main_admin/sala/save", method = RequestMethod.POST)
    public String saveSala(@ModelAttribute("sala") Sala sala) {
        salaDAO.save(sala);
        return "redirect:/main_admin/sala";
    }
    @RequestMapping("/main_admin/sala/edit/{id}")
    public ModelAndView showEditSalaForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/sala/edit_sala");
        Sala sala = salaDAO.get(id);
        mav.addObject("sala", sala);
        mav.addObject("teatrList", teatrDAO.list());
        return mav;
    }
    @RequestMapping(value = "/main_admin/sala/update", method = RequestMethod.POST)
    public String updateSala(@ModelAttribute("sala") Sala sala) {
        salaDAO.update(sala);
        return "redirect:/main_admin/sala";
    }
    @RequestMapping("/main_admin/sala/delete/{id}")
    public String deleteSala(@PathVariable(name = "id") int id, Model model) {
        try {
            salaDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Nie można usunąć sali, ponieważ jest ona powiązana z innymi tabelami.");
            return "errors/other";
        }
        return "redirect:/main_admin/sala";
    }

    // TABELA PRACOWNIK
    @RequestMapping("/main_admin/pracownik")
    public String showPracownikPage(Model model) {
        List<Pracownik> pracownikList = pracownikDAO.list();
        model.addAttribute("pracownikList", pracownikList);
        return "admin/pracownik/pracownik";
    }
    @RequestMapping("/main_admin/pracownik/new")
    public String showNewPracownikForm(Model model) {
        model.addAttribute("pracownik", new Pracownik());
        List<Teatr> teatrList = teatrDAO.list();
        if (teatrList == null || teatrList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać pracownika, ponieważ nie istnieje żaden teatr do przypisania w bazie danych.");
            return "errors/other";
        }
        List<Adres> adresList = adresDAO.list();
        if (adresList == null || adresList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać pracownika, ponieważ nie istnieje żaden adres do przypisania w bazie danych.");
            return "errors/other";
        }
        model.addAttribute("teatrList", teatrList);
        model.addAttribute("adresList", adresList);
        return "admin/pracownik/new_pracownik";
    }
    @RequestMapping(value = "/main_admin/pracownik/save", method = RequestMethod.POST)
    public String savePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
        pracownikDAO.save(pracownik);
        return "redirect:/main_admin/pracownik";
    }
    @RequestMapping("/main_admin/pracownik/edit/{id}")
    public ModelAndView showEditPracownikForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/pracownik/edit_pracownik");
        Pracownik pracownik = pracownikDAO.get(id);
        mav.addObject("pracownik", pracownik);
        mav.addObject("teatrList", teatrDAO.list());
        mav.addObject("adresList", adresDAO.list());
        return mav;
    }
    @RequestMapping(value = "/main_admin/pracownik/update", method = RequestMethod.POST)
    public String updatePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
        pracownikDAO.update(pracownik);
        return "redirect:/main_admin/pracownik";
    }
    @RequestMapping("/main_admin/pracownik/delete/{id}")
    public String deletePracownik(@PathVariable(name = "id") int id, Model model) {
        try {
            pracownikDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Nie można usunąć pracownika, ponieważ jest on powiązany z innymi tabelami.");
            return "errors/other";
        }
        return "redirect:/main_admin/pracownik";
    }

    // TABELA SPEKTAKL
    @RequestMapping("/main_admin/spektakl")
    public String showSpektaklPage(Model model) {
        List<Spektakl> spektaklList = spektaklDAO.list();
        model.addAttribute("spektaklList", spektaklList);
        return "admin/spektakl/spektakl";
    }
    @RequestMapping("/main_admin/spektakl/new")
    public String showNewSpektaklForm(Model model) {
        model.addAttribute("spektakl", new Spektakl());
        List<Teatr> teatrList = teatrDAO.list();
        if (teatrList == null || teatrList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać spektaklu, ponieważ nie istnieje żaden teatr do przypisania w bazie danych.");
            return "errors/other";
        }
        List<Sala> salaList = salaDAO.list();
        if (salaList == null || salaList.isEmpty()) {
            model.addAttribute("message", "Nie można dodać spektaklu, ponieważ nie istnieje żadna sala do przypisania w bazie danych.");
            return "errors/other";
        }
        model.addAttribute("teatrList", teatrList);
        model.addAttribute("salaList", salaList);
        return "admin/spektakl/new_spektakl";
    }
    @RequestMapping(value = "/main_admin/spektakl/save", method = RequestMethod.POST)
    public String saveSpektakl(@ModelAttribute("spektakl") Spektakl spektakl, Model model) {
        spektaklDAO.save(spektakl);
        return "redirect:/main_admin/spektakl";
    }
    @RequestMapping("/main_admin/spektakl/edit/{id}")
    public ModelAndView showEditSpektaklForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/spektakl/edit_spektakl");
        Spektakl spektakl = spektaklDAO.get(id);
        mav.addObject("spektakl", spektakl);
        mav.addObject("teatrList", teatrDAO.list());
        mav.addObject("salaList", salaDAO.list());
        return mav;
    }
    @RequestMapping(value = "/main_admin/spektakl/update", method = RequestMethod.POST)
    public String updateSpektakl(@ModelAttribute("spektakl") Spektakl spektakl, Model model) {
        spektaklDAO.update(spektakl);
        return "redirect:/main_admin/spektakl";
    }
    @RequestMapping("/main_admin/spektakl/delete/{id}")
    public String deleteSpektakl(@PathVariable(name = "id") int id, Model model) {
        try {
            spektaklDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Nie można usunąć spektaklu, ponieważ jest on powiązany z innymi tabelami.");
            return "errors/other";
        }
        return "redirect:/main_admin/spektakl";
    }

    @RequestMapping(value={"/main_spectator"})
    public String showUserPage(Model model) {
        return "spectator/main_spectator";
    }
}