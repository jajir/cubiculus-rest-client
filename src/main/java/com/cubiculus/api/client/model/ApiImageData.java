package com.cubiculus.api.client.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Object for uploading image data.
 */
public class ApiImageData {

    private String fileName = null;
    private String data = null;

    /**
     * Image file name.
     * 
     * @return fileName
     **/
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Image data encoded in base64
     * 
     * @return data
     **/
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiImageData imageData = (ApiImageData) o;
        return Objects.equals(this.fileName, imageData.fileName)
                && Objects.equals(this.data, imageData.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ApiImageData.class).add("fileName", fileName).toString();
    }

}
