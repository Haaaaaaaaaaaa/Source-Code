package cn.edu.ujn.ch17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ujn.ch17.dao.Customer;
import cn.edu.ujn.ch17.service.ICustomerService;

@Controller
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	@RequestMapping("/findById")
	public String findCustomerById(Integer id,Model model) {
		Customer customer = this.customerService.findCustomerById(id);
		model.addAttribute("customer",customer);
		return "customer";
	}

}
