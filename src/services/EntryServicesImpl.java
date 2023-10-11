package services;
import data.model.Entry;
import data.model.repositories.EntryRepository;
import data.model.repositories.EntryRepositoryImpl;

public class EntryServicesImpl implements EntryServices{

    private EntryRepository entryRepository = new EntryRepositoryImpl();
    @Override
    public Entry addEntry(String ownerName, String title, String body) {
        checkIfTitleAlreadyExists(ownerName, title);
        Entry newEntry = new Entry();
        newEntry.setOwnerName(ownerName);
        newEntry.setTitle(title);
        newEntry.setBody(body);
        entryRepository.save(newEntry);
        return newEntry;
    }

    private void checkIfTitleAlreadyExists(String username, String title) {
        Entry foundEntry = entryRepository.findByUsername(username, title);
        if(foundEntry != null)
            throw new IllegalArgumentException("Entry Title Already Exists");
    }

    @Override
    public long count(){
        return entryRepository.count();
    }

    @Override
    public void delete(String ownerName, String title) {
        Entry foundEntry = findEntry(ownerName, title);
        entryRepository.delete(foundEntry);
    }

    @Override
    public Entry findEntry(String ownerName, String title) {
        Entry foundEntry = entryRepository.findByUsername(ownerName, title);
        boolean entryIsNotFound = foundEntry == null;
        if(entryIsNotFound) throw new IllegalArgumentException("Entry is not Found");
        return foundEntry;
    }


}
