package common;

public interface JsonSerializer {
	String serialize(Object object);

	<T> T deserialize(String json, Class<T> clazz);
}
