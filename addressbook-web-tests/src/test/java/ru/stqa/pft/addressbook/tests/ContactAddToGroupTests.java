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

public class ContactAddToGroupTests extends TestBase {



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

    @Test(enabled = true)
    public void testContactAddToGroup() throws Exception {
        Groups groupbefore = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        Iterator<GroupData> iterator = groupbefore.iterator();
        GroupData selectedGroup = iterator.next();
        Groups contactGroups = modifiedContact.getGroups();
        int n=1;
        for (GroupData g : contactGroups){
            if (selectedGroup.equals(g)) {
                if (iterator.hasNext()) {
                    selectedGroup = iterator.next();
                    n++;
                } else {
                    app.goTo().groupPage();
                    GroupData newgroup = new GroupData().withName(String.format("testN%s",n)).withHeader(String.format("headerN%s",n)).withFooter(String.format("footerN%s",n ));
                    app.group().create(newgroup);
                    Groups groupafter = app.db().groups();
                    groupbefore.withAdded(newgroup.withId(groupafter.stream().mapToInt((g1) -> g1.getId()).max().getAsInt()));
                    selectedGroup = newgroup;
                }
            }
        }

        app.goTo().homePage();
        app.contact().addToGroup(modifiedContact,selectedGroup);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }

}

