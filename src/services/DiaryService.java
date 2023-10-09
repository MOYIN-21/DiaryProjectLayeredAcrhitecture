package services;

import models.Diary;

public interface DiaryService {
    void register(String username, String password);
    Diary findByUserName(String userName);
    void delete(String userName);
    long count();
}
