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
		final User subscriber = alice;
		final User publisher = bob;

		final String subscriberMessage = "Hi I'm Alice.";
		final String publisherMessage = "I'm Bob.";
		app.postMessage(subscriber, new Message(subscriberMessage));
		app.postMessage(publisher, new Message(publisherMessage));

		app.follow(subscriber, publisher);
		app.wall(subscriber);

		assertThat(outContent.toString(),
				equalTo(subscriberMessage + "\n" + publisherMessage + "\n"));
	}

	@Test
	public void readsUsersTimeline() {
		app.postMessage(alice, new Message("Hi"));
		app.postMessage(alice, new Message("Bye"));

		app.readTimeline(alice);

		assertThat(outContent.toString(), equalTo("Hi\nBye\n"));
	}

	@Test
	public void noDuplicateUsernames() {
		final User alice2 = new User("Alice");
		app.postMessage(alice, new Message("something"));
		app.postMessage(alice2, new Message("something"));

		assertThat(app.users(), contains(alice));
	}

	@Test
	public void newUsersCreatedWhenFirstMessagePosted() {
		app.postMessage(alice, new Message("something"));
		app.postMessage(bob, new Message("something"));

		assertThat(app.users(), contains(alice, bob));
	}

}
