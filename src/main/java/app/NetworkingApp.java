package app;

public interface NetworkingApp {

	void postMessage(String username, Message message);

	void readTimeline(String username);

	void follow(String subscriberName, String publisherName);

	void wall(String username);

}