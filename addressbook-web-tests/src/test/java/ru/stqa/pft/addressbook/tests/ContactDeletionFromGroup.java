package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {


    @BeforeMethod
    public void ensureContactPreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create((new ContactData()
                    .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("MOSCOW")
                    .withHomephone("495123-44-55").withEmail("ivanov@mail.ru").withGroup("test1")), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
        }
    }


    @Test
    public void testContactDeletionFromGroup() throws Exception {
        app.goTo().homePage();
        Groups groupsbefore = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        GroupData selectedGroup;

        if (modifiedContact.getGroups().size() == 0) {
            selectedGroup = groupsbefore.iterator().next();
            app.contact().addToGroup(modifiedContact, selectedGroup);
            modifiedContact.inGroup(selectedGroup);
        }
        Groups contactGroups = modifiedContact.getGroups();
        Iterator<GroupData> iterator = contactGroups.iterator();
        selectedGroup = iterator.next();
        app.goTo().homePage();
        app.contact().deleteContactFromGroup(modifiedContact, selectedGroup);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();

    }

}
