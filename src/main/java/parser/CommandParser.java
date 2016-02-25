package parser;

import app.Message;
import app.SocialApp;
import app.User;

public interface CommandParser {

	void postMessage(User user, Message message);

	void connectTo(SocialApp socialApp);

}
