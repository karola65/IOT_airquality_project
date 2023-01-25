package smart.air.quality.control.actuators;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public abstract class Actuator implements MqttCallback {
	static final String serverURI = "tcp://localhost:1883";

	MqttClient client;
	final int home_id;
	final String room_id;
	final int actuator_id;

	public Actuator(int home_id, String room_id, int actuator_id) {
		try {
			String clientId = Integer.toString(actuator_id);
			MemoryPersistence persistence = new MemoryPersistence();
			this.client = new MqttClient(serverURI, clientId, persistence);
			this.client.setCallback(this);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		this.home_id = home_id;
		this.room_id = room_id;
		this.actuator_id = actuator_id;
	}

	public void setupConnection() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setUserName("admin");
		options.setPassword("1234".toCharArray());
		options.setKeepAliveInterval(480);
		String message = String.format("Actuator %d: ", this.actuator_id) + "Connection established with server";
		options.setWill(client.getTopic("ConnectionTopic"), message.getBytes(), 2, true);
		try {
			this.client.connect(options);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public abstract void subscribeToTopic();

	@Override
	public void connectionLost(Throwable arg0) {
		String message = String.format("Actuator %d: ", this.actuator_id) + "Connection lost with server";
		System.out.println(message);
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("The delivery has been complete. The delivery token is " + arg0.toString());
	}
	
	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		System.out.println("A new message arrived from the topic: \"" + arg0 + "\". The payload of the message is " + message.toString());
	}
	
	
	
	
}


