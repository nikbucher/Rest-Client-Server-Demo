package com.example.demo;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public final class DateObject {
	private final Instant instant;
	private final OffsetDateTime offset;
	private final ZonedDateTime zoned;

	public DateObject(Instant instant, OffsetDateTime offset, ZonedDateTime zoned) {
		this.instant = instant;
		this.offset = offset;
		this.zoned = zoned;
	}

	public Instant getInstant() {
		return instant;
	}

	public OffsetDateTime getOffset() {
		return offset;
	}

	public ZonedDateTime getZoned() {
		return zoned;
	}

	@Override
	public String toString() {
		return "DateObject[" +
				"instant=" + instant + ", " +
				"offset=" + offset + ", " +
				"zoned=" + zoned + ']';
	}

}
