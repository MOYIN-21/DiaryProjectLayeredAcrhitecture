package data.model.repositories;
import data.model.Entry;

public interface EntryRepository {
    Entry save(Entry entry);

    void delete(Entry entry);

    long count();

    Entry findById(int i);

    Iterable<Entry> findAll();

    void clear();

    Entry findByUsername(String ownerName, String title);
}
