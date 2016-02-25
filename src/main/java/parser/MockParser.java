package parser;

import java.util.ArrayList;
import java.util.List;

import app.Message;
import app.SocialApp;
import app.User;

public class MockParser implements CommandParser {

	private final List<SocialApp> listeners = new ArrayList<SocialApp>();

	public void connectTo(final SocialApp socialApp) {
		listeners.add(socialApp);
	}

	public void postMessage(final User user, final Message message) {
		for (final SocialApp listener : listeners) {
			listener.postMessage(user, message);
		}
	}

}
