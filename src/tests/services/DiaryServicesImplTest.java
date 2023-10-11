package tests.services;

import data.model.Entry;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryServicesImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServicesImplTest {

    private DiaryServicesImpl diaryServices;
    private RegisterUserRequest registerUserRequest;
    private LogInRequest logInRequest;

    @BeforeEach
    public void setUp(){

        diaryServices = new DiaryServicesImpl();

        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");

        logInRequest = new LogInRequest();
        logInRequest.setPassword("password");
        logInRequest.setUsername("Username");
    }
    @Test
    public void testThatRegisterIncreasesCount(){

        diaryServices.register(registerUserRequest);
        assertEquals(1, diaryServices.count());

    }

    @Test
    public void testThatAUsernameCannotBeRegisteredTwice_andCountRemainUnchanged(){

        diaryServices.register(registerUserRequest);

        assertThrows(IllegalArgumentException.class,
                ()->diaryServices.register(registerUserRequest));
        assertEquals(1, diaryServices.count());

    }

    @Test
    public void testThatRegisteredUserIsSaved(){
        diaryServices.register(registerUserRequest);
        assertEquals("Username", diaryServices.findByUsername("Username").getUsername());
    }

    @Test
    public void testThatSearchingUnsavedUsername_throwsException(){
        diaryServices.register(registerUserRequest);
        assertThrows(IllegalArgumentException.class,
                ()->diaryServices.findByUsername("User"));
    }
    @Test
    public void testThatAUserCanDeleteDiary(){
        diaryServices.register(registerUserRequest);
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        diaryServices.register(registerUserRequest1);

        assertEquals(2, diaryServices.count());

        diaryServices.delete("Username", "password");
        assertEquals(1, diaryServices.count());

    }

    @Test
    public void testThatSearchingADeletedDiary_throwsException(){
        diaryServices.register(registerUserRequest);
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        diaryServices.register(registerUserRequest1);
        assertEquals(2, diaryServices.count());

        diaryServices.delete("Username", "password");
        assertThrows(NullPointerException.class, ()->diaryServices.findByUsername(registerUserRequest.getUsername()));

    }

    @Test
    public void testThatDeletingADiary_WithAWrongPassword_throwsException(){
        diaryServices.register(registerUserRequest);
        assertThrows(IllegalArgumentException.class,
                ()->diaryServices.delete("Username", "pass"));
    }

    @Test
    public void testThatUserCanChangePassword(){
        diaryServices.register(registerUserRequest);
        diaryServices.update("Username", "password", "newPassword");

        assertThrows(IllegalArgumentException.class,
                ()->diaryServices.delete("Username", "password"));
        assertEquals(1, diaryServices.count());
    }

    @Test
    public void testThatUserCanNotChangePasswordWithIncorrectOldPassword(){
        diaryServices.register(registerUserRequest);

        assertThrows(IllegalArgumentException.class,
                ()->diaryServices.update(
                        "Username",
                        "oldPassword",
                        "newPassword")
        );
    }

    @Test
    public void testThatEntryCanBeAddedToTheDairy(){
        diaryServices.register(registerUserRequest);
        diaryServices.addEntry("Username", "title", "body");

        Entry entry = diaryServices.findEntry("Username", "title");
        assertEquals("body", entry.getBody());

    }

    @Test
    public void testThatAddEntryThrowsExceptionIfDiaryDoesNotExist(){
        assertThrows(IllegalArgumentException.class,
                ()-> diaryServices.addEntry("Username", "title", "body"));
    }

    @Test
    public void testThatAddEntryThrowsExceptionIfDiaryIsLocked(){
        diaryServices.register(registerUserRequest);
        diaryServices.lock("Username");

        assertThrows(IllegalArgumentException.class,
                ()-> diaryServices.addEntry("Username", "title", "body"));
    }

    @Test
    public void testThatLockedDiaryCanBeUnlockedToAddEntry(){
        diaryServices.register(registerUserRequest);
        diaryServices.lock("Username");

        assertThrows(IllegalArgumentException.class,
                ()-> diaryServices.addEntry("Username", "title", "body"));

        diaryServices.unlock(logInRequest);
        diaryServices.addEntry("Username", "title", "body");
        Entry entry = diaryServices.findEntry("Username", "title");
        assertEquals("body", entry.getBody());


    }

    @Test
    public void testThatDiaryIsStillLocked_andThrowsExceptionIfUnlockPasswordIsIncorrect(){
        diaryServices.register(registerUserRequest);
        diaryServices.lock("Username");

        LogInRequest logInRequest1 = new LogInRequest();
        assertThrows(IllegalArgumentException.class,
                ()-> diaryServices.unlock(logInRequest1));
    }

}