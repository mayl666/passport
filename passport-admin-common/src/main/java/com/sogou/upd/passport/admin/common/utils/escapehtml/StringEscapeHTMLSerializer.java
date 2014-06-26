package com.sogou.upd.passport.admin.common.utils.escapehtml;

import com.sogou.upd.passport.admin.common.utils.taglib.tag.Functions;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 自定义StringSerializer的实现
 * <p/>
 * User: chengang
 * Date: 14-6-25
 * Time: 下午6:03
 */
public final class StringEscapeHTMLSerializer
        extends NonTypedScalarSerializerBase<String> {
    public StringEscapeHTMLSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonGenerationException {
        jgen.writeString(Functions.escapeHTML(value));
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("string", true);
    }
}
