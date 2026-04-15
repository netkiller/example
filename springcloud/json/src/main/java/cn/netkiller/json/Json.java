package cn.netkiller.json;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;

@JsonComponent
public class Json {

	public Json() {
		// TODO Auto-generated constructor stub
	}

	public static class MemberJsonSerializer extends JsonSerializer<Member> {

		@Override
		public void serialize(Member value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			// TODO Auto-generated method stub
			gen.writeStartObject();
			gen.writeStringField("member", value.toString());
			gen.writeEndObject();

		}
	}

	public static class MemberJsonDeserializer extends JsonDeserializer<Member> {

		@Override
		public Member deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub
			TreeNode treeNode = p.getCodec().readTree(p);
			TextNode member = (TextNode) treeNode.get("member");
			return new Member(member.asText());
		}
	}
}