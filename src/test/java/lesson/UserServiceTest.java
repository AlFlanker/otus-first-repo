package lesson;

import com.gmail.alexflanker89.lesson.dao.UserDaoSimple;
import com.gmail.alexflanker89.lesson.domain.User;
import com.gmail.alexflanker89.lesson.exceptions.UserAlreadyExistException;
import com.gmail.alexflanker89.lesson.service.SimpleUserService;
import org.junit.Test;

/**
 *
 */
public class UserServiceTest {

    @Test(expected = UserAlreadyExistException.class)
    public void UserServiceDublicateTest(){
        UserDaoSimple daoSimple = new UserDaoSimple();
        User u1 = new User("S","R");
        User u2 = new User("T","B");
        daoSimple.save(u1);
        daoSimple.save(u2);
        SimpleUserService simpleUserService = new SimpleUserService(daoSimple);
        simpleUserService.save(u1);
        simpleUserService.save(u2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UserServiceSaveEmptyTest(){
        UserDaoSimple daoSimple = new UserDaoSimple();
        User u1 = new User("","R");
        daoSimple.save(u1);
        SimpleUserService simpleUserService = new SimpleUserService(daoSimple);
        simpleUserService.save(u1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UserServiceSaveNullTest(){
        UserDaoSimple daoSimple = new UserDaoSimple();
        User u1 = new User(null,"R");
        daoSimple.save(u1);
        SimpleUserService simpleUserService = new SimpleUserService(daoSimple);
        simpleUserService.save(u1);
    }
}
