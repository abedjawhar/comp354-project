package com.github.comp354project.service.account;

import com.github.comp354project.service.user.User;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "Account")
public class Account {
    @DatabaseField(id = true, columnName = "id")
    private Integer ID;

    @DatabaseField(columnName = "bank_name")
    private String bankName;

    @DatabaseField(columnName = "type")
    private String type;

    @DatabaseField(columnName = "balance")
    private Double balance;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user_id")
    private User user;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Transaction> transactions;
};