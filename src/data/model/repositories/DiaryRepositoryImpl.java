package data.model.repositories;
import data.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl  implements DiaryRepository{

    private List<Diary> diaries = new ArrayList<>();

    @Override
    public Diary save(Diary diary) {
        boolean diaryDoesNotExists = diary.getId() == 0;
        if (diaryDoesNotExists) saveNew(diary);
        else update(diary);

        return diary;
    }

    private void saveNew(Diary diary) {
        diary.setId(generateId());
        diaries.add(diary);
    }

    private void update(Diary diary) {
        Diary newDiary = findById(diary.getId());
        newDiary.setUsername(diary.getUsername());
    }

    private int generateId() {
        return diaries.size() + 1;
    }

    @Override
    public void delete(Diary diary) {
        Diary foundDiary = findById(diary.getId());
        diaries.remove(foundDiary);
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public Diary findById(int id) {
        for (Diary diary: diaries)
            if(diary.getId() == id)
                return diary;

        return null;
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
    public Diary findByUsername(String username) {
        for(Diary diary: diaries)
            if(diary.getUsername().equalsIgnoreCase(username))
                return diary;
        return null;
    }
}
