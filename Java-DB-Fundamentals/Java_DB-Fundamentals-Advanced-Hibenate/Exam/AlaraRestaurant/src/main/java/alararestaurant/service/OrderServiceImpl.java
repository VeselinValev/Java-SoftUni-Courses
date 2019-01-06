package alararestaurant.service;

import alararestaurant.domain.dtos.ItemXmlDto;
import alararestaurant.domain.dtos.OrderXmlDto;
import alararestaurant.domain.dtos.OrderXmlRootDto;
import alararestaurant.domain.entities.*;
import alararestaurant.enums.OrderType;
import alararestaurant.repository.*;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final String FILE_PATH = "C:\\Users\\Ves\\Desktop\\AlaraRestaurant\\src\\main\\resources\\files\\orders.xml";

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;
    private EmployeeRepository employeeRepository;
    private ItemRepository itemRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, XmlParser xmlParser, EmployeeRepository employeeRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() != 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(this.FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException, ParseException {
        StringBuilder sb = new StringBuilder();
        OrderXmlRootDto orderXmlRootDto = this.xmlParser.parseXml(OrderXmlRootDto.class, this.FILE_PATH);

        for (OrderXmlDto orderXmlDto : orderXmlRootDto.getOrders()) {
            if (!this.validationUtil.isValid(orderXmlDto)) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Employee employee = this.employeeRepository.getByName(orderXmlDto.getEmployee());

            if (employee == null) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Order order = new Order();
            order.setCustomer(orderXmlDto.getCustomer());
            order.setOrderType(OrderType.valueOf(orderXmlDto.getType()));

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = df.parse(orderXmlDto.getDateTime());
            order.setDateTime(date);
            boolean itemCheck = false;
            List<OrderItem> orderItems = new ArrayList<>();
            for (ItemXmlDto itemXmlDto : orderXmlDto.getItems().getItems()) {
                if (!this.validationUtil.isValid(itemXmlDto)) {
                    sb.append("Invalid data format.").append(System.lineSeparator());
                    itemCheck = true;
                    break;
                }

                Item item = this.itemRepository.getByName(itemXmlDto.getName());

                if (item == null) {
                    sb.append("Invalid data format.").append(System.lineSeparator());
                    itemCheck = true;
                    break;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setOrder(order);
                orderItem.setQuantity(itemXmlDto.getQuantity());
                orderItems.add(orderItem);
                order.getOrderItems().add(orderItem);
            }

            if (itemCheck) {
                continue;
            }

            order.setEmployee(employee);

            order = this.orderRepository.saveAndFlush(order);
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(order);
            }
            this.orderItemRepository.saveAll(orderItems);
            sb.append(String.format("Order for %s on %s added%n", order.getCustomer(), orderXmlDto.getDateTime()));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder sb = new StringBuilder();
        List<Order> orders = this.orderRepository.getAllByEmployeePosition();
        for (Order order : orders) {
            Employee employee = order.getEmployee();
            sb.append(String.format("Name: %s%nOrders:%n", employee.getName()));
            sb.append(String.format("  Customer: %s%n  Items:%n", order.getCustomer()));
            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();
                sb.append(String.format("    Name: %s%n    Price: %s%n    Quantity: %s%n%n", item.getName(), item.getPrice(), orderItem.getQuantity()));
            }
        }
//        List<Employee> employees = this.employeeRepository.getAllByEmployeePosition();
//
//        for (Employee employee : employees) {
//            sb.append(String.format("Name: %s%nOrders:%n", employee.getName()));
//            employee.getOrders().sort(Comparator.comparing((Order x) -> x.getEmployee().getName()).thenComparingInt(BaseEntity::getId));
//            for (Order order : employee.getOrders()) {
//                sb.append(String.format("Customer: %s%nItems:%n", order.getCustomer()));
//                for (OrderItem orderItem : order.getOrderItems()) {
//                    Item item = orderItem.getItem();
//                    sb.append(String.format("Name: %s%nPrice: %s%nQuantity: %s%n%n", item.getName(), item.getPrice(), orderItem.getQuantity()));
//                }
//            }
//        }
        return sb.toString().trim();
    }
}
