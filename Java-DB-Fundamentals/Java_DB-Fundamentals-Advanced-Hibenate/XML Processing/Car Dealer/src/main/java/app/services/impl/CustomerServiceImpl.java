package app.services.impl;

import app.models.dtos.binding.CustomerDto;
import app.models.dtos.views.CustomerBirthDateYoungDriver;
import app.models.dtos.views.CustomerTotalSales;
import app.models.entities.Customer;
import app.models.entities.Part;
import app.models.entities.Sale;
import app.repositories.CustomerRepository;
import app.services.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAll(List<CustomerDto> customers){
        for (CustomerDto customerDto : customers) {
            Customer customer = this.modelMapper.map(customerDto, Customer.class);

            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<CustomerBirthDateYoungDriver> getAllByBirthDateAndYoungDriver() {
        List<Customer> customers = this.customerRepository.findAllOrOrderByBirthDateAndIsYoungDriver();
        return this.customerRepository.findAllOrOrderByBirthDateAndIsYoungDriver()
                .stream()
                .map(x -> {
                    CustomerBirthDateYoungDriver customerDto = this.modelMapper.map(x, CustomerBirthDateYoungDriver.class);
                    customerDto.setSales(new ArrayList<>());
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerTotalSales> getAllCustomerWithSales() {
        List<Customer> customers = this.customerRepository.getAllCustomersWithSales();
        List<CustomerTotalSales> customersDto = new ArrayList<>();
        for (Customer customer: customers){
            CustomerTotalSales customerTotalSales = new CustomerTotalSales();
            customerTotalSales.setFullName(customer.getName());
            customerTotalSales.setBoughtCars(customer.getSales().size());
            BigDecimal totalMoneySpent = BigDecimal.ZERO;
            for (Sale sale: customer.getSales()){
                BigDecimal carPrice = BigDecimal.ZERO;
                for (Part part: sale.getCar().getParts()){
                    carPrice = carPrice.add(part.getPrice());
                }
                carPrice = carPrice.multiply(BigDecimal.valueOf(1).subtract(sale.getDiscount()));
                totalMoneySpent = totalMoneySpent.add(carPrice);
            }
            customerTotalSales.setSpentMoney(totalMoneySpent);
            customersDto.add(customerTotalSales);
        }
        return customersDto.stream().sorted((x,y) -> {
            int compare = y.getSpentMoney().compareTo(x.getSpentMoney());
            if (compare == 0){
                compare = Integer.compare(y.getBoughtCars(), x.getBoughtCars());
            }
            return compare;
        }).collect(Collectors.toList());
    }
}
