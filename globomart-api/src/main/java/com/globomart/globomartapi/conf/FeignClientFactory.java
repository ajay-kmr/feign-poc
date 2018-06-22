package com.globomart.globomartapi.conf;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

public class FeignClientFactory {

    private GsonEncoder encoder;
    private GsonDecoder decoder;
    private Request.Options options;

    public FeignClientFactory() {
        this.encoder = new GsonEncoder();
        this.decoder = new GsonDecoder();
        options = new Request.Options(30 * 1000, 15 * 60 * 1000);
    }

    public FeignClientFactory(GsonEncoder encoder, GsonDecoder decoder, Request.Options options) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.options = options;
    }

    public <T> T getInstance(Class<T> apiType, String baseUrl) {
        return Feign.builder()
                .client(new OkHttpClient())
                .options(options)
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Logger.JavaLogger())
                .logLevel(Logger.Level.BASIC)
                .retryer(Retryer.NEVER_RETRY)
                .target(apiType, baseUrl);
    }
}
