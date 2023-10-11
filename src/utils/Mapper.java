package utils;
import data.model.Diary;
import dtos.RegisterUserRequest;

public class Mapper {

    public static void map(RegisterUserRequest registerUserRequest, Diary diary){
        diary.setUsername(registerUserRequest.getUsername());
        diary.setPassword(registerUserRequest.getPassword());

    }
}
