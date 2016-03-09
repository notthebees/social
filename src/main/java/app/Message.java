package app;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Message {

	private final String message;
	private final String author;
	private final Date date;

	public Message(final String message, final String author) {
		this.message = message;
		this.author = author;
		date = new Date();
	}

	public static List<Message> sort(final List<Message> messages) {
		Collections.sort(messages, new MessageComparator());
		return messages;
	}

	private static class MessageComparator implements Comparator<Message> {
		public int compare(final Message message0, final Message message1) {
			return message1.date.compareTo(message0.date);
		}
	}

	@Override
	public String toString() {
		return message + timeStamp();
	}

	private String timeStamp() {
		final long milliseconds = new Date().getTime() - date.getTime();
		if (milliseconds >= 60000) {
			final long minutes = milliseconds / 60000;
			return " (" + minutes + " minutes ago)";
		} else {
			final long seconds = milliseconds / 1000;
			return " (" + seconds + " seconds ago)";
		}
	}

	public String withAuthor() {
		return author + " - " + toString();
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
