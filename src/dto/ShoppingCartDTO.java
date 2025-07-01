package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Carrinho de compras que agrupa vários CartItemDTO.
 */
public class ShoppingCartDTO {
    private final List<CartItemDTO> items = new ArrayList<>();

    /**
     * Adiciona um produto ao carrinho.
     * Se o mesmo produto já existir, acumula a quantidade.
     */
    public void addItem(ProductDTO product, int quantity) {
        for (CartItemDTO item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItemDTO(product, quantity));
    }

    /**
     * Remove completamente um CartItem do carrinho.
     */
    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    /**
     * Limpa todos os itens do carrinho.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Retorna a lista imutável de itens.
     */
    public List<CartItemDTO> getItems() {
        return List.copyOf(items);
    }

    /**
     * Calcula o valor total de todos os itens (somatório de subtotais).
     */
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

