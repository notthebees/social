package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

	private final String name;
	private final Timeline timeline;
	private final Set<User> subscriptions = new HashSet<User>();

	public User(final String name) {
		this.name = name;
		timeline = new Timeline();
	}

	public Timeline timeline() {
		return timeline;
	}

	public List<Message> wall() {
		final List<Message> wall = new ArrayList<Message>();
		wall.addAll(timeline.messages());
		for (final User publisher : subscriptions) {
			wall.addAll(publisher.timeline.messages());
		}
		return wall;
	}

	public void addMessage(final Message message) {
		timeline.add(message);
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
