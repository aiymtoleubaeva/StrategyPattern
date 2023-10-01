// Strategy interface for calculating discounts
interface DiscountStrategy {
    double applyDiscount(double originalPrice);
}

// Concrete strategy: 10% discount
class TenPercentDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.9; // 10% discount
    }
}

// Concrete strategy: 20% discount
class TwentyPercentDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice * 0.8; // 20% discount
    }
}

// Product class
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

// Shopping cart class
class ShoppingCart {
    private DiscountStrategy discountStrategy;
    private List<Product> products = new ArrayList<>();

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        if (discountStrategy != null) {
            totalPrice = discountStrategy.applyDiscount(totalPrice);
        }

        return totalPrice;
    }
}

public class ProductStoreExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Add products to the cart
        Product product1 = new Product("Product 1", 100.0);
        Product product2 = new Product("Product 2", 50.0);
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Choose the discount strategy (10% discount)
        DiscountStrategy discountStrategy = new TenPercentDiscount();
        cart.setDiscountStrategy(discountStrategy);

        // Calculate the total amount with the applied discount
        double totalPrice = cart.calculateTotal();
        System.out.println("Total amount after applying the discount: $" + totalPrice);
    }
}