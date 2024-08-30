package iti.jets.dao;
import iti.jets.model.Order;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class OrderDAOTest {
    @Test
    public void testSearchByUserId() {
        OrderDao orderDAO = new OrderDao();
        int userId = 1; // Provide a valid user ID for testing
        List<Order> orders = orderDAO.searchByUserId(userId);
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetAllOrdersWithinTimestampRange() {
        OrderDao orderDAO = new OrderDao();
        Timestamp startTime = Timestamp.valueOf("2023-12-24 00:00:00");
        Timestamp endTime = Timestamp.valueOf("2024-12-31 23:59:59");
        List<Order> orders = orderDAO.getAllOrdersWithinTimestampRange(startTime, endTime);
        assertNotNull(orders);
        //assertEquals(8, orders.size());
    }
}
