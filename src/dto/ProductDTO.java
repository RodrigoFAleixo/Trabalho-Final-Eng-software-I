package dto;

public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public double getPrice() {
       return price;
    }

    public void setPrice(double price) {
       this.price = price;
    }

    public int getQuantity() {
       return quantity;
    }

    public void setQuantity(int quantity) {
       this.quantity = quantity;
    }

    @Override
    public String toString() {
       return "ProductDTO{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", price=" + price +
              ", quantity=" + quantity +
              '}';
    }
}

