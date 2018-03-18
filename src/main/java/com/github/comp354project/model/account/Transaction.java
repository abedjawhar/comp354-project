package com.github.comp354project.model.account;

import com.github.comp354project.model.account.Account;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DatabaseTable(tableName = "AccountTransaction")
public class Transaction {
    @DatabaseField(generatedId = true)
    private Integer ID;

    @DatabaseField(columnName = "date", canBeNull = false)
    private Integer date;

    @DatabaseField(columnName = "amount", canBeNull = false)
    private Double amount;

    @DatabaseField(columnName = "type", canBeNull = false)
    private String type;

    @DatabaseField(columnName = "category")
    private String category;

    @DatabaseField(columnName = "source_id")
    private Integer sourceID;

    @DatabaseField(columnName = "destination_id")
    private Integer destinationID;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "account_id")
    private Account account;
}
