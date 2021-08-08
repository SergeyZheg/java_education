package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.goTo().homePage();
        Contacts before = app.contact().all();

        ContactData contact = new ContactData()
                .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("115500\n MOSCOW\n Red square\n h.1")
                .withHomephone("495123-44-55").withEmail("ivanov@mail.ru").withGroup("test1");
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}

