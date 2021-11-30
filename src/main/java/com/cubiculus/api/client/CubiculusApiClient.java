package com.cubiculus.api.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.cubiculus.api.client.model.ApiBaseLegoSet;
import com.cubiculus.api.client.model.ApiImage;
import com.cubiculus.api.client.model.ApiImageData;
import com.cubiculus.api.client.model.ApiLegoSet;
import com.cubiculus.api.client.model.ApiNewLegoSet;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Main class that allows interact with cubiculus.com server.
 * 
 * @author jan
 *
 */
public class CubiculusApiClient {

    private final static Type TYPE_LIST_OF_LEGOSETS = new TypeToken<ArrayList<ApiLegoSet>>() {
    }.getType();
    private final static String URI_PART_LEGOSETS = "legosets/";

    private final URI apiBaseUrl;
    private final HttpClient client;
    private final Gson gson;

    public CubiculusApiClient(final String apiBaseUrl) {
        try {
            this.apiBaseUrl = new URI(Preconditions.checkNotNull(apiBaseUrl));
        } catch (URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }
        this.client = HttpClient.newBuilder()//
                .version(Version.HTTP_2)//
                .followRedirects(Redirect.ALWAYS)//
                .build();
        this.gson = new GsonBuilder()//
                .setPrettyPrinting()//
                .disableHtmlEscaping()//
                .create();
    }

    public List<ApiLegoSet> getLegoSets(final String searchQuery) {
        URI uri = apiBaseUrl.resolve(URI_PART_LEGOSETS);
        if (searchQuery != null) {
            uri = uri.resolve("?query=" + searchQuery);
        }
        final HttpRequest request = HttpRequest.newBuilder(uri) //
                .header("Accept", "application/json")//
                .build();
        return makeCall(request, response -> {
            final List<ApiLegoSet> out = gson.fromJson(response.body(), TYPE_LIST_OF_LEGOSETS);
            return out;
        });
    }

    public List<ApiLegoSet> getLegoSetsByNo(final String legoSetNo) {
        URI uri = apiBaseUrl.resolve(URI_PART_LEGOSETS);
        if (legoSetNo != null) {
            uri = uri.resolve("?legoSetNo=" + legoSetNo);
        }
        final HttpRequest request = HttpRequest.newBuilder(uri) //
                .header("Accept", "application/json")//
                .build();
        return makeCall(request, response -> {
            final List<ApiLegoSet> out = gson.fromJson(response.body(), TYPE_LIST_OF_LEGOSETS);
            return out;
        });
    }

    public ApiLegoSet createNewLegoSet(final String apiKey, final ApiNewLegoSet apiNewLegoSet) {
        final String postBody = gson.toJson(apiNewLegoSet);
        final HttpRequest request = HttpRequest.newBuilder(apiBaseUrl.resolve("legosets"))//
                .header("Accept", "application/json")//
                .header("Content-Type", "application/json")//
                .header("X-API-Key", apiKey)//
                .POST(BodyPublishers.ofString(postBody))//
                .build();
        return makeCall(request, response -> {
            final ApiLegoSet out = gson.fromJson(response.body(), ApiLegoSet.class);
            return out;
        });
    }

    public ApiImage createNewLegoSetImage(final String apiKey, final Integer idLegoSet,
            final ApiImageData apiImageData) {
        final String postBody = gson.toJson(apiImageData);
        final URI uri = apiBaseUrl.resolve(URI_PART_LEGOSETS + idLegoSet + "/images/");
        final HttpRequest request = HttpRequest.newBuilder(uri)//
                .header("Accept", "application/json")//
                .header("Content-Type", "application/json")//
                .header("X-API-Key", apiKey)//
                .POST(BodyPublishers.ofString(postBody))//
                .build();
        return makeCall(request, response -> {
            final ApiImage out = gson.fromJson(response.body(), ApiImage.class);
            return out;
        });
    }

    public boolean isExists(final String legoSetNo) {
        final List<ApiLegoSet> list = getLegoSetsByNo(legoSetNo);
        return !list.isEmpty();
    }

    public ApiLegoSet updateLegoSet(final String apiKey, final Integer idLegoSet,
            final ApiBaseLegoSet apiBaseLegoSet) {
        final String postBody = gson.toJson(apiBaseLegoSet);
        final URI uri = apiBaseUrl.resolve(URI_PART_LEGOSETS + idLegoSet + "/");
        final HttpRequest request = HttpRequest.newBuilder(uri)//
                .header("Accept", "application/json")//
                .header("Content-Type", "application/json")//
                .header("X-API-Key", apiKey)//
                .PUT(BodyPublishers.ofString(postBody))//
                .build();
        return makeCall(request, response -> {
            final ApiLegoSet out = gson.fromJson(response.body(), ApiLegoSet.class);
            return out;
        });
    }

    public ApiImage updateLegoSetImage(final String apiKey, final Integer idLegoSet,
            final Integer idImage, final ApiImageData apiImageData) {
        final String postBody = gson.toJson(apiImageData);
        final URI uri = apiBaseUrl.resolve(URI_PART_LEGOSETS + idLegoSet + "/images/" + idImage);
        final HttpRequest request = HttpRequest.newBuilder(uri)//
                .header("Accept", "application/json")//
                .header("Content-Type", "application/json")//
                .header("X-API-Key", apiKey)//
                .PUT(BodyPublishers.ofString(postBody))//
                .build();
        return makeCall(request, response -> {
            final ApiImage out = gson.fromJson(response.body(), ApiImage.class);
            return out;
        });
    }

    private <T> T makeCall(final HttpRequest request,
            final ExceptionFunction<HttpResponse<String>, T> code200Consumer) {
        try {
            final HttpResponse<String> response = client.send(request,
                    BodyHandlers.ofString(Charsets.UTF_8));
            if (response.statusCode() == 200) {
                return PossiblyFunction.of(code200Consumer).apply(response)
                        .doIfException(exception -> {

                        }).getValue()
                        .orElseThrow(() -> new ClientException(String.format(
                                "Unable to process reponse '%s' "
                                        + "with body '%s' for request '%s'",
                                response, response.body(), request)));

            } else {
                throw new ClientException(
                        response.statusCode() + " from: " + request + "\n" + response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

}
