package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensureContactPreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create((new ContactData()
                    .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("MOSCOW")
                    .withHomephone("495123-44-55").withEmail("ivanov@mail.ru").withGroup("test1")), true);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Maxim")
                .withMiddlename("Maximovich").withLastname("Maxov").withAddress("112666\nTVER\nPravda str.").withHomephone("495123-44-59").withEmail("maxmod@mail.ru").withPhoto(new File("src/test/resources/user.png"));

        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
