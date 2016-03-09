package app;

import java.util.HashSet;
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

	public Wall wall() {
		Wall wall = new Wall(timeline.messages());
		for (final User publisher : subscriptions) {
			wall = wall.merge(publisher.timeline.messages());
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
