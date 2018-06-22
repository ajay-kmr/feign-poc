package com.globomart.globomartapi.conf.feign.jaxb;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.reflect.Type;

/**
 * Encodes requests using JAXB. <br>
 * <p>
 * Basic example with with Feign.Builder:
 * </p>
 * <p>
 * <pre>
 * JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
 *     .withMarshallerJAXBEncoding("UTF-8")
 *     .withMarshallerSchemaLocation("http://apihost http://apihost/schema.xsd")
 *     .build();
 *
 * api = Feign.builder()
 *     .encoder(new JAXBEncoder(jaxbFactory))
 *     .target(MyApi.class, "http://api");
 * </pre>
 * <p>
 * The JAXBContextFactory should be reused across requests as it caches the created JAXB contexts.
 * </p>
 */
public class JAXBEncoder implements Encoder {
    private final JAXBContextFactory jaxbContextFactory;

    public JAXBEncoder(JAXBContextFactory jaxbContextFactory) {
        this.jaxbContextFactory = jaxbContextFactory;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (!(bodyType instanceof Class)) {
            throw new UnsupportedOperationException(
                    "JAXB only supports encoding raw types. Found " + bodyType);
        }
        try {
            Marshaller marshaller = jaxbContextFactory.createMarshaller((Class) bodyType);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object, stringWriter);
            template.body(stringWriter.toString());
        } catch (JAXBException e) {
            throw new EncodeException(e.toString(), e);
        }
    }
}
