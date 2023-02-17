package com.example.mineditse;

public class PostModel {

    String prodName, prodStatus, prodPrice, prodImgPath;
    int baleId;

    public PostModel(String prodName, String prodStatus, String prodPrice, String prodImgPath, int baleId) {
        this.prodName = prodName;
        this.prodStatus = prodStatus;
        this.prodPrice = prodPrice;
        this.prodImgPath = prodImgPath;
        this.baleId = baleId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdImgPath() {
        return prodImgPath;
    }

    public void setProdImgPath(String prodImgPath) {
        this.prodImgPath = prodImgPath;
    }

    public int getBaleId() {
        return baleId;
    }

    public void setBaleId(int baleId) {
        this.baleId = baleId;
    }
}
