package iti.jets.business.service;

import iti.jets.persistence.dao.OrderDao;
import iti.jets.persistence.model.Order;
import iti.jets.persistence.util.ConnectionInstance;

import java.util.List;

public class OrderService {

  public  static Order getOrder(ConnectionInstance connectionInstance,int id){
      OrderDao orderDao = new OrderDao(connectionInstance.getEntityManager());
     return orderDao.findById(id);
  }
}
