package smart.air.quality.control.actuators;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Purifier extends Actuator{

	public Purifier(int home_id, String room_id, int actuator_id) {
		super(home_id, room_id, actuator_id);
	}

	@Override
	public void subscribeToTopic() {
		String topic = "home/" + this.home_id + "/room/" + this.room_id + "/purifier/" + this.actuator_id;
		try {
			this.client.subscribe(topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		super.messageArrived(arg0, message);
		
		String prefix = String.format("Purifier %d: ", this.actuator_id);
		if (message.toString().compareTo("on") == 0) {
			System.out.println(prefix + "Instruction to turn on received");
		} else if (message.toString().compareTo("off") == 0) {
			System.out.println(prefix + "Instruction to turn off received");
		} else {
			System.out.println(prefix + "Unknown instruction received");
		}
	}
	
}