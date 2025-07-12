public class PaperBook extends  Book{
    private int Quantity ;
    private   double Price ;

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public int getQuantity() {
        return Quantity;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getPrice() {
        return Price;
    }
}
