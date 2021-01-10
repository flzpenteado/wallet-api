package com.wallet.repository;

import com.wallet.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    private static final String EMAIL = "johndoe@mail.com";

    @Autowired
    UserRepository repository;

    @Before
    public void setUp() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail(EMAIL);
        user.setPassword("mysecret");

        repository.save(user);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@doe.com");
        user.setPassword("myscret");

        User response = repository.save(user);

        assertNotNull(response);
    }

    @Test
    public void testFindByEmail() {
        Optional<User> response = repository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());

        assertEquals(response.get().getEmail(), EMAIL);
    }
}
