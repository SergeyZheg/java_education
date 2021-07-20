package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Maxim", "Maximovich", "Maxov", "TVER", "495123-44-59", "max@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }


}
