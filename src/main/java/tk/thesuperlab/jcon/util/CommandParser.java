package tk.thesuperlab.jcon.util;

import tk.thesuperlab.jcon.Command;
import tk.thesuperlab.jcon.ParsedCommand;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class is used to parse command
 * @author JurijTSL
 */
public class CommandParser {
	protected static HashMap<String, Command> commands = new HashMap<String, Command>();

	/**
	 * Method registers command object.
	 * @param command - Command object
	 */
	public static void registerCommand(Command command) {
		commands.put(command.getCommand(), command);
	}

	/**
	 * Method parses command.
	 * @param text - Raw text to be parsed
	 * @return - ParsedCommand object
	 */
	public static ParsedCommand parse(String text) {
		String[] parts = text.split(" ");
		String[] params = Arrays.copyOfRange(parts, 1, parts.length);

		return new ParsedCommand(commands.get(parts[0]), params);
	}
}