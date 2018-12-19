package exerciseOOP;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double surficeArea() {
        return (this.height * this.width) * 2 + (this.height * this.length) * 2 + (this.length * this.width) * 2;
    }
    public double lateralSurficeArea() {
        return (this.height * this.width) * 2 + (this.height * this.length) * 2;
    }
    public double volume() {
        return this.height * this.length * this.width;
    }
}
