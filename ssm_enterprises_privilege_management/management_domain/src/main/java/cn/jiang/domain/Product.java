package cn.jiang.domain;

import cn.jiang.util.DateFormatUtil;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * 产品实体类
 */
public class Product implements Serializable {
    private String id; // 主键
    private String productNum; // 编号 唯一
    private String productName; // 名称
    private String cityName; // 出发城市
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime; // 出发时间
    private String departureTimeStr;
    private double productPrice; // 产品价格
    private String productDesc; // 产品描述
    private Integer productStatus; // 状态 0 关闭 1 开启
    private String productStatusStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) throws ParseException {
//        //这里要注意加上工具类的日期格式转换
//        Date date = DateFormatUtil.StrToDate(departureTimeStr, "yyyy-MM-dd hh:mm");
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if (departureTime != null) {
            return DateFormatUtil.DateToStr(departureTime, "yyyy年MM月dd日");
        }
        return null;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
//        if (productStatusStr != null) {
//            if (productStatusStr.equals("关闭")) {
//                this.productStatus = 0;
//            } else if (productStatusStr.equals("开启")) {
//                this.productStatus = 1;
//            }
//        }
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus != null) {
            if (productStatus == 0) {
                return "关闭";
            } else if (productStatus == 1) {
                return "开启";
            }
        }
        return null;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
