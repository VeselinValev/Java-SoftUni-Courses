package exerciseOOP;

public class Player {
    private String name;
    private double endurance = 0;
    private double sprint = 0;
    private double dribble = 0;
    private double passing = 0;
    private double shooting = 0;
    private double skillLevel = 0;

    public Player(String name, double endurance, double sprint, double dribble, double passing, double shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
        this.setSkillLevel();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public double getEndurance() {

        return endurance;
    }

    private void setEndurance(double endurance) {
        this.endurance = endurance;
    }

    public double getSprint() {
        return sprint;
    }

    private void setSprint(double sprint) {
        this.sprint = sprint;
    }

    public double getDribble() {
        return dribble;
    }

    private void setDribble(double dribble) {
        this.dribble = dribble;
    }

    public double getPassing() {
        return passing;
    }

    private void setPassing(double passing) {
        this.passing = passing;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel() {
        this.skillLevel = (this.endurance + this.dribble + this.shooting + this.passing + this.sprint)/5;
    }
}
