package com.cubiculus.api.client.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Information about LEGO set.
 */
public class ApiLegoSet extends ApiBaseLegoSet {

    private Integer idLegoSet = null;
    private ApiImage mainImage = null;
    private Integer avgRating = null;

    /**
     * Id of LEGO set in cubiculus database.
     * 
     * @return idLegoSet
     **/
    public Integer getIdLegoSet() {
        return idLegoSet;
    }

    public void setIdLegoSet(Integer idLegoSet) {
        this.idLegoSet = idLegoSet;
    }

    /**
     * Get mainImage
     * 
     * @return mainImage
     **/
    public ApiImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(ApiImage mainImage) {
        this.mainImage = mainImage;
    }

    /**
     * LEGO set rating nultiplied by 2.
     * 
     * @return avgRating
     **/
    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiLegoSet legoSet = (ApiLegoSet) o;
        return Objects.equals(this.idLegoSet, legoSet.idLegoSet)
                && Objects.equals(this.mainImage, legoSet.mainImage)
                && Objects.equals(this.avgRating, legoSet.avgRating) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLegoSet, mainImage, avgRating, super.hashCode());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiLegoSet.class).add("idLegoSet", idLegoSet)
                .add("avgRating", avgRating).add("boxNo", getBoxNo()).add("name", getName())
                .add("released", getReleased()).add("price", getPrice()).add("pieces", getPieces())
                .add("description", getDescription()).add("mainImage", mainImage).toString();
    }

}
