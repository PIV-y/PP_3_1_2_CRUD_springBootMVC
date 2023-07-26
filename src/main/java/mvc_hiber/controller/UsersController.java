package mvc_hiber.controller;

import mvc_hiber.model.User;
import mvc_hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

// GET
    @GetMapping()
    public String printStart (Model model) {
        model.addAttribute("messages", "HEllO");
        return "start";
    }
    @GetMapping("/user_list")
    public String printUserList (Model model) {
        model.addAttribute("user", userService.getAllUsers());
        System.out.println(model.toString());
        return "users";
    }

// POST
    @GetMapping("/user_info")
    public String addNewUserInfo (Model model) {
        model.addAttribute("user", new User());
        System.out.println("форма открылась");
    return "user-info";
    }
    @PostMapping("/save_user")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        System.out.println("user added!!! "
                + user.getName() + user.toString());
        return "redirect:/user_list";
    }

// GET USER FOR CHANGES
    @RequestMapping("/{id}/update")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        System.out.println(model.toString());
        return "edit";
    }

// DELETE
    @RequestMapping("/clean_table")
    public String deleteAllUsers () {
        userService.dropData();
        return  "redirect:/user_list";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUserByID (@RequestParam("id") long id) {
        userService.removeUserById(id);
        return "redirect:/user_list";
    }

// PATCH
    @PatchMapping ("/change")
    public String update (@ModelAttribute("user") User user) {
        System.out.println("Обновленный юзер пришел: " + user.toString());
        userService.changeByID(user);
        System.out.println("юзер ушел в БД");
        return "redirect:/user_list";
    }
}