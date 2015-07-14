import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class Screen extends JFrame  {

	//sets the window size in pixels.
	private static final int WIDTH = 600;
	private static final int HEIGHT = 550;
	//we will be logging answers in arrayList 
	static String question; //declare question
	String[] questionWords;
	StringBuilder buildURL = new StringBuilder;
	String viewURL;
	String userLink;
	
	//declaring GUI variables
	private static JLabel text;
	private JLabel imageField;
	private JFrame frame;
	ImageGetter getIt = new ImageGetter();
	ImageIcon img = getIt.getImage("magic.png");
	
	private static JButton startButton;
	
	public Screen() {
		//initPrompts();
		try {
			//check out JavaDocs on .setLookandFeel to find more custom managers for your GUI
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		text = new JLabel("Click the button to ask question.",SwingConstants.CENTER);
		
		imageField = new JLabel(img); //must place image within JLabel 
		
		startButton = new JButton("ASK THE MAGIC 8-BALL");

		//create ActionListeners for button ...
		//choice A actionListener
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				question = (String)JOptionPane.showInputDialog(frame, "Ask the Magical Ball your question.", "The Question", JOptionPane.PLAIN_MESSAGE);
				//creates a pane with the question and an action box
				
				//splits a sentence into separate words stored into an array
				//in order to filter non-alphanumeric characters and spaces
				questionWords = question.split("\\s+");
				
				//replace any non-alphabetical characters, since Google sanitizes them before searching.
				for (int i = 0; i < questionWords.length; i++) {
					questionWords[i] = questionWords[i].replaceAll("[^a-zA-Z0-9 ]", "");
				}
				
				//Creates the search parameters with the search after filter
				//Uses StringBuilder to append each word with a "+"
				for (String value : questionWords) {
					buildURL.append(value + "+");
				}
				
				//turns the StringBuilder into a String
				viewURL = buildURL.toString();
				//System.out.println(viewURL);
				
				//get user's main browser to open the link
				Desktop userDesktop = Desktop.getDesktop();
				//creates the link using viewURL and tacks on a LMGTFY link.
				userLink = ("http://lmgtfy.com/?q=" + viewURL);
				//System.out.println(userLink); (for debug purposes)
				
				try {
					userDesktop.browse(new URI(userLink));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		//layout GUI setup stuff.		
		JPanel masterPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		imageField.setAlignmentX(Component.CENTER_ALIGNMENT); //this centers our image... looks nice :)
		;
		
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
		masterPanel.add(imageField);
		
		buttonPanel.setLayout(new GridLayout (0,1)); //using grid layout manager, this frees us of worrying about aligning stuff
									//constructor = gridLayout(rows, columns)
		buttonPanel.add(text);
		buttonPanel.add(startButton);
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(masterPanel, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.PAGE_END);
	
		masterPanel.setVisible(true);
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Screen game = new Screen();
	}
}
