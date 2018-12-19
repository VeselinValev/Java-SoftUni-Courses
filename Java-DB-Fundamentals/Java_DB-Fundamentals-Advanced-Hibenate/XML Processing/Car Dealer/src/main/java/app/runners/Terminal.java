package app.runners;

import app.constants.InputFilePaths;
import app.constants.OutputFilePaths;
import app.io.XMLParser;
import app.models.dtos.binding.*;
import app.models.dtos.views.*;
import app.models.dtos.views.wrappers.*;
import app.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.text.ParseException;
import java.util.List;


@Component
public class Terminal implements CommandLineRunner {
    private SupplierService supplierService;
    private PartService partService;
    private CarService carService;
    private CustomerService customerService;
    private SaleService saleService;
    private XMLParser parser;

    @Autowired
    public Terminal(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.parser = new XMLParser();
    }

    @Override
    public void run(String... args) throws Exception {
        //seedDatabase();

        //Problem 1
        //orderedCustomers();

        //Problem 2
        //carsFromMakeToyota();

        //Problem 3
        //localSuppliers();

        //Problem 4
        //carsAndParts();

        //Problem 5
        //customersTotalSales();

        //Problem 6
        //salesDiscounts();

    }

    private void seedDatabase() throws FileNotFoundException, ParseException, JAXBException {
        SupplierWrapperDto suppliers = this.parser.fromXMLToObject(SupplierWrapperDto.class, InputFilePaths.SUPPLIERS_FILE_PATH);
        this.supplierService.saveAll(suppliers.getSuppliers());

        PartWrapperDto partDtos = this.parser.fromXMLToObject(PartWrapperDto.class, InputFilePaths.PARTS_FILE_PATH);
        this.partService.saveAll(partDtos.getParts());

        CarWrapperDto carDtos = this.parser.fromXMLToObject(CarWrapperDto.class, InputFilePaths.CARS_FILE_PATH);
        this.carService.saveAll(carDtos.getCars());

        CustomerWrapperDto customerDtos = this.parser.fromXMLToObject(CustomerWrapperDto.class, InputFilePaths.CUSTOMERS_FILE_PATH);
        this.customerService.saveAll(customerDtos.getCustomers());

        this.saleService.seedSales();
    }


    private void orderedCustomers() throws JAXBException {
        List<CustomerBirthDateYoungDriver> customers = this.customerService.getAllByBirthDateAndYoungDriver();
        CustomerBirthDateWrapper customerWrapper = new CustomerBirthDateWrapper();
        customerWrapper.setCustomers(customers);
        this.parser.fromObjectToXML(CustomerBirthDateWrapper.class, customerWrapper, OutputFilePaths.ORDERED_CUSTOMERS);
    }

    private void carsFromMakeToyota() throws JAXBException {
        List<CarToyota> toyotas = this.carService.getAllToyotas();
        CarToyotaWrapper toyotaWrapper = new CarToyotaWrapper();
        toyotaWrapper.setCars(toyotas);
        this.parser.fromObjectToXML(CarToyotaWrapper.class, toyotaWrapper, OutputFilePaths.TOYOTA_CARS);
    }

    private void localSuppliers() throws JAXBException {
        List<LocalSupplier> suppliers = this.supplierService.getLocalSuppliers();
        LocalSupplierWrapper supplierWrapper = new LocalSupplierWrapper();
        supplierWrapper.setSuppliers(suppliers);
        this.parser.fromObjectToXML(LocalSupplierWrapper.class, supplierWrapper, OutputFilePaths.LOCAL_SUPPLIERS);
    }

    private void carsAndParts() throws JAXBException {
        List<CarAndParts> cars = this.carService.getAllCarsAndParts();
        CarAndPartsWrapper carAndPartsWrapper = new CarAndPartsWrapper();
        carAndPartsWrapper.setCars(cars);
        this.parser.fromObjectToXML(CarAndPartsWrapper.class, carAndPartsWrapper, OutputFilePaths.CARS_AND_PARTS);
    }

    private void customersTotalSales() throws JAXBException {
        List<CustomerTotalSales> customers = this.customerService.getAllCustomerWithSales();
        CustomerTotalSalesWrapper customerWrapper = new CustomerTotalSalesWrapper();
        customerWrapper.setCustomers(customers);
        this.parser.fromObjectToXML(CustomerTotalSalesWrapper.class, customerWrapper, OutputFilePaths.CUSTOMERS_TOTAL_SALES);
    }

    private void salesDiscounts() throws IOException, JAXBException {
        List<SaleWIthDiscount> sales = this.saleService.getAllSalesWithDiscount();
        SaleWithDiscountWrapper saleWrapper = new SaleWithDiscountWrapper();
        saleWrapper.setSales(sales);
        this.parser.fromObjectToXML(SaleWithDiscountWrapper.class, saleWrapper, OutputFilePaths.SALES_DISCOUNTS);
    }

}
