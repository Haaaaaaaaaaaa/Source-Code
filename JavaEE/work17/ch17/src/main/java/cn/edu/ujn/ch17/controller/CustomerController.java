package cn.edu.ujn.ch17.controller;

import java.util.List;

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
	public String findCustomerById(Integer id, Model model) {
		Customer customer = this.customerService.findCustomerById(id);
		model.addAttribute("customer", customer);
		return "customer";
	}

	@RequestMapping("/add")
	public String add() {
		return "addCustomer";
	}

	@RequestMapping("/addCustomer")
	public String addCustomer(Customer customer, Model model) {
		int customer2 = this.customerService.addCustomer(customer);
		model.addAttribute("customer", customer);
		return "customer";
	}

	@RequestMapping("/findAll")
	public String findAllCustomer(Customer customer, Model model) {
		List<Customer> customer3 = this.customerService.findAllCustomer();
		model.addAttribute("list", customer3);
		return "listCustomer";
	}
}
