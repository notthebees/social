package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private final String name;
	private final List<Message> timeline = new ArrayList<Message>();
	private final Set<User> subscriptions = new HashSet<User>();

	public User(final String name) {
		this.name = name;
	}

	public List<Message> timeline() {
		return new ArrayList<Message>(timeline);
	}

	public List<Message> wall() {
		final List<Message> wall = new ArrayList<Message>();
		wall.addAll(timeline);
		for (final User publisher : subscriptions) {
			wall.addAll(publisher.timeline);
		}
		return wall;
	}

	public void addMessage(final Message message) {
		timeline.add(0, message);
	}

	public void addSubscription(final User publisher) {
		subscriptions.add(publisher);
	}

	public boolean nameIs(final String username) {
		return name.equals(username);
	}

	@Override
	public String toString() {
		return "User: " + name;
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
