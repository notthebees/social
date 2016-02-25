package app;

import java.util.List;
import java.util.Set;

public interface NetworkingApp {

	void postMessage(User user, Message message);

	Set<User> users();

	List<Message> readTimeline(User user);

}