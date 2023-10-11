package data.model.repositories;
import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {
    private List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        boolean entryDoesNotExist = entry.getId() == 0;
        if (entryDoesNotExist) saveNew(entry);
        else update(entry);

        return entry;
    }

    private void saveNew(Entry entry) {
        entry.setId(generateId());
        entries.add(entry);
    }

    private void update(Entry entry) {
        Entry newEntry = findById(entry.getId());
        newEntry.setTitle(entry.getTitle());
        newEntry.setBody(entry.getBody());
    }

    private int generateId() {
        return entries.size() + 1;
    }

    @Override
    public void delete(Entry entry) {
        Entry foundEntry = findById(entry.getId());
        entries.remove(foundEntry);
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public Entry findById(int id) {
        for(Entry entry: entries)
            if(entry.getId() == id) return entry;

        return null;
    }

    @Override
    public Iterable<Entry> findAll() {
        return entries;
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Entry findByUsername(String ownerName, String title) {
        for(Entry entry: entries)
            if (entry.getOwnerName().equalsIgnoreCase(ownerName)
                    && entry.getTitle().equalsIgnoreCase(title))
                return entry;
        return null;

    }
}