package app.services.api;

import app.models.dtos.binding.CustomerDto;
import app.models.dtos.views.CustomerBirthDateYoungDriver;
import app.models.dtos.views.CustomerTotalSales;
import app.models.entities.Customer;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    void saveAll(List<CustomerDto> customers) throws ParseException;

    List<Customer> getAllCustomers();

    List<CustomerBirthDateYoungDriver> getAllByBirthDateAndYoungDriver();

    List<CustomerTotalSales> getAllCustomerWithSales();
}
