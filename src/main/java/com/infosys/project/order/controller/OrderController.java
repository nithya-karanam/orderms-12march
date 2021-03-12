package com.infosys.project.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.project.order.dto.CartDTO;
import com.infosys.project.order.dto.CombinedDTO;
import com.infosys.project.order.dto.OrderDTO;
import com.infosys.project.order.service.OrderService;

@RestController
@CrossOrigin
public class OrderController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${cart.uri}")
	String cartUri;


	@Autowired
	OrderService orderService;

	// Fetches order details of a specific order
	@GetMapping(value = "/orders/{orderid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CombinedDTO getspecificOrderDetails(@PathVariable int orderid) {
		logger.info("Orderdetails request for user {}", orderid);

		return orderService.getSpecificOrderDetails(orderid);
	}
	@GetMapping(value = "/orders",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getAllOrderDetails() {
		logger.info("Orderdetails");

		return orderService.getOrderDetails();
	}
@DeleteMapping(value="/api/cart/remove",produces = MediaType.APPLICATION_JSON_VALUE)
	public void remove(@RequestBody CartDTO cartDto) {
logger.info("delete request from cartDTO:{}",cartDto);
String url="http://localhost:8300/api/cart/remove";
RestTemplate restTemp=new RestTemplate();
	restTemp.delete(url,cartDto);
}

	@PostMapping(value="/api/cart/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public void addCart(@RequestBody CartDTO cartDTO) {
		logger.info("adding product to cart :{}",cartDTO);
		String url="http:locakhost:8300/api/cart/add";
		RestTemplate restTemp=new RestTemplate();
		restTemp.postForObject(url, cartDTO, CartDTO.class);
	}
}
