package simpleRMI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MyDrawPanel;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MiniMusicService implements Service{
	MyDrawPanel myPanel;
	
	@Override
	public JPanel getGuiPanel() {
		JPanel mainPanel=new JPanel();
		myPanel=new MyDrawPanel();
		JButton playItButton=new JButton("play it");
		playItButton.addActionListener(new PlayItListener());
		mainPanel.add(myPanel);
		mainPanel.add(playItButton);
		return null;
	}
	
	public class PlayItListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			try {
				//sequencer is like DVD player
				Sequencer sequencer=MidiSystem.getSequencer();
				sequencer.open();
				sequencer.addControllerEventListener(myPanel, new int[]{127});
				
				Sequence seq=new Sequence(Sequence.PPQ, 4);					//This our DVD
				Track track=seq.createTrack();
				
				for (int i = 0; i < 100; i++) {
					int r=(int)(Math.random()*50);
					if(r>38){		//so now only do it if r<38(75% of time)
						track.add(makeEvent(144,1,r,100,i));
						track.add(makeEvent(176,1,127,0,i));
						track.add(makeEvent(128,1,r,100,i+2));
					}
				}
				
				sequencer.setSequence(seq);
				sequencer.start();
				sequencer.setTempoInBPM(220);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
			
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		ShortMessage a =new ShortMessage();
		try {
			a.setMessage(comd, chan, one, two);
			event=new MidiEvent(a, tick);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	class MyDrawPanel extends JPanel implements ControllerEventListener{
		//do we want to paint if we got an event
		boolean msg=false;
		
		public void controlChange(ShortMessage event){
			msg=true;
			repaint();
		}
		
		public Dimension getPreferredSize(){
			return new Dimension(300, 300);
		}
		
		public void paintComponent(Graphics g){
			if(msg){
				int r=(int)(Math.random()*255);			//get random paint for your brush
				int gr=(int)(Math.random()*255);
				int b=(int)(Math.random()*255);
				
				g.setColor(new Color(r,gr,b));			//set it on paintbrush
				
				int wd=(int)(Math.random()*120 +10);	//width of rectangle
				int ht=(int)(Math.random()*120 +10);
				
				int x=(int)(Math.random()*40);
				int y=(int)(Math.random()*40);
				
				g.fillRect(x, y, wd, ht);
				msg=false;
			}
		}
	}
}
