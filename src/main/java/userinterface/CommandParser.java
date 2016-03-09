package userinterface;

import app.NetworkingApp;

public interface CommandParser {

	boolean recognises(String command);

	void process(String command, NetworkingApp app);

}
