package com.cubiculus.api.client.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Image object including link to scaled versions of image.
 */
public class ApiImage {

    private String id = null;
    private String size100 = null;
    private String size160 = null;
    private String size500 = null;
    private String size1024 = null;
    private String original = null;
    private Integer width = null;
    private Integer height = null;

    public ApiImage id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Image identification id
     * 
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Link to image scaled down to fit square 100 x 100 pixels, sides keep aspect
     * ratio.
     * 
     * @return size100
     **/
    public String getSize100() {
        return size100;
    }

    public void setSize100(String size100) {
        this.size100 = size100;
    }

    /**
     * Link to image scaled down to fit square 160 x 160 pixels, sides keep aspect
     * ratio.
     * 
     * @return size160
     **/
    public String getSize160() {
        return size160;
    }

    public void setSize160(String size160) {
        this.size160 = size160;
    }

    /**
     * Link to image scaled down to fit square 500 x 500 pixels, sides keep aspect
     * ratio.
     * 
     * @return size500
     **/
    public String getSize500() {
        return size500;
    }

    public void setSize500(String size500) {
        this.size500 = size500;
    }

    /**
     * Link to image with width 1024 height is computed keeping original image
     * aspect ratio
     * 
     * @return size1024
     **/
    public String getSize1024() {
        return size1024;
    }

    public void setSize1024(String size1024) {
        this.size1024 = size1024;
    }

    /**
     * Link to original image
     * 
     * @return original
     **/
    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * Original image width
     * 
     * @return width
     **/
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Original image height
     * 
     * @return height
     **/
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiImage image = (ApiImage) o;
        return Objects.equals(this.id, image.id) && Objects.equals(this.size100, image.size100)
                && Objects.equals(this.size160, image.size160)
                && Objects.equals(this.size500, image.size500)
                && Objects.equals(this.size1024, image.size1024)
                && Objects.equals(this.original, image.original)
                && Objects.equals(this.width, image.width)
                && Objects.equals(this.height, image.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size100, size160, size500, size1024, original, width, height);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiImage.class).add("id", id).add("width", width)
                .add("height", height).add("original", original).add("size100", size100)
                .add("size160", size160).add("size500", size500).add("size1024", size1024)
                .toString();
    }
}
