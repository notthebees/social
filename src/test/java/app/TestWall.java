package app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TestWall {

	@Test
	public void mergingWallsPreservesOrderOfPosts() {
		final Message message1 = new Message("1", "Alice");
		pause();
		final Message message2 = new Message("2", "Bob");
		pause();
		final Message message3 = new Message("3", "Bob");
		pause();
		final Message message4 = new Message("4", "Alice");
		final Wall aliceWall = new Wall(message4, message1);
		final Wall bobWall = new Wall(message3, message2);

		assertThat(aliceWall.mergeWith(bobWall),
				equalTo(new Wall(message4, message3, message2, message1)));
	}

	private void pause() {
		try {
		    Thread.sleep(5);
		} catch(final InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}

}
