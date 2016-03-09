package app;

import java.util.Set;

public interface NetworkingApp {

	void postMessage(User user, Message message);

	Set<User> users();

	void readTimeline(User user);

	void follow(User subscriber, User publisher);

	void wall(User user);

}