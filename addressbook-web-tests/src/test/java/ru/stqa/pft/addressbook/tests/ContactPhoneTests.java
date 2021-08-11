package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensureContactPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create((new ContactData()
                    .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("MOSCOW")
                    .withHomephone("495123-44-55").withEmail("ivanov@mail.ru").withGroup("test1")), true);
        }
    }

    @Test ( enabled=false )
    public void testContactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
