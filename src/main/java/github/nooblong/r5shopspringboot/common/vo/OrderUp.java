package github.nooblong.r5shopspringboot.common.vo;

import java.util.List;
import java.util.Map;

public class OrderUp {
    private String name;
    private String room;
    private String tele;
    private String remark;
    private Map<Integer,Integer> productMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderUp{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", tele='" + tele + '\'' +
                ", remark='" + remark + '\'' +
                ", productMap=" + productMap +
                '}';
    }

    public Map<Integer, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Integer, Integer> productMap) {
        this.productMap = productMap;
    }
}
