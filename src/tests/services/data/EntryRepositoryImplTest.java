package tests.services.data;

import data.model.Entry;
import data.model.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplTest {

    private EntryRepositoryImpl entryRepository;
    @BeforeEach
    void setUp() {
        entryRepository = new EntryRepositoryImpl();
    }

    @Test public void saveEntry_countIncreases() {
        Entry entry = new Entry();
        entryRepository.save(entry);

        assertEquals(1, entryRepository.count());
    }
    @Test public void saveOneDiary_FindEntryTest(){
        Entry entry = new Entry();
        Entry newEntry = entryRepository.save(entry);

        assertEquals(newEntry, entryRepository.findById(1));

    }

    @Test public void updateEntryTest(){
        Entry entry = new Entry();

        entry.setTitle("The bad day the devil Drink Water");
        entry.setBody("Omo on this blessed day, the devil was thirsty");
        entryRepository.save(entry);
        assertEquals("The bad day the devil Drink Water",
                entryRepository.findById(1).getTitle());
        assertEquals("Omo on this blessed day, the devil was thirsty",
                entryRepository.findById(1).getBody());

        Entry updateEntry = new Entry();
        updateEntry.setId(1);
        updateEntry.setTitle("It was the last day the Devil Drank Water");
        updateEntry.setBody("The bad day the devil Drink Water, " +
                "Omo on this blessed day, the devil was thirsty");
        entryRepository.save(updateEntry);
        assertEquals("It was the last day the Devil Drank Water",
                entryRepository.findById(1).getTitle());
        assertEquals("The bad day the devil Drink Water, " +
                        "Omo on this blessed day, the devil was thirsty",
                entryRepository.findById(1).getBody());
    }
    @Test public void findingAEntryThatDoesNotExist_ReturnNull(){
        assertNull(entryRepository.findById(1));
    }
    @Test public void moreEntries_increasesCount(){
        Entry entry = new Entry();

        entry.setTitle("The bad day the devil Drink Water");
        entry.setBody("Omo on this blessed day, the devil was thirsty");
        entryRepository.save(entry);

        Entry updateEntry = new Entry();
        updateEntry.setTitle("It was the last day the Devil Drank Water");
        updateEntry.setBody("The bad day the devil Drink Water, " +
                "Omo on this blessed day, the devil was thirsty");
        entryRepository.save(updateEntry);

        assertEquals(2, entryRepository.count());

    }

    @Test public void deleteEntry_reducesCount() {
        Entry entry = new Entry();

        entry.setTitle("The bad day the devil Drink Water");
        entry.setBody("Omo on this blessed day, the devil was thirsty");
        entryRepository.save(entry);

        Entry updateEntry = new Entry();
        updateEntry.setTitle("It was the last day the Devil Drank Water");
        updateEntry.setBody("The bad day the devil Drink Water, " +
                "Omo on this blessed day, the devil was thirsty");
        entryRepository.save(updateEntry);

        assertEquals(2, entryRepository.count());

        entryRepository.delete(entry);
        assertEquals(1, entryRepository.count());

    }
    @Test public void clearEntryTest(){
        Entry entry = new Entry(); entryRepository.save(entry);
        Entry newEntry = new Entry(); entryRepository.save(newEntry);
        Entry extraEntry = new Entry(); entryRepository.save(extraEntry);

        assertEquals(3, entryRepository.count());

        entryRepository.clear();
        assertEquals(0, entryRepository.count());

    }

    @Test public void AllEntriesCanBeReceivedTest() {
        Entry entry = new Entry();
        entryRepository.save(entry);
        Entry newEntry = new Entry();
        entryRepository.save(newEntry);
        Entry extraEntry = new Entry();
        entryRepository.save(extraEntry);

        assertEquals(3, entryRepository.count());

        Iterable<Entry> allEntries = List.of(new Entry[]{entry, newEntry, extraEntry});
        assertEquals(allEntries, entryRepository.findAll());

    }

}