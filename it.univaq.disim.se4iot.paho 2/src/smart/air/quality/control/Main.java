package smart.air.quality.control;

import java.util.ArrayList;

import smart.air.quality.control.actuators.*;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<Actuator> actuators = new ArrayList<Actuator>();
		
		actuators.add(new Window(1, "kitchen", 100));
		actuators.add(new Window(1, "bedroom", 101));
		actuators.add(new Purifier(1, "kitchen", 200));
		actuators.add(new Purifier(1, "bedroom", 202));
		actuators.add(new Window(1, "bathroom", 110));
		actuators.add(new Purifier(1, "bathroom", 201));
		
		actuators.add(new Window(1, "hall", 111));
		actuators.add(new Purifier(1, "hall", 220));
		
		for (Actuator a: actuators) {
			a.setupConnection();
			a.subscribeToTopic();
		}
	}
}