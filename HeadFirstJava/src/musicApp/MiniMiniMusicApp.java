package musicApp;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

//MINI MUSIC APPLICATION
public class MiniMiniMusicApp {
	//Start playing music
	public void play(){
		try
		{		
		Sequencer player =MidiSystem.getSequencer();
		player.open();
		
		Sequence seq = new Sequence(Sequence.PPQ, 4);
		//ask the sequence for a track
		Track track=seq.createTrack();
									
		//make a bunch of notes to keep piano go up from 5 to 61
		for (int i = 5; i < 100; i += 4) {
			track.add(makeEvent(144,1,i,100,i));
			track.add(makeEvent(128,1,i,100,i+2));
		}
		
		//give the sequence to the sequencer
		player.setSequence(seq);
		player.setTempoInBPM(220);
		
		//play the music
		player.start();
		}catch(Exception ex){
			ex.printStackTrace();
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
}
