package exerciseOOP;

public class Worker extends Human {
    private double weekSalary;
    private double hoursPerDay;

    public Worker(String firstName, String lastName, double weekSalary, double hoursPerDay) {
        super(firstName, lastName);
        this.weekSalary = weekSalary;
        this.hoursPerDay = hoursPerDay;
    }

    public double getWeekSalary() {
        return weekSalary;
    }

    private void setWeekSalary(double weekSalary) {
        if(weekSalary <= 10){
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public double getHoursPerDay() {
        if(hoursPerDay < 1 || hoursPerDay > 12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        return hoursPerDay;
    }

    private void setHoursPerDay(double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }
    @Override
    protected void setLastName(String lastName){
        if(!Character.isUpperCase(lastName.charAt(0))){
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        }
        if(lastName.length() < 4){
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
        super.setLastName(lastName);
    }

    public double claculateSalaryPerHour(){
        return this.weekSalary / (this.hoursPerDay * 7);
    }
}
