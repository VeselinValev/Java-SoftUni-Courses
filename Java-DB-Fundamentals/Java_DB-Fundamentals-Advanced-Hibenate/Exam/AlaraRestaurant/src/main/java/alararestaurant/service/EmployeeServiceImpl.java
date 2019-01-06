package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String FILE_PATH = "C:\\Users\\Ves\\Desktop\\AlaraRestaurant\\src\\main\\resources\\files\\employees.json";

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private Gson gson;
    private PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, Gson gson, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.positionRepository = positionRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(this.FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder sb = new StringBuilder();
        EmployeeDto[] employeeDtos = this.gson.fromJson(employees, EmployeeDto[].class);

        for (EmployeeDto employeeDto: employeeDtos){
            if (!this.validationUtil.isValid(employeeDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Employee employee = this.modelMapper.map(employeeDto, Employee.class);

            Position position = this.positionRepository.getByName(employeeDto.getPosition());

            if (position == null){
                position = new Position();
                position.setName(employeeDto.getPosition());
                this.positionRepository.saveAndFlush(position);
            }

            employee.setPosition(position);

            this.employeeRepository.saveAndFlush(employee);
            sb.append(String.format("Record %s successfully imported.%n", employee.getName()));
        }
        return sb.toString().trim();
    }
}
