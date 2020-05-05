package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import unicorn.dto.UserDTO;
import unicorn.service.api.UserService;
import unicorn.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Process requests related with users
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    /**
     * Displays form for user registration
     *
     * @param model - model for viewing
     * @return page with form for registration
     */

    @GetMapping(value = "/registration")
    public String createModel(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    /**
     * Saves user data
     *
     * @param userForm      - data for saving
     * @param bindingResult - object for keeping errors
     * @return page for authorization
     */

    @PostMapping(value = "/registration")
    public String registration(@Valid @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.create(userForm);
        return "redirect:/login";
    }

    /**
     * Displays form for authorization
     *
     * @param model  - model for viewing
     * @param error
     * @param logout
     * @return page for authorization
     */

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Email or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    /**
     * logs out for user
     *
     * @param request  - object for providing request information for HTTP servlets
     * @param response - object for providing HTTP-specific functionality in sending a response
     * @return url for redirecting to authorization page
     */

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    /**
     * Displays 403 error
     *
     * @return page with 403 error
     */

    @GetMapping(value = "/403")
    public String renderErrorPage() {
        return "error/403";
    }
}
