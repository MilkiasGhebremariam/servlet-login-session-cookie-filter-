import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class UserData {

    public static HashMap<Integer, UserD> usersPassword = new HashMap<Integer, UserD>();



    private static int count = 0;
    private static boolean isLoggedIn = false;
    public static void addUsers() {

        UserD user1 = new UserD("Bob", "123456");
        UserD user2 = new UserD("SARA", "654321");
        UserD user3 = new UserD("CHARLES", "89765");
        UserD user4 = new UserD("FEVEN", "5678956");

        add(user1);
        add(user2);
        add(user3);
        add(user4);

    }

    private static void add(UserD user) {
        usersPassword.put(count, user);
        count++;
    }

    public static UserD checkUser(String username, String password) {
        Iterator userPasswords = usersPassword.entrySet().iterator();
        while (userPasswords.hasNext()) {
            Entry<Integer, UserD> mappairs = (Entry<Integer, UserD>) userPasswords.next();
            if (username.equals(mappairs.getValue().getName())
                    && password.equals(mappairs.getValue().getPassword())) {
                isLoggedIn = true;
                return mappairs.getValue();

            }

        }
        return null;
    }
    public static boolean hasLoggedIn()
    {
        return isLoggedIn;
    }

}
