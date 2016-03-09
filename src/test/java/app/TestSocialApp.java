package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.Test;

public class TestSocialApp {

	private final SocialApp app = new SocialApp();

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

		assertThat(app.readTimeline(username), contains(secondMessage, firstMessage));
	}

}
