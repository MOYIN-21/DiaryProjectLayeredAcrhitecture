package repositories;

import models.Diary;

public interface DiaryRepository {
    Diary save(Diary diary);
    void delete(Diary diary);
    void addDiary(Diary diary);
    long count();
    Diary findById(int id);
    //    Diary findById(int id);
    Iterable<Diary> findAll();
    void clear();
    String getDiaryUserName(String id);
}
