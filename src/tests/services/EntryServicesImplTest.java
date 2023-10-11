package tests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.EntryServices;
import services.EntryServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntryServicesImplTest {

    private EntryServices entryServices;

    @BeforeEach
    public void setUp(){
        entryServices = new EntryServicesImpl();
    }
    @Test
    public void testThatEntryCanBeAdded(){
        entryServices.addEntry("OwnerName", "title", "body");
        assertEquals(1, entryServices.count());
    }

    @Test
    public void testThatEntryBelongingToUserCanBeDeletedUsingTitle(){
        entryServices.addEntry("OwnerName", "title", "body");
        entryServices.addEntry("OwnerName", "titleOfEntry", "body");
        assertEquals(2, entryServices.count());

        entryServices.delete("OwnerName", "title");
        assertEquals(1, entryServices.count());
    }

    @Test
    public void testThatDeleteEntryThrowsAnExceptionIfEntryIsNotFound(){
        entryServices.addEntry("OwnerName", "title", "body");
        entryServices.addEntry("OwnerName", "titleOfEntry", "body");
        assertEquals(2, entryServices.count());

        assertThrows(IllegalArgumentException.class,
                ()-> entryServices.delete("OwnerName", "NoTitle"));
    }

    @Test
    public void testThatEntryServicesThrowsExceptionIfEntryIsNotFound(){
        entryServices.addEntry("OwnerName", "title", "body");
        entryServices.addEntry("OwnerName", "title Of Entry", "body");
        assertEquals(2, entryServices.count());

        assertThrows(IllegalArgumentException.class,
                ()-> entryServices.findEntry("OwnerName", "titles"));
    }



}