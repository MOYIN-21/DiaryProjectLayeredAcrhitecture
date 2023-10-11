package tests.services.controller;

import controller.DiaryController;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryControllerTest {

    private DiaryController diaryController;
    private RegisterUserRequest registerUserRequest;
    private LogInRequest logInRequest;
    @BeforeEach
    public void setUp(){
        diaryController = new DiaryController();

        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Username");
        registerUserRequest.setPassword("password");

        logInRequest = new LogInRequest();
        logInRequest.setUsername("Username");
        logInRequest.setPassword("password");
    }
    @Test
    public void testThatDiaryCanBeRegistered(){
        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);
    }

    @Test
    public void testThatDairyUsernameIsUnique_(){
        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);

        String reRegisterOutput = diaryController.register(registerUserRequest);
        assertEquals("Username Already Exist", reRegisterOutput);
    }

    @Test
    public void testThatDiaryCanBeLocked_andCreateEntryCannotCreateEntryWhenUserCreateEntry(){
        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);

        String lockedOutput = diaryController.lock("Username");
        assertEquals("Locked", lockedOutput);

        String createOutput = diaryController.createEntry("Username", "title", "entry body");
        assertEquals("Diary is Locked", createOutput);


    }

    @Test
    public void testThatDiaryCanBeUnlocked_andEntryCanBeAdded(){
        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);

        String lockedOutput = diaryController.lock("Username");
        assertEquals("Locked", lockedOutput);

        String createOutput = diaryController.createEntry("Username", "title", "entry body");
        assertEquals("Diary is Locked", createOutput);

        String unlockedOutput = diaryController.unlock(logInRequest);
        assertEquals("Diary Unlocked", unlockedOutput);

        String anotherCreatedOutput = diaryController.createEntry("Username", "title", "body");
        assertEquals("Entry Created Successfully", anotherCreatedOutput);
    }

    @Test
    public void testThatAddedEntryBelongingToUserCanBeFound(){
        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);

        String anotherCreatedOutput = diaryController.createEntry("Username", "title", "body");
        assertEquals("Entry Created Successfully", anotherCreatedOutput);

        String entryInfo= diaryController.findEntry("Username", "title");
        System.out.println(entryInfo);

    }

    @Test
    public void testThatEntryTitleCannotBeDuplicated(){

        String actual = diaryController.register(registerUserRequest);
        assertEquals("Successful", actual);

        String anotherCreatedOutput = diaryController.createEntry("Username", "title", "body");
        assertEquals("Entry Created Successfully", anotherCreatedOutput);

        String anotherCreatedEntry = diaryController.createEntry("Username", "title", "body");
        assertEquals("Entry Title Already Exists", anotherCreatedEntry);
    }

}