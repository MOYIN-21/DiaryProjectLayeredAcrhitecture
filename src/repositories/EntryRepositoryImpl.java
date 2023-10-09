package repositories;
import models.Entry;

import java.util.ArrayList;
import java.util.List;


public class EntryRepositoryImpl implements EntryRepository {
    List<Entry> entries = new ArrayList<>();
    private String userName;
    private String password;
    private Entry entry;

    @Override
    public Entry createEntry(Entry entry) {
        boolean entryDoesNotExist = entry.getEntryId() == 0 || findEntryById(entry.getEntryId()) == null;
        if (entryDoesNotExist) saveNewEntry(entry);
    else {
        updateEntry();
        }
        return entry;
    }

    private void saveNewEntry(Entry entry) {
        entry.setOwner(userName);
        entries.add(entry);
    }

    public void updateEntry() {
        Entry newEntry = findEntryById(entry.getEntryId());
        newEntry.setOwner(entry.getOwner());
        entry.setId(entry.getEntryId());
        entry.setTitle(entry.getTitle());
        entry.setBody(entry.getBody());
    }

    public int generateId(){
        return entries.size() + 1;
    }

    @Override
    public void delete(Entry entry) {
        Entry entry1 = findEntryById(entry.getId());
        entries.remove(entry1);
    }
    public Entry  findEntryById(int entryId){
        for (Entry entry : entries){
            if (entry.getEntryId() == entryId){
                return entry;
            }
        }
        throw new NullPointerException("Invalid Id");
    }

    @Override
    public void add(Entry entry) {
        entry.setId(generateId());
        entries.add(entry);
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public Iterable<Entry> findAll() {
        return entries;
    }

    @Override
    public void clear() {
        entries.clear();
    }
}
