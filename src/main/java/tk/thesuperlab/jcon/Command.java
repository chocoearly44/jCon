package tk.thesuperlab.jcon;

/**
 * Class for creating command
 *
 * @author JurijTSL
 */
public abstract class Command {
	private String command;
	private JConsole jcon;

	/**
	 * @param command - Name of command
	 * @param jcon    - JConsole instance that you want to register command to
	 */
	public Command(String command, JConsole jcon) {
		this.command = command;
		this.jcon = jcon;

		jcon.registerCommand(this);
	}

	/**
	 * Abstract method for executing command
	 *
	 * @param params - All parameters
	 */
	public abstract void execute(String[] params);

	/**
	 * Method returns command name
	 * @return String command name
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Method writes error to console
	 * @param text - Raw String that you want to write as error
	 */
	public void addError(String text) {
		jcon.addError(text);
	}

	/**
	 * Method writes warning to console
	 * @param text - Raw String that you want to write as warning
	 */
	public void addWarning(String text) {
		jcon.addWarning(text);
	}

	/**
	 * Method writes info to console
	 * @param text - Raw String that you want to write as info
	 */
	public void addInfo(String text) {
		jcon.addInfo(text);
	}

	/**
	 * Method writes text to console
	 * @param text - Raw String that you want to write as text
	 */
	public void addLog(String text) {
		jcon.addLog(text);
	}

	/**
	 * Method clears console
	 */
	public void clearConsole() {
		jcon.clearConsole();
	}
}
