package lesson;

import com.gmail.alexflanker89.lesson1.dao.UserDaoSimple;
import com.gmail.alexflanker89.lesson1.domain.User;
import org.junit.Test;
import com.gmail.alexflanker89.lesson1.service.SimpleUserService;

/**
 *
 */
public class UserServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void UserServiceSaveTest(){
        UserDaoSimple daoSimple = new UserDaoSimple();
        User u1 = new User("S","R");
        User u2 = new User("T","B");
        daoSimple.save(u1);
        daoSimple.save(u2);
        SimpleUserService simpleUserService = new SimpleUserService(daoSimple);
        simpleUserService.save(u1);
        simpleUserService.save(u2);
    }
}
