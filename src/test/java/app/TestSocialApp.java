package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

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
		final Message subscriberMessage = new Message("Hi I'm Alice.");
		final Message publisherMessage = new Message("I'm Bob.");
		app.postMessage("Alice", subscriberMessage);
		app.postMessage("Bob", publisherMessage);

		app.follow("Alice", "Bob");
		app.readWall("Alice");

		assertThat(app.readWall("Alice"), contains(subscriberMessage, publisherMessage));
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
