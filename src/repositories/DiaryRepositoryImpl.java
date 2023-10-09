package repositories;

import models.Diary;

import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository{
    private String userName;
    private String password;
    List<Diary> diaries = new ArrayList<>();
    private Diary diary;

    @Override
    public Diary save(Diary diary) {
        boolean newDiary = diary.getId() == 0 || findById(diary.getId()) == null;
        if (newDiary) saveNewDiary(diary);
        else {
            updateDiary();
        }
        return diary;
    }

    private void updateDiary() {
        Diary newDiary = findById(diary.getId());
        newDiary.setUsername(diary.getUserName());
    }

    public int generateId(){
        return diaries.size() + 1;
    }

    private void saveNewDiary(Diary diary) {
        diary.setUserName(userName);
        diary.setPassword(password);
        diaries.add(diary);
    }

    @Override
    public void delete(Diary diary) {
        Diary diary1 = findById(diary.getId());
        diaries.remove(diary1);
    }

    @Override
    public void addDiary(Diary diary) {
        diary.setUserName(userName);
        diary.setPassword(password);
        diaries.add(diary);
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public Diary findById(int id) {
        for (Diary diary:diaries) {
            if (diary.getId() == id){
                return diary;
            }
        }
        throw new NullPointerException("Id not found");
    }

    @Override
    public Iterable<Diary> findAll() {
        return diaries;
    }

    @Override
    public void clear() {
        diaries.clear();
    }

    @Override
    public String getDiaryUserName(String userName) {
        return userName;
    }
}
