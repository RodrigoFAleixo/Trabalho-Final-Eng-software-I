package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Carrinho de compras que agrupa vários CartItemDTO.
 */
public class ShoppingCartDTO {
    private final List<CartItemDTO> items = new ArrayList<>();

    public void addItem(ProductDTO product, int quantity) {
        for (CartItemDTO item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItemDTO(product, quantity));
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void clear() {
        items.clear();
    }

    
    public List<CartItemDTO> getItems() {
        return List.copyOf(items);
    }

    public double getTotal() {
        return items.stream()
                    .mapToDouble(CartItemDTO::getSubtotal)
                    .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
               "items=" + items +
               ", total=" + getTotal() +
               '}';
    }
}

