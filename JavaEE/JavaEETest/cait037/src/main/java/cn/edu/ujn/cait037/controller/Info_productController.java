package cn.edu.ujn.cait037.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ujn.cait037.dao.Info_product;
import cn.edu.ujn.cait037.service.IInfo_productService;


@Controller
public class Info_productController {
	@Autowired
	private IInfo_productService info_productService;
	
	@RequestMapping("/findById")
	public String findCustomerById(Integer id, Model model) {
		Info_product product = this.info_productService.findProductById(id);
		model.addAttribute("product", product);
		return "product";
	}

	@RequestMapping("/add")
	public String add() {
		return "addProduct";
	}

	@RequestMapping("/addProduct")
	public String addCustomer(Info_product info_product, Model model) {
		int product2 = this.info_productService.addProduct(info_product);
		model.addAttribute("product", info_product);
		return "product";
	}

	@RequestMapping("/findAll")
	public String findAllCustomer(Info_product customer, Model model) {
		List<Info_product> product3 = this.info_productService.findAllProduct();
		model.addAttribute("list", product3);
		return "listProduct";
	}
}
