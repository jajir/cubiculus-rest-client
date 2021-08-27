package com.cubiculus.api.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cubiculus.api.client.model.ApiBaseLegoSet;
import com.cubiculus.api.client.model.ApiImage;
import com.cubiculus.api.client.model.ApiImageData;
import com.cubiculus.api.client.model.ApiLegoSet;
import com.cubiculus.api.client.model.ApiNewLegoSet;
import com.google.common.io.Files;

public class SimpleTest {

    /**
     * Robot's API key.
     */
    final static String API_KEY = "1up0cnknlr8evnfpaa17dijqs3vunenrl09bn0o581mo8cponlmp6g3odhck7k8n";

    @Test
    public void test_get_simple_list_of_images() throws Exception {
        final CubiculusApiClient client = new CubiculusApiClient("http://localhost:8080/api/v2/");
        final List<ApiLegoSet> ret = client.getLegoSets(null);
        assertNotNull(ret);
        System.out.println(ret);
    }

    @Test
    public void test_create_legoSet_with_additional_image() throws Exception {
        final CubiculusApiClient client = new CubiculusApiClient(
                "http://localhost:8080/api/v2/legosets");
        final byte[] file1 = loadFile("./src/test/resources/download-icon1.png");
        final ApiImageData mainImage = new ApiImageData();
        mainImage.setFileName("./src/test/resources/download-icon.png");
        mainImage.setData(Base64.getEncoder().encodeToString(file1));
        final ApiNewLegoSet apiNewLegoSet = new ApiNewLegoSet();
        apiNewLegoSet.setBoxNo("23423");
        apiNewLegoSet.setName("Nejaky super set");
        apiNewLegoSet.setPieces(987);
        apiNewLegoSet.setPrice(BigDecimal.valueOf(13.99));
        apiNewLegoSet.setReleased(2021);
        apiNewLegoSet.setDescription("Nejaky super pekne LEGO.");
        apiNewLegoSet.setMainImage(mainImage);
        // Create legoSet
        final ApiLegoSet ret = client.createNewLegoSet(API_KEY, apiNewLegoSet);
        assertNotNull(ret);
        System.out.println(ret);

        // upload additional image
        final byte[] file2 = loadFile("./src/test/resources/download-icon2.png");
        final ApiImageData sedondaryImage = new ApiImageData();
        sedondaryImage.setFileName("./src/test/resources/download-icon.png");
        sedondaryImage.setData(Base64.getEncoder().encodeToString(file2));
        ApiImage apiImage = client.createNewLegoSetImage(API_KEY, ret.getIdLegoSet(),
                sedondaryImage);
        assertNotNull(apiImage);
        System.out.println(apiImage);

        final ApiBaseLegoSet baseLegoSet = new ApiBaseLegoSet();
        baseLegoSet.setBoxNo("1111");
        baseLegoSet.setName("red car");
        baseLegoSet.setDescription("Nice red car with blue door.");
        baseLegoSet.setPieces(100);
        baseLegoSet.setPrice(BigDecimal.valueOf(10));
        ApiLegoSet apiLegoSet = client.updateLegoSet(API_KEY, ret.getIdLegoSet(), baseLegoSet);
        System.out.println("Updated object:");
        System.out.println(apiLegoSet);

        ApiImage updated = client.updateLegoSetImage(API_KEY, ret.getIdLegoSet(),
                Integer.valueOf(apiImage.getId()), mainImage);
        System.out.println(updated);
    }

    @Test
    public void test_isLegoSetExists() throws Exception {
        final CubiculusApiClient client = new CubiculusApiClient("http://localhost:8080/api/v2/");
        assertTrue(client.isExists("123"));
        assertFalse(client.isExists("076543"));
    }

    @Test
    public void test_URI_work() throws Exception {
        URI uri = new URI("http//localhost:8080/api/v2/");
        System.out.println(uri.resolve("legosets/2324/images"));
        System.out.println(uri.resolve("?param=342").resolve("?another=31232"));
    }

    @Test
    void test_update_legoset() throws Exception {

    }

    public byte[] loadFile(final String imageUrl) {
        final File file = new File(imageUrl);
        try {
            return Files.toByteArray(file);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

}
