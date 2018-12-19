package exerciseOOP;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        if(length > 0){
            this.length = length;
        }else {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
    }

    private void setWidth(double width) {
        if(width > 0) {
            this.width = width;
        }else {

            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
    }

    private void setHeight(double height) {
        if(height > 0) {
            this.height = height;
        }else {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
    }

    public double surfaceArea() {
        return (this.height * this.width) * 2 + (this.height * this.length) * 2 + (this.length * this.width) * 2;
    }
    public double lateralSurfaceArea() {
        return (this.height * this.width) * 2 + (this.height * this.length) * 2;
    }
    public double volume() {
        return this.height * this.length * this.width;
    }
}
