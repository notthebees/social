package app;

public interface CommandParser {

	void postMessage(User alice, Message message);

	void connectTo(SocialApp socialApp);

}
