package app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Wall {

	private final List<Message> messages = new ArrayList<Message>();

	public Wall(final List<Message> messages) {
		this.messages.addAll(messages);
	}

	public Wall(final Message...messages) {
		for (final Message message : messages) {
			this.messages.add(message);
		}
	}

	public Wall mergeWith(final Wall other) {
		final List<Message> messages = new ArrayList<Message>(this.messages);
		messages.addAll(other.messages);
		return new Wall(messages);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (final Message message : messages) {
			builder.append(message);
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
