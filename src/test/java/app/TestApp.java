package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.Test;

import parser.CommandParser;
import parser.MockParser;

public class TestApp {

	@Test
	public void userAddedWhenPostsFirstMessage() {
		final SocialApp app = new SocialApp();
		final CommandParser parser = new MockParser();
		parser.connectTo(app);

		final User alice = new User("Alice");
		final Message message = new Message("Testing 1 2 3");
		parser.postMessage(alice, message);

		assertThat(app.users(), contains(alice));
	}

}
