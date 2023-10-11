package controller;
import data.model.Entry;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;
import services.DiaryServices;
import services.DiaryServicesImpl;

public class DiaryController {

    private DiaryServices diaryServices = new DiaryServicesImpl();
    public String register(RegisterUserRequest registerUserRequest) {
        try {
            diaryServices.register(registerUserRequest);
            return "Successful";
        }
        catch (Exception error){
            return error.getMessage();

        }
    }


    public String lock(String username) {
        diaryServices.lock(username);
        return "Locked";
    }

    public String createEntry(String username, String title, String body) {
        try {
            diaryServices.addEntry(username, title, body);
            return "Entry Created Successfully";
        }
        catch (Exception error){
            return error.getMessage();
        }
    }

    public String unlock(LogInRequest logInRequest) {
        try {
            diaryServices.unlock(logInRequest);
            return "Diary Unlocked";
        }
        catch (Exception error){
            return error.getMessage();
        }
    }

    public String findEntry(String username, String title) {
        try {
            Entry entry = diaryServices.findEntry(username, title);
            return entry.toString();
        }
        catch (Exception error){
            return error.getMessage();
        }
    }
}