import java.util.Random;

public class MockAirQualityData {

    // Generate mock outdoor air quality data
    public static int generateOutdoorData() {
        Random rand = new Random();
        return rand.nextInt(501); // generate a random value between 0 and 500
    }

    // Generate mock indoor air quality data
    public static int generateFirstRoomData() {
        Random rand = new Random();
        return rand.nextInt(501); // generate a random value between 0 and 500
    }

    public static int generateSecondRoomData() {
        Random rand = new Random();
        return rand.nextInt(501); // generate a random value between 0 and 500
    }

    public static int generateThirdRoomData() {
        Random rand = new Random();
        return rand.nextInt(501); // generate a random value between 0 and 500
    }

    public static int generateFourthRoomData() {
        Random rand = new Random();
        return rand.nextInt(501); // generate a random value between 0 and 500
    }

    public static void main(String[] args) {
        int outdoorData = generateOutdoorData();
        System.out.println("Outdoor air quality data:");
        System.out.println(outdoorData);

        int FirstRoomData = generateFirstRoomData();
        System.out.println("Kitchen air quality data:");
        System.out.println(FirstRoomData);

        int SecondRoomData = generateSecondRoomData();
        System.out.println("Bathroom air quality data:");
        System.out.println(SecondRoomData);

        int ThirdRoomData = generateThirdRoomData();
        System.out.println("Bedroom air quality data:");
        System.out.println(ThirdRoomData);

        int FourthRoomData = generateFourthRoomData();
        System.out.println("Hall air quality data:");
        System.out.println(FourthRoomData);
    }
}
