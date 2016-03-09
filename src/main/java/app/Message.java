package app;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Message {

	private final String message;
	private final Date date;

	public Message(final String message) {
		this.message = message;
		date = new Date();
	}

	@Override
	public String toString() {
		final long milliseconds = new Date().getTime() - date.getTime();
		final long seconds = milliseconds / 1000;
		return message + " (" + seconds + " seconds ago)";
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Message) {
			final Message other = (Message) obj;
			return new EqualsBuilder()
			.append(message, other.message)
			.isEquals();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(message)
		.toHashCode();
	}

}
