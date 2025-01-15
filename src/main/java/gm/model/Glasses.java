package gm.model;

public class Glasses {
    private String brand;
    private String frameType;
    private String frameColor;
    private double price;
    private double rightLensPower;
    private double leftLensPower;
    private String rightLensColor;
    private String leftLensColor;
    private String supplierId; // Referencia al proveedor

    public Glasses(String brand, String frameType, String frameColor, double price,
                   double rightLensPower, double leftLensPower, String rightLensColor,
                   String leftLensColor, String supplierId) {
        this.brand = brand;
        this.frameType = frameType;
        this.frameColor = frameColor;
        this.price = price;
        this.rightLensPower = rightLensPower;
        this.leftLensPower = leftLensPower;
        this.rightLensColor = rightLensColor;
        this.leftLensColor = leftLensColor;
        this.supplierId = supplierId;
    }

    // Getters y Setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getFrameType() { return frameType; }
    public void setFrameType(String frameType) { this.frameType = frameType; }

    public String getFrameColor() { return frameColor; }
    public void setFrameColor(String frameColor) { this.frameColor = frameColor; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRightLensPower() { return rightLensPower; }
    public void setRightLensPower(double rightLensPower) { this.rightLensPower = rightLensPower; }

    public double getLeftLensPower() { return leftLensPower; }
    public void setLeftLensPower(double leftLensPower) { this.leftLensPower = leftLensPower; }

    public String getRightLensColor() { return rightLensColor; }
    public void setRightLensColor(String rightLensColor) { this.rightLensColor = rightLensColor; }

    public String getLeftLensColor() { return leftLensColor; }
    public void setLeftLensColor(String leftLensColor) { this.leftLensColor = leftLensColor; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
}
