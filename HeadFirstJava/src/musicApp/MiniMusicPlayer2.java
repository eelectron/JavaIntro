package musicApp;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMusicPlayer2 implements ControllerEventListener{
	public void play(){
		Sequencer player;
		try {
			player = MidiSystem.getSequencer();
			player.open();
			
			//Event source = player
			//Event listener = any class which implements ControllerEventListener
			int[] eventIWant = {127};
			player.addControllerEventListener(this, eventIWant);
			
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track = seq.createTrack();
			
			//write song on DVD or track
			for (int i = 10; i < 100; i +=4) {
				//start a note
				track.add(makeEvent(144,1,i,100,i-10));
				
				//this will trigger the event
				track.add(makeEvent(176, 1, 127, 0, i));
				
				//end a note
				track.add(makeEvent(128, 1, i, 100, i-10+2));
			}
			
			player.setSequence(seq);
			player.setTempoInBPM(220);
			player.start();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public void controlChange(ShortMessage event) {
		System.out.println("la");
		
	}
}
