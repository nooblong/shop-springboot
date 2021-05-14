package github.nooblong.r5shopspringboot.common.po;

public class OrderExtend {
    private int id;
    private int product_id;
    private int count;
    private int order_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderExtend{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", count=" + count +
                ", order_id=" + order_id +
                '}';
    }
}
