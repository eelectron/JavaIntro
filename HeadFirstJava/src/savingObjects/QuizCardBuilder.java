package savingObjects;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class QuizCardBuilder {
	JTextArea question;
	JTextArea answer;
	ArrayList<QuizCard> cardList;
	JFrame frame;
	
	/*
	 * Builds and display the GUI,including making and 
	 * registering events
	 * */
	public void go(){
		//buil gui
		frame = new JFrame("Quiz Card Builder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel=new JPanel();
		
		//create textarea
		JScrollPane qScroller =createTextAreaAndScrollPane(question, 6, 20);
		JScrollPane aScroller =createTextAreaAndScrollPane(answer, 6, 20);
		
		JButton nextButton =new JButton("Next Card");
		
		cardList = new ArrayList<QuizCard>();
		
		JLabel qLabel=new JLabel("Question");
		JLabel aLabel=new JLabel("Answer");
		
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem newMenuItem=new JMenuItem("New");
		JMenuItem saveMenuItem=new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(600, 500);
		frame.setVisible(true);
	}
	
	JScrollPane createTextAreaAndScrollPane(JTextArea ta, int row, int col){
		Font bigFont=new Font("sanserif",Font.BOLD,24);
		ta=new JTextArea(6,20);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setFont(bigFont);
		
		//Pass your textArea object to scroll pane object
		JScrollPane qScroller=new JScrollPane(ta);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return qScroller;
	}
	//Triggered when user hits 'Next Card'
	private class NextCardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			QuizCard card =new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	//Triggered when user Clicks SAVE in File menu
	private class SaveMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//adds last card to quiz list
			QuizCard card=new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			
			//It will ask user for a file where all cards will be saved
			JFileChooser fileSave=new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}
	
	private class NewMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cardList.clear();
			clearCard();
		}
	}
	
	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	//Called by SaveMenuListener class
	private void saveFile(File file){
		try{
			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
			for(QuizCard card: cardList){
				writer.write(card.getQuestion()+"/");
				writer.write(card.getAnswer()+"\n");
			}
			writer.close();
		}catch(IOException ex){
			System.out.println("Couldn't write the cardlist");
		}
	}
}
