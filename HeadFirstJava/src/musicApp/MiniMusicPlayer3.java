package musicApp;

import gui.MyDrawPanel;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JFrame;

public class MiniMusicPlayer3 {
	static JFrame f=new JFrame("My first video");
	static MyDrawPanel ml;
	static int width=300,height=300;
	
	public void setUpGui(){
		ml=new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30, 30, width, height);
		f.setVisible(true);
	}
	
	public void go(){
		setUpGui();
		
		Sequencer player;
		try {
			player = MidiSystem.getSequencer();
			player.open();
			
			//Event source = player
			//Event listener = any class which implements ControllerEventListener
			int[] eventIWant = {127};
			player.addControllerEventListener(ml, eventIWant);
			
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track = seq.createTrack();
			
			int r=0;
			//write song on DVD or track
			for (int i = 0; i < 100; i+=4) {
				r=(int)((Math.random()*50) +10);
				//start a note
				track.add(makeEvent(144,1,r,100,i));
				
				//this will trigger the event
				track.add(makeEvent(176, 1, 127, 0, i));
				
				//end a note
				track.add(makeEvent(128, 1, r, 100, i+3));
			}
			
			player.setSequence(seq);
			player.setTempoInBPM(120);
			player.start();
		} catch (Exception e){}
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
	
	
}
