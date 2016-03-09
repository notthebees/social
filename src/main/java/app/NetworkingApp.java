package app;

import java.util.List;

public interface NetworkingApp {

	void postMessage(String username, Message message);

	Timeline readTimeline(String username);

	void follow(String subscriberName, String publisherName);

	List<Message> readWall(String username);

}