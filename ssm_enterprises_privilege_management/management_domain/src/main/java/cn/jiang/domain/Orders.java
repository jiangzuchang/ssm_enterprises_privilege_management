package cn.jiang.domain;

import cn.jiang.util.DateFormatUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class Orders implements Serializable {
    private String id;//订单id
    private String orderNum;//订单编号
    private Date orderTime;//下单时间
    private String orderTimeStr;
    private int orderStatus;//订单状态
    private String orderStatusStr;
    private int peopleCount;//订单内包含的人数
    private Product product;//产品
    private List<Traveller> travellers;//游客列表
    private Member member;//成员列表
    private Integer payType;//支付方式：0支付宝、1微信、2其他
    private String payTypeStr;
    private String orderDesc;//订单描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime != null) {
            return DateFormatUtil.DateToStr(orderTime, "yyyy年MM月dd日");
        }
        return null;

    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {
        if (orderStatus == 1) {
            return "开启";
        } else if (orderStatus == 0) {
            return "关闭";
        }
        return null;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        if (payType != null) {
            if (payType == 0) {
                return "支付宝";
            } else if (payType == 1) {
                return "微信";
            } else {
                return "其他";
            }
        }
        return null;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
