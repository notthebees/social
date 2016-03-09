package app;

public interface NetworkingApp {

	void postMessage(String username, Message message);

	Timeline readTimeline(String username);

	void follow(String subscriberName, String publisherName);

	Wall readWall(String username);

}