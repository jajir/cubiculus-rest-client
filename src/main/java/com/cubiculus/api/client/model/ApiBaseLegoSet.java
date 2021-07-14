package com.cubiculus.api.client.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * BaseLegoSet
 */

public class ApiBaseLegoSet {

    private String boxNo = null;
    private Integer released = null;
    private String name = null;
    private String description = null;
    private BigDecimal price = null;
    private Integer pieces = null;

    /**
     * LEGO set number
     * 
     * @return boxNo
     **/
    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    /**
     * LEGO set release year
     * 
     * @return released
     **/
    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    /**
     * LEGO set name
     * 
     * @return name
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * LEGO set description
     * 
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * LEGO set original price
     * 
     * @return price
     **/
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Number of LEGOpieces in LEGO set
     * 
     * @return pieces
     **/
    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiBaseLegoSet baseLegoSet = (ApiBaseLegoSet) o;
        return Objects.equals(this.boxNo, baseLegoSet.boxNo)
                && Objects.equals(this.released, baseLegoSet.released)
                && Objects.equals(this.name, baseLegoSet.name)
                && Objects.equals(this.description, baseLegoSet.description)
                && Objects.equals(this.price, baseLegoSet.price)
                && Objects.equals(this.pieces, baseLegoSet.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxNo, released, name, description, price, pieces);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiBaseLegoSet.class).add("boxNo", boxNo)
                .add("name", name).add("released", released).add("price", price)
                .add("pieces", pieces).add("description", description).toString();
    }

}
