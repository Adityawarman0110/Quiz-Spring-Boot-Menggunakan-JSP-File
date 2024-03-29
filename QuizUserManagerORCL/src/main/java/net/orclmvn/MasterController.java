package net.orclmvn;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;






@Controller
public class MasterController {
	
	
	@Autowired
	private UserService userService;
	

	@RequestMapping("/")
	public String usermain(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	
	@RequestMapping("/user")
	public String user(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}
	
	@RequestMapping(value = "userform", method = RequestMethod.GET)
	public String userform(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "userform";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String SaveUser(@Valid User user, BindingResult result, 
			ModelMap model, RedirectAttributes redirectattributes) {
		if (result.hasErrors()) {
			return "userform";
		}
		userService.save(user);
		return "useradded";
	}
	
	@RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public String showEditUserPage(@PathVariable(name = "id") int id, Model model) {
		User user = userService.get(id);
		model.addAttribute("user", user);
        return "userupdateform";
	}
	
	@RequestMapping(value = "/updateForm/update", method = RequestMethod.POST)
	public String UpdateUser(@Valid User user, BindingResult result, 
			ModelMap model, RedirectAttributes redirectattributes) {
		if (result.hasErrors()) {
			return "userupdateform";
		}
		userService.save(user);
		return "userupdated";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteForm(@PathVariable(name = "id") int id) {
		userService.delete(id);
		return "userdeleted";
	}
	

	@ModelAttribute("brands")
	public List<String> initializeSections() {

		List<String> brands = new ArrayList<String>();
		brands.add("PANASONIC");
		brands.add("SAMSUNG");
		brands.add("SANYO");
		return brands;
	}
}

