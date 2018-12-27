package com.forwork.web.www.common.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class ShortUUID {

	public static String build() {
		UUID uuid = UUID.randomUUID();
		byte[] bytes = uuid.toString().getBytes();
		long l = ByteBuffer.wrap(bytes).getLong();

		return Long.toString(l, Character.MAX_RADIX);
	}

}