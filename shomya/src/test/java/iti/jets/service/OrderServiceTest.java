//package iti.jets.service;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderServiceTest {
////    private OrderDAO orderDAO;
////    private OrderService orderService;
////
////    @Before
////    public void setUp() {
////        orderDAO = mock(OrderDAO.class);
////        orderService = new OrderService();
////        orderService.setOrderDAO(orderDAO);
////    }
////
////    @Test
////    public void testGetAllOrders() {
////        List<Order> orders = new ArrayList<>();
////        when(orderDAO.findAll()).thenReturn(orders);
////        assertEquals(orders, orderService.getAllOrders());
////    }
////
////    @Test
////    public void testGetOrderById() {
////        Order order = new Order();
////        when(orderDAO.findById(1)).thenReturn(order);
////        assertEquals(order, orderService.getOrderById(1));
////    }
////
////    @Test
////    public void testAddOrder() {
////        Order order = new Order();
////        orderService.addOrder(order);
////        verify(orderDAO, times(1)).save(order);
////    }
////
////    @Test
////    public void testDeleteOrder() {
////        Order order = new Order();
////        orderService.deleteOrder(order);
////        verify(orderDAO, times(1)).delete(order);
////    }
////
////    @Test
////    public void testDeleteOrderById() {
////        orderService.deleteOrderById(1);
////        verify(orderDAO, times(1)).deleteById(1);
////    }
//}