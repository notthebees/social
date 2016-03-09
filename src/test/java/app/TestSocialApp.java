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
		final String username = "Alice";
		final Message firstMessage = new Message("Hi");
		final Message secondMessage = new Message("Bye");
		app.postMessage(username, firstMessage);
		app.postMessage(username, secondMessage);

		app.readTimeline(username);

		assertThat(app.readTimeline(username), contains(firstMessage, secondMessage));
	}

}
