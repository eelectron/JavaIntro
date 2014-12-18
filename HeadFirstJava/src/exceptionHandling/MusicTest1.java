package exceptionHandling;

import javax.sound.midi.*;

public class MusicTest1 {

	public void play(){
		try {
			Sequencer s=MidiSystem.getSequencer();
			System.out.println("we got a sequencer");
			
		} catch (MidiUnavailableException e) {
			System.out.println("Bummer");
		}
		
	}
	public static void main(String[] args) {
		MusicTest1 mt=new MusicTest1();
		mt.play();
	}

}
