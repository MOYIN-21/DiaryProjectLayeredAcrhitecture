package services;
import data.model.Diary;
import data.model.Entry;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;

public interface DiaryServices {

    void register(RegisterUserRequest registerUserRequest);

    long count();

    Diary findByUsername(String username);

    void delete(String username, String password);

    void update(String username, String oldPassword, String newPassword);


    Entry addEntry(String username, String title, String body);

    Entry findEntry(String username, String title);

    void lock(String username);

    void unlock(LogInRequest logInRequest);
}
