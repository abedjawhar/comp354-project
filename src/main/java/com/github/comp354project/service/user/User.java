package com.github.comp354project.service.user;


import com.github.comp354project.service.account.Account;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

@Data
@EqualsAndHashCode(exclude={"accounts"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "User")
public class User {
    @DatabaseField(columnName = "id", generatedId = true)
    private Integer ID;

    @DatabaseField(columnName = "username", unique = true, canBeNull = false)
    private String username;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String lastName;

    @ForeignCollectionField
    private ForeignCollection<Account> accounts;
}