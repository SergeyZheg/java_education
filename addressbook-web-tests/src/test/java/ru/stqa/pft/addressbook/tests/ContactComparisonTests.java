package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactComparisonTests extends TestBase{

    @BeforeMethod
    public void ensureContactPreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create((new ContactData()
                    .withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("MOSCOW")
                    .withHomephone("495123-44-55").withEmail("ivanov@mail.ru").withGroup("test1")), true);
        }
    }

    @Test
    public void testContactComparison() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream().filter((s) -> ! s.equals(""))
                        .map(ContactComparisonTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactComparisonTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String list) {
        return list.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
