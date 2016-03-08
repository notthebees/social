package app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private final String name;
	private final List<Message> timeline = new ArrayList<Message>();

	public User(final String name) {
		this.name = name;
	}

	public void addMessage(final Message message) {
		timeline.add(message);
	}

	public void printTimeline() {
		for (final Message message : timeline) {
			System.out.println(message);
		}
	}

	@Override
	public String toString() {
		return "User: " + name;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User other = (User) obj;
			return new EqualsBuilder()
			.append(name, other.name)
			.isEquals();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(name)
		.toHashCode();
	}

}
