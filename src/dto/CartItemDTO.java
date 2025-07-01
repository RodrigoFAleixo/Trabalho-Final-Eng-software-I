package dto;

import java.util.Objects;

/**
 * Representa um item no carrinho de compras:
 * um produto + quantidade desejada.
 */
public class CartItemDTO {
    private ProductDTO product;
    private int quantity;

    public CartItemDTO() {
    }

    public CartItemDTO(ProductDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Retorna o subtotal deste item (preço * quantidade).
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItemDTO{" +
               "product=" + product +
               ", quantity=" + quantity +
               ", subtotal=" + getSubtotal() +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDTO)) return false;
        CartItemDTO that = (CartItemDTO) o;
        return Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId());
    }
}

