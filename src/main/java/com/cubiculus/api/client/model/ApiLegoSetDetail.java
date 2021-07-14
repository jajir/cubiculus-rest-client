package com.cubiculus.api.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * LEGO set including all details.
 */
public class ApiLegoSetDetail extends ApiLegoSet {

    private List<ApiImage> images = null;

    public ApiLegoSetDetail addImagesItem(ApiImage imagesItem) {
        if (this.images == null) {
            this.images = new ArrayList<ApiImage>();
        }
        this.images.add(imagesItem);
        return this;
    }

    /**
     * List of LEGO set images
     * 
     * @return images
     **/
    public List<ApiImage> getImages() {
        return images;
    }

    public void setImages(List<ApiImage> images) {
        this.images = images;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiLegoSetDetail legoSetDetail = (ApiLegoSetDetail) o;
        return Objects.equals(this.images, legoSetDetail.images) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(images, super.hashCode());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiLegoSetDetail.class).add("idLegoSet", getIdLegoSet())
                .add("avgRating", getAvgRating()).add("boxNo", getBoxNo()).add("name", getName())
                .add("released", getReleased()).add("price", getPrice()).add("pieces", getPieces())
                .add("description", getDescription()).add("mainImage", getMainImage())
                .add("images", images).toString();
    }

}
