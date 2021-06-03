package tk.thesuperlab.jcon;

/**
 * Class for Command and params combination object
 */
public class ParsedCommand {
	private Command command;
	private String[] params;

	/**
	 * @param command - Command object
	 * @param params - Parameters
	 */
	public ParsedCommand(Command command, String[] params) {
		this.command = command;
		this.params = params;
	}

	/**
	 * Getter for command
	 * @return Command that was parsed
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Getter for parameters
	 * @return String[] with parameters
	 */
	public String[] getParams() {
		return params;
	}
}