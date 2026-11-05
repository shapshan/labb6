package registration;

import java.util.ArrayList;

public class RegistrationManager {
    public static final String[] EVENTS = {
            "Web Design Workshop",
            "JavaScript Quiz",
            "IT Career Talk"
    };

    private final ArrayList<Registration> registrations = new ArrayList<>();
    private int nextId = 1;

    public static String validateName(String name) {
        if (name == null || name.trim().length() < 3) {
            return "Full name must contain at least 3 characters.";
        }
        return null;
    }

    public static String validateEmail(String email) {
        if (email == null) {
            return "Email must not be empty.";
        }
        String e = email.trim();
        int at = e.indexOf('@');
        if (at <= 0 || at == e.length() - 1) {
            return "Email must contain '@' with text before and after it.";
        }
        return null;
    }

    public static String validateGroup(String group) {
        if (group == null || group.trim().isEmpty()) {
            return "Student group must not be blank.";
        }
        return null;
    }

    public static String validateEventNumber(int number) {
        if (number < 1 || number > EVENTS.length) {
            return "Event number must be from 1 to " + EVENTS.length + ".";
        }
        return null;
    }

        public int addRegistration(String fullName, String email,
                               String studentGroup, int eventNumber) {
        if (validateName(fullName) != null
                || validateEmail(email) != null
                || validateGroup(studentGroup) != null
                || validateEventNumber(eventNumber) != null) {
            return -1;
        }
        int id = nextId++;
        registrations.add(new Registration(id, fullName.trim(), email.trim(),
                studentGroup.trim(), EVENTS[eventNumber - 1]));
        return id;
    }

    public void displayAll() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations yet.");
            return;
        }
        for (Registration r : registrations) {
            System.out.println(r);
        }
    }

    public Registration findById(int id) {
        for (Registration r : registrations) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getId() == id) {
                registrations.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayByEvent(int eventNumber) {
        String title = EVENTS[eventNumber - 1];
        boolean found = false;
        for (Registration r : registrations) {
            if (r.getEventTitle().equals(title)) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No registrations for \"" + title + "\".");
        }
    }
}
