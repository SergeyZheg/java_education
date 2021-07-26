package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().contactCreation(new ContactData("Ivan", "Ivanovich", "Ivanov", "MOSCOW", "495123-44-55", "ivanov@mail.ru", "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Maxim", "Maximovich", "Maxov", "TVER", "495123-44-59", "max@mail.ru", null),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }


}
