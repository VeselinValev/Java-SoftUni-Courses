package app.runners;

import app.constants.InputFilePaths;
import app.constants.OutputFilePaths;
import app.io.JSONparser;
import app.models.dtos.binding.CarDto;
import app.models.dtos.binding.CustomerDto;
import app.models.dtos.binding.PartDto;
import app.models.dtos.binding.SupplierDto;
import app.models.dtos.views.*;
import app.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Component
public class Terminal implements CommandLineRunner {
    private JSONparser parser;
    private SupplierService supplierService;
    private PartService partService;
    private CarService carService;
    private CustomerService customerService;
    private SaleService saleService;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.parser = new JSONparser();
    }

    @Override
    public void run(String... args) throws Exception {
        //seedDatabase();

        //Problem 1
        orderedCustomers();

        //Problem 2
        carsFromMakeToyota();

        //Problem 3
        localSuppliers();

        //Problem 4
        carsAndParts();

        //Problem 5
        customersTotalSales();

        //Problem 6
        salesDiscounts();

    }

    private void seedDatabase() throws FileNotFoundException, ParseException {
        SupplierDto[] supplierDtos = this.parser.fromJSONtoObject(SupplierDto[].class, InputFilePaths.SUPPLIERS_FILE_PATH);
        this.supplierService.saveAll(supplierDtos);

        PartDto[] partDtos = this.parser.fromJSONtoObject(PartDto[].class, InputFilePaths.PARTS_FILE_PATH);
        this.partService.saveAll(partDtos);

        CarDto[] carDtos = this.parser.fromJSONtoObject(CarDto[].class, InputFilePaths.CARS_FILE_PATH);
        this.carService.saveAll(carDtos);

        CustomerDto[] customerDtos = this.parser.fromJSONtoObject(CustomerDto[].class, InputFilePaths.CUSTOMERS_FILE_PATH);
        this.customerService.saveAll(customerDtos);

        this.saleService.seedSales();
    }

    private void orderedCustomers() throws IOException {
        List<CustomerBirthDateYoungDriver> customersDto = this.customerService.getAllByBirthDateAndYoungDriver();
        this.parser.fromObjectToJSON(customersDto, OutputFilePaths.ORDERED_CUSTOMERS);
    }

    private void carsFromMakeToyota() throws IOException {
        List<CarToyota> toyotas = this.carService.getAllToyotas();
        this.parser.fromObjectToJSON(toyotas, OutputFilePaths.TOYOTA_CARS);
    }

    private void localSuppliers() throws IOException {
        List<LocalSupplier> localSuppliers = this.supplierService.getLocalSuppliers();
        this.parser.fromObjectToJSON(localSuppliers, OutputFilePaths.LOCAL_SUPPLIERS);
    }

    private void carsAndParts() throws IOException {
        List<CarView> carAndParts = this.carService.getAllCarsAndParts();
        this.parser.fromObjectToJSON(carAndParts, OutputFilePaths.CARS_AND_PARTS);
    }

    private void customersTotalSales() throws IOException {
        List<CustomerTotalSales> customerTotalSales = this.customerService.getAllCustomerWithSales();
        this.parser.fromObjectToJSON(customerTotalSales, OutputFilePaths.CUSTOMERS_TOTAL_SALES);
    }

    private void salesDiscounts() throws IOException {
        List<SaleWIthDiscount> sales = this.saleService.getAllSalesWithDiscount();
        this.parser.fromObjectToJSON(sales, OutputFilePaths.SALES_DISCOUNTS);
    }

}
