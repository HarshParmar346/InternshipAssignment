package assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import assignment.Model.Customer;
import assignment.Model.CustomerList;
import assignment.Model.LoginBody;
import assignment.services.CustomerService;
@Controller
public class AssignmentController {

	@Autowired
	CustomerService customerService;

	String accessCode = "";
	
	@GetMapping(value={"/","/home"})
    public String welcome() {
        return "home";
    }  
	
	@PostMapping(value = "/login")
	   public String loginUser(@ModelAttribute LoginBody user ) {
		accessCode = customerService.login(user);
		System.out.println(accessCode);
		
		return "redirect:/dashboard";
		
	   }
	
	@GetMapping(value="/newCustomer")
	public String newCustomer() {
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		
		return "newCustomer";
	}
	
	@PostMapping(value="/newCustomer")
	public String createCustomer(@ModelAttribute() Customer customer) {
		
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		
		customerService.createUser(accessCode, customer);
		
		return "redirect:/dashboard";
	}
	
	
	@GetMapping(value="/dashboard")
    public String dashboarString(Model model) {
		
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		List<CustomerList> customers = customerService.getList(accessCode);
		model.addAttribute("list", customers);
		
		
        return "dashboard";
    }  
	
	@GetMapping(value="/update/{uuid}")
	public String updateView(@PathVariable String uuid, @ModelAttribute() Customer customerList, Model model) {
		
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		System.out.println(customerList);
		model.addAttribute("customer", customerList);
		model.addAttribute("uuid", uuid);
		return "updateCustomer";
		
	}
	
	@PostMapping(value="/update/{uuid}")
	public String update(@PathVariable String uuid, @ModelAttribute Customer customerList) {
		
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		customerService.update(accessCode, customerList, uuid);
		return "redirect:/dashboard";
		
	}
	
	@PostMapping(value="/delete/{uuid}")
	public String delete(@PathVariable String uuid) {
		
		if(accessCode.equals("")) {
			return "redirect:/home";
		}
		customerService.delete(accessCode,uuid);
		return "redirect:/dashboard";
	}
	
}
