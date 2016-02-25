package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.Test;

public class TestSocialApp {

	@Test
	public void noDuplicateUsernames() {
		final SocialApp app = new SocialApp();

		final User alice = new User("Alice");
		final User alice2 = new User("Alice");
		app.postMessage(alice, new Message("something"));
		app.postMessage(alice2, new Message("something"));

		assertThat(app.users(), contains(alice));
	}

	@Test
	public void newUsersCreatedWhenFirstMessagePosted() {
		final SocialApp app = new SocialApp();

		final User alice = new User("Alice");
		final User bob = new User("Bob");
		app.postMessage(alice, new Message("something"));
		app.postMessage(bob, new Message("something"));

		assertThat(app.users(), contains(alice, bob));
	}

}
