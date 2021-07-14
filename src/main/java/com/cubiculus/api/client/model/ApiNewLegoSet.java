package com.cubiculus.api.client.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Information about LEGO set.
 */
public class ApiNewLegoSet extends ApiBaseLegoSet {
    private ApiImageData mainImage = null;

    /**
     * Get mainImage
     * 
     * @return mainImage
     **/
    public ApiImageData getMainImage() {
        return mainImage;
    }

    public void setMainImage(ApiImageData mainImage) {
        this.mainImage = mainImage;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiNewLegoSet newLegoSet = (ApiNewLegoSet) o;
        return Objects.equals(this.mainImage, newLegoSet.mainImage) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainImage, super.hashCode());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiNewLegoSet.class).add("boxNo", getBoxNo())
                .add("name", getName()).add("released", getReleased()).add("price", getPrice())
                .add("pieces", getPieces()).add("description", getDescription())
                .add("mainImage", mainImage).toString();
    }

}
