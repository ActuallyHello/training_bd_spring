package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepoTest {

    //@Autowired
    //UserRepo userOfRepo;

    @Test
    public void testAddUser() {
//        User user = new User("userof_test2@mail.ru", "555", "USER");
//        userOfRepo.save(user);
        //userOfRepo.deleteByEmail("userof_test45@mail.ru");
    }

    @Test
    public void testDeleteUser() {
        //System.out.println(userOfRepo.findAllByEmail("userof_test45@mail.ru"));
        //userOfRepo.deleteByIdUser(102L);
        //userOfRepo.deleteByEmail("userof_test45@mail.ru");
        //userOfRepo.deleteByIdUserAndEmail(202L, "userof_test2@mail.ru");
    }

    @Test
    public void testFindBy() {
        //System.out.println(userOfRepo.findAllByEmail("userof_test3@mail.ru"));
        //System.out.println(userOfRepo.findEmailByIdUser(252L));
    }
}