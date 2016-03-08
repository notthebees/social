package app;

import java.util.Set;

public interface NetworkingApp {

	void postMessage(User user, Message message);

	Set<User> users();

	void readTimeline(User user);

}