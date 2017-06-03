package com.ld.springboot.repo;

import com.ld.springboot.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Dao层
 * Created by liudun on 2017/6/3.
 */

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Contact> findAll() {
        return jdbc.query("select id,firstName,lastName,phoneNumber,emailAddress" +
                        " from contacts order by lastName",
                (rs, rowNum) -> {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong(1));
                    contact.setFirstName(rs.getString(2));
                    contact.setLastName(rs.getString(3));
                    contact.setPhoneNumber(rs.getString(4));
                    contact.setEmailAddress(rs.getString(5));
                    return contact;
                }
        );
    }

    public void saveContact(Contact contact) {
        jdbc.update(
                "insert into contacts" +
                        "(firstName,lastName,phoneNumber,emailAddress)" +
                        "VALUES (?,?,?,?)",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber(),
                contact.getEmailAddress()
        );
    }
}
