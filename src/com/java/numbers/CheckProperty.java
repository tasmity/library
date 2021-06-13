package numbers;

import java.util.*;

public class CheckProperty {
    static Map<String, String> couple = new HashMap<>();

    // Set value pairs that cannot match
    static {
        couple.put("even", "odd");
        couple.put("square", "sunny");
        couple.put("spy", "duck");
        couple.put("happy", "sad");
    }
    static Set<String> notProperties = new HashSet<>();
    static Set<String> validProperties = new HashSet<>();

    // Following the recommendations of Sonar, we set up a non-public constructor of the functional class
    CheckProperty() {
    }

    // Create fixed lines of output
    static final String VALUE = "-buzz -duck -palindromic -gapful -spy -square -sunny -jumping -sad -happy -even -odd";
    static final String NOTVALUES = "Available properties:%n+" +
            "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]%n";
    static final String NOTVALUE = "The property [%s] is wrong.%n" + NOTVALUES;
    static final String VALUEISVALUE = "The request contains mutually exclusive properties: [%s, %s]%n" +
            "There are no numbers with these properties.%n";

    //Determine the correspondence of the string to possible values
    static boolean checkIn(ArrayList<String> properties) {
        if (!properties.get(0).matches("-?\\d+") || Long.parseLong(properties.get(0)) < 0) {
            System.out.println("\nThe first parameter should be a natural number or zero.");
            return false;
        } else if (properties.size() > 1 && (!properties.get(1).matches("-?\\d+") ||
                Long.parseLong(properties.get(1)) < 0)) {
            System.out.println("\nThe second parameter should be a natural number.\n");
            return false;
        } else if (properties.size() == 3 && !VALUE.contains(properties.get(2).toLowerCase())) {
            System.out.printf(NOTVALUE, properties.get(2).toUpperCase());
            return false;
        } else if (properties.size() >= 3) {
            createNotProperty(properties);
            return checkProperty(properties);
        }
        return true;
    }

    // Create a set of values with and without output
    static void createNotProperty(ArrayList<String> properties) {
        notProperties.clear();
        validProperties.clear();
        for (String s: properties)
            if (!s.equals(properties.get(0)) && !s.equals(properties.get(1))) {
                if (s.startsWith("-"))
                    notProperties.add(s.toLowerCase().substring(1));
                else
                    validProperties.add(s.toLowerCase());
            }
    }

    // Counting and Displaying Invalid Values
    static boolean checkProperty(ArrayList<String> properties) {
        ArrayList<String> notProperty = new ArrayList<>();
        for (var i = 2; i < properties.size(); i++) {
            if (!VALUE.contains(properties.get(i).toLowerCase())) {
                notProperty.add(properties.get(i).toUpperCase());
            }
        }
        if (!notProperty.isEmpty()) {
            if (notProperty.size() == 1) {
                System.out.printf(NOTVALUE, notProperty.get(0));
            } else {
                System.out.println("The properties " + Arrays.toString(notProperty.toArray()) + " are wrong.\n" +
                        NOTVALUES);
            }
            return false;
        }
        return checkValidCouple(properties);
    }

    // Find values if they are present in the output set and not in the output set
    static boolean checkValidOpposites(ArrayList<String> properties) {
        for (String s : properties) {
            if (validProperties.toString().contains(s.toLowerCase()) &&
                    notProperties.toString().contains(s.toLowerCase())) {
                System.out.printf(VALUEISVALUE, s.toUpperCase(), "-" + s.toUpperCase());
                return false;
            }
        }
        return true;
    }

    // Finding impossible pairs of values
    static boolean checkValidCouple(ArrayList<String> properties) {
        for (Map.Entry<String, String> map : couple.entrySet()) {
            if (validProperties.toString().contains(map.getKey()) && validProperties.toString().contains(map.getValue())) {
                System.out.printf(VALUEISVALUE, map.getKey().toUpperCase(), map.getValue().toUpperCase());
                return false;
            }
            if (notProperties.toString().contains(map.getKey()) && notProperties.toString().contains(map.getValue()) &&
                    !map.getKey().equals("spy") && !map.getKey().equals("square")) {
                System.out.printf(VALUEISVALUE, "-" + map.getKey().toUpperCase(), "-" + map.getValue().toUpperCase());
                return false;
            }
        }
        return checkValidOpposites(properties);
    }
}