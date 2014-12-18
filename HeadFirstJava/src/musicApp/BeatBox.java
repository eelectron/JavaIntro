package musicApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BeatBox {
	JFrame theFrame;
	JPanel mainPanel;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	ArrayList<JCheckBox> checkBoxList;
	
	String[] instrumentsNames = {"Bass Drum","Closed Hi Hat","Open Hi Hat","Acoustic ","Crash Cymbal"
									,"Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low Congo",
									"CowBell","Vibraslap","Low mid tom", "High Agogo", "Open Hi Conga"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	public static void main(String[] args) {
		new BeatBox().buildGUI();
	}
	
	public void buildGUI(){
		theFrame = new JFrame("Cyber BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		
		//creates margin
		background.setBorder(BorderFactory.createEmptyBorder());
		
		checkBoxList = new ArrayList<JCheckBox>();
		
		//BUTTON BOX
		Box buttonBox=new Box(BoxLayout.Y_AXIS);
		
		JButton start=new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop=new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo=new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo=new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		JButton save=new JButton("Save");
		save.addActionListener(new MySendListener());
		buttonBox.add(save);
		
		JButton restore=new JButton("Restore");
		restore.addActionListener(new MyReadInsListener());
		buttonBox.add(restore);
		
		//BOX  Layout for label
		Box nameBox=new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			nameBox.add(new Label(instrumentsNames[i]));
		}
		
		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
		
		theFrame.getContentPane().add(background);
		
		GridLayout grid=new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER,mainPanel);
		
		//add CHECKBOX to mainPanel
		for (int i = 0; i < 256; i++) {
			JCheckBox c=new JCheckBox();
			c.setSelected(false);
			checkBoxList.add(c);
			mainPanel.add(c);
		}
		
		//SETUP MIDI
		setUpMidi();
		
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	public void setUpMidi(){
		try{
			sequencer=MidiSystem.getSequencer();
			sequencer.open();
			sequence=new Sequence(Sequence.PPQ, 4);
			track=sequence.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex){}
	}
	
	public void buildTrackAndStart(){
		int[] trackList = null;
		
		sequence.deleteTrack(track);
		track=sequence.createTrack();
		
		for (int i = 0; i < 16; i++) {
			trackList = new int[16];
			
			int key = instruments[i];
			
			for (int j = 0; j < 16; j++) {
				JCheckBox jc=(JCheckBox)checkBoxList.get(j + i*16);
				if(jc.isSelected())
					trackList[j]=key;
				else
					trackList[j]=0;
			}
			
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			
		}
		
		track.add(makeEvent(192,9,1,0,15));
		try{
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex){}
	}
	
	//Triggered when START button is clicked.....................
	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buildTrackAndStart();
		}
	}
	
	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			sequencer.stop();
		}
	}
	
	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*1.03));
			
		}
	}
	
	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor*.97));
			
		}
	}
	
	public void makeTracks(int[] list){
		for (int i = 0; i < 16; i++) {
			int key = list[i];
			if(key != 0){
				
				//NOTE ON 144 event
				track.add(makeEvent(144,9,key,100,i));
				
				//NOTE OFF 128 event
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}
	
	public  MidiEvent makeEvent(int comd, int chan, int one, int two,int tick){
		MidiEvent event=null;
		try{
			ShortMessage a=new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event=new MidiEvent(a, tick);
		}catch(Exception e){}
		return event;
	}
	
	public class MySendListener  implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Save ur beat pattern in array
			boolean[] checkBoxState=new boolean[256];
			for(int i=0; i<256; i++){
				JCheckBox check=(JCheckBox)checkBoxList.get(i);
				if(check.isSelected())
					checkBoxState[i]=true;
			}
			
			//Store that array in file
			JFileChooser fileSave=new JFileChooser();
			fileSave.showOpenDialog(theFrame);
			File file = fileSave.getSelectedFile();
			
			//serialize it
			try {
				ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(file));
				os.writeObject(checkBoxState);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
		}
	}
	
	public class MyReadInsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean[] checkBoxState=null;
			
			//First read boolean array object from file
			JFileChooser fileOpen=new JFileChooser();
			fileOpen.showOpenDialog(theFrame);
			File file=fileOpen.getSelectedFile();
			
			//deserialize the file
			try {
				FileInputStream fs=new FileInputStream(file);
				ObjectInputStream os=new ObjectInputStream(fs);
				checkBoxState=(boolean[])os.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//Then reflect it in Music App
			for (int i = 0; i < checkBoxState.length; i++) {
				JCheckBox check=checkBoxList.get(i);
				if(checkBoxState[i])
					check.setSelected(true);     
				else
					check.setSelected(false);
			}
			
			//stop whatever is playing
			sequencer.stop();
			buildTrackAndStart();
		}
	}
}
