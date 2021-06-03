package tk.thesuperlab.jcon;

import tk.thesuperlab.jcon.util.CommandParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class is used to define JConsole window.
 * @author JurijTSL
 * @author znidi
 */
public class JConsole {
	private String windowTitle;

	private String currentHtml = "";
	private JFrame frame;
	private JEditorPane editorPane;
	private JScrollPane editorScrollPane;
	private JTextField commandLine;
	private String iconUrl;

	/**
	 * @param windowTitle - Title of console window
	 */
	public JConsole(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	/**
	 * @param windowTitle - Title of console window
	 * @param iconUrl - Location of icon
	 */
	public JConsole(String windowTitle, String iconUrl) {
		this.windowTitle = windowTitle;
		this.iconUrl = iconUrl;
	}

	/**
	 * Method opens console window.
	 */
	public void show() {
		frame = new JFrame(windowTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(iconUrl).getImage());

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");

		JPanel panel = new JPanel();

		commandLine = new JTextField();
		commandLine.setPreferredSize(new Dimension(700, 25));
		commandLine.setMinimumSize(new Dimension(700, 25));
		commandLine.setMaximumSize(new Dimension(700, 25));

		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(70, 25));
		send.setMinimumSize(new Dimension(70, 25));
		send.setMaximumSize(new Dimension(70, 25));

		panel.add(commandLine);
		panel.add(send);

		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeCommand(commandLine.getText());
			}
		});

		commandLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeCommand(commandLine.getText());
			}
		});

		editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.getContentPane().add(BorderLayout.NORTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, editorScrollPane);
		frame.setVisible(true);
	}

	/**
	 * Method calls registerCommand() function from CommandParser class.
	 * @param command - Command object
	 */
	protected void registerCommand(Command command) {
		CommandParser.registerCommand(command);
	}

	/**
	 * Method refreshes console text.
	 */
	private void refreshText() {
		editorPane.setText(currentHtml);
	}

	/**
	 * Method retrieves current time in specific format.
	 * @return String with time
	 */
	protected String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		return formatter.format(date);
	}

	/**
	 * Method executes and parses commands from input.
	 * @param text - Raw String from input
	 */
	private void executeCommand(String text) {
		if(!text.equals("")) {
			currentHtml += "<font color=\"#000000\">[" + getCurrentTime() + "] [USER]: " + text + "</font><br>";
			refreshText();

			commandLine.setText("");

			//Parse command and execute it
			ParsedCommand parsedCommand = CommandParser.parse(text);
			Command command = parsedCommand.getCommand();

			if(command == null) {
				addError("Command <b>" + text.split(" ")[0] + "</b> doesn't exist.");
			} else {
				command.execute(parsedCommand.getParams());
			}
		}
	}

	/**
	 * Method writes error to console
	 * @param text - Raw String that you want to write as error
	 */
	protected void addError(String text) {
		currentHtml += "<font color=\"#FF0000\">[" + getCurrentTime() + "] [System/Error]: " + text + "</font><br>";
		refreshText();
	}

	/**
	 * Method writes warning to console
	 * @param text - Raw String that you want to write as warning
	 */
	protected void addWarning(String text) {
		currentHtml += "<font color=\"#F2A53F\">[" + getCurrentTime() + "] [System/Warning]: " + text + "</font><br>";
		refreshText();
	}

	/**
	 * Method writes info to console
	 * @param text - Raw String that you want to write as info
	 */
	protected void addInfo(String text) {
		currentHtml += "<font color=\"#0000FF\">[" + getCurrentTime() + "] [System/Info]: " + text + "</font><br>";
		refreshText();
	}

	/**
	 * Method writes text to console
	 * @param text - Raw String that you want to write as text
	 */
	protected void addLog(String text) {
		currentHtml += "<font color=\"#000000\">[" + getCurrentTime() + "] [System/Log]: " + text + "</font><br>";
		refreshText();
	}

	/**
	 * Method clears console
	 */
	protected void clearConsole() {
		currentHtml = "";
		refreshText();
	}
}