import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.Thread;
import java.lang.InterruptedException;

public class MockAirQualityDataPublisher {

    public static void main(String[] args) {
        try {
            // Create an MQTT client
            MqttClient client = new MqttClient("tcp://broker.hivemq.com:1883", "MockAirQualityDataPublisher",
                    new MemoryPersistence());

            // Set MQTT connect options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            // Connect to the MQTT broker
            System.out.println("Connecting to broker: tcp://broker.hivemq.com:1883");
            client.connect(options);
            System.out.println("Connected");

            // Generate mock outdoor air quality data
            int outdoorData = MockAirQualityData.generateOutdoorData();

            // Publish the outdoor data to the "outdoor/airquality" topic
            byte[] payload = Integer.toString(outdoorData).getBytes();
            MqttMessage message = new MqttMessage(payload);
            message.setQos(1);
            client.publish("outdoor/AQI", message);
            System.out.println("Outdoor data published");

            int FirstRoomData = MockAirQualityData.generateOutdoorData();

            byte[] payload1 = Integer.toString(FirstRoomData).getBytes();
            MqttMessage message1 = new MqttMessage(payload1);
            message.setQos(1);
            client.publish("home/1/room/kitchen", message1);
            System.out.println("Kitchen data published");

            // Generate mock
            int SecondRoomData = MockAirQualityData.generateSecondRoomData();

            byte[] payload2 = Integer.toString(SecondRoomData).getBytes();
            MqttMessage message2 = new MqttMessage(payload2);
            message.setQos(1);
            client.publish("home/1/room/bathroom", message2);
            System.out.println("Bathroom data published");

            // Generate mock
            int ThirdRoomData = MockAirQualityData.generateThirdRoomData();

            byte[] payload3 = Integer.toString(ThirdRoomData).getBytes();
            MqttMessage message3 = new MqttMessage(payload3);
            message.setQos(1);
            client.publish("home/1/room/bedroom", message3);
            System.out.println("Bedroom data published");

            // Generate mock indoor air quality data
            int FourthRoomData = MockAirQualityData.generateFourthRoomData();

            byte[] payload4 = Integer.toString(FourthRoomData).getBytes();
            MqttMessage message4 = new MqttMessage(payload4);
            message.setQos(1);
            client.publish("home/1/room/hall", message4);
            System.out.println("Hall data published");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Handle the exception
            }

            // Disconnect from the MQTT broker
            // client.disconnect();
            // System.out.println("Disconnected");

        } catch (MqttException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
