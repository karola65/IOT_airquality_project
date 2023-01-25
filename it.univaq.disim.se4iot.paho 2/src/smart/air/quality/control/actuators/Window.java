package smart.air.quality.control.actuators;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Window extends Actuator{

	public Window(int home_id, String room_id, int actuator_id) {
		super(home_id, room_id, actuator_id);
	}

	@Override
	public void subscribeToTopic() {
		String topic = "home/" + this.home_id + "/room/" + this.room_id + "/window/" + this.actuator_id;
		try {
			this.client.subscribe(topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		super.messageArrived(arg0, message);
		
		String prefix = String.format("Window %d: ", this.actuator_id);
		if (message.toString().compareTo("open") == 0) {
			System.out.println(prefix + "Instruction to open received");
		} else if (message.toString().compareTo("close") == 0) {
			System.out.println(prefix + "Instruction to close received");
		} else {
			System.out.println(prefix + "Unknown instruction received");
		}
	}
	
}