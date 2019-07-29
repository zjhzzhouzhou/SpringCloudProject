package com.dyhospital.cloudhis.common.redis.serialize;

public interface Serializer {

	byte[] serialize(Object obj);
	Object deserialize(byte[] bytes);

}
