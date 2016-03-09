package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSocialApp {

	private final SocialApp app = new SocialApp();
	private final User alice = new User("Alice");
	private final User bob = new User("Bob");
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

	@Test
	public void subscriberReceivesPublishersPostsToTimelineWhenFollowing() {
		final String subscriberMessage = "Hi I'm Alice.";
		final String publisherMessage = "I'm Bob.";
		app.postMessage("Alice", new Message(subscriberMessage));
		app.postMessage("Bob", new Message(publisherMessage));

		app.follow("Alice", "Bob");
		app.wall("Alice");

		assertThat(outContent.toString(),
				equalTo(subscriberMessage + "\n" + publisherMessage + "\n"));
	}

	@Test
	public void readsUsersTimeline() {
		app.postMessage("Alice", new Message("Hi"));
		app.postMessage("Alice", new Message("Bye"));

		app.readTimeline("Alice");

		assertThat(outContent.toString(), equalTo("Hi\nBye\n"));
	}

	@Test
	public void noDuplicateUsernames() {
		app.postMessage("Alice", new Message("something"));
		app.postMessage("Alice", new Message("something"));

		assertThat(app.users(), contains(alice));
	}

	@Test
	public void newUsersCreatedWhenFirstMessagePosted() {
		app.postMessage("Alice", new Message("something"));
		app.postMessage("Bob", new Message("something"));

		assertThat(app.users(), contains(alice, bob));
	}

}
