package ru.itmo.kotiki;

import entity.Cat;
import entity.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.CatService;
import service.OwnerService;
import tool.Color;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KotikiTests {

    CatService catService = org.mockito.Mockito.mock(CatService.class);
    OwnerService ownerService = org.mockito.Mockito.mock(OwnerService.class);

    private Cat cat, cat2, expectedCat;
    private Owner owner;
    private List<Cat> friends;
    private List<Cat> allCats;

    @Before
    public void setUp() throws SQLException {

        owner = new Owner();
        owner.setName("bob");

        cat = new Cat();
        cat.setName("basya");
        cat.setColor(Color.BLACK);
        cat.setBreed("Abyssinian");
        cat.setBirthDate(Date.valueOf("2017-11-15"));
        cat.setOwner(owner);

        cat2 = new Cat();
        cat2.setName("sasha");
        cat2.setColor(Color.BLACK);
        cat2.setBreed("bengal");
        cat2.setBirthDate(Date.valueOf("2002-11-10"));
        cat2.setOwner(owner);

        expectedCat = new Cat();
        expectedCat.setName("masha");
        expectedCat.setColor(Color.BLACK);
        expectedCat.setBreed("bengal");
        expectedCat.setBirthDate(Date.valueOf("2002-11-10"));
        expectedCat.setOwner(owner);

        catService.add(cat);
        catService.add(cat2);
        ownerService.add(owner);

        allCats = new ArrayList<>();
        allCats.add(cat);
        allCats.add(cat2);

       when(catService.getById(1L)).thenReturn(cat);
       when(catService.getAll()).thenReturn(allCats);
       when(ownerService.getById(1L)).thenReturn(owner);
       when(ownerService.getCatsByOwnerId(1L)).thenReturn(allCats);
       //doNothing().when(catService).update(cat);
    }


    @Test
    public void getCatById() throws SQLException {
        Cat expectedCat = catService.getById(1L);
        Assert.assertEquals(expectedCat, cat);
    }

    @Test
    public void getAllCats() throws SQLException {
        List<Cat> expectedCats = catService.getAll();
        Assert.assertEquals(expectedCats, allCats);
    }

    @Test
    public void checkUpdatedCat() throws SQLException {
        cat2.setName("masha");
        catService.update(cat2);
        Assert.assertEquals(expectedCat, cat2);
    }

    @Test
    public void addToFriend() throws SQLException {
        friends = new ArrayList<Cat>();
        friends.add(cat2);
        cat.setFriends(friends);
        Assert.assertEquals(friends, cat.getFriends());
    }

    @Test
    public void getOwnerById() throws SQLException {
        Owner expectedOwner = ownerService.getById(1L);
        Assert.assertEquals(expectedOwner, owner);
    }

    @Test

    public void getCatsByOwnerId() throws SQLException {
        List<Cat> expectedCats = ownerService.getCatsByOwnerId(1L);
        Assert.assertEquals(expectedCats, allCats);
    }
}
