package com.infosys.project.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.project.order.repository.OrderRepository;
import com.infosys.project.order.repository.ProductsOrderedRepo;
import com.infosys.project.order.dto.CombinedDTO;
import com.infosys.project.order.dto.OrderDTO;
import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.entity.OrderDetails;
import com.infosys.project.order.entity.ProductsOrdered;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductsOrderedRepo productRepo;
	public List<OrderDTO> getOrderDetails() {

		List<OrderDetails> orders = orderRepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for (OrderDetails order:orders) {
			OrderDTO orderDTO = OrderDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		}
		logger.info("Order details : {}", orderDTOs);
		return orderDTOs;
	}
	public CombinedDTO getSpecificOrderDetails(int orderid) {
		CombinedDTO newDTO=null;
		Optional<OrderDetails> optOrder=orderRepo.findById(orderid);
		if(optOrder.isPresent()) {
			OrderDetails orderDetails=optOrder.get();
			newDTO=CombinedDTO.valueOf(orderDetails);
			List<ProductsOrdered> prodorders=productRepo.findByorderid(orderid);
		List<ProductsOrderedDTO> prodList=new ArrayList<>();
		for(ProductsOrdered p:prodorders) {
			ProductsOrderedDTO pdto=ProductsOrderedDTO.valueOf(p);
			prodList.add(pdto);
		}
		newDTO.setProductsOrdered(prodList);
		}
		return newDTO;
	}
//	public OrderDTO getSpecificOrder(int orderid) {
//		logger.info("Order details : {}", orderid);
//		OrderDTO orderDTO=null;
//		Optional<OrderDetails> optOrder=orderRepo.findById(orderid);
//		if(optOrder.isPresent()) {
//			OrderDetails orderDetails=optOrder.get();
//	        orderDTO=OrderDTO.valueOf(orderDetails);
//		}
//		return orderDTO;
//	}
public void remove(int prodid) {
	
}
}
