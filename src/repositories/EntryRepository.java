package repositories;
import models.Entry;

public interface EntryRepository {
    Entry createEntry(Entry entry);
    void updateEntry();
    void delete(Entry entry);
    void add(Entry entry);
    long count();
    Iterable<Entry> findAll();
    void clear();
}
