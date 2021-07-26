package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().contactCreation(new ContactData("Ivan", "Ivanovich", "Ivanov", "MOSCOW", "495123-44-55", "ivanov@mail.ru", "test1"), true);

    }

}
