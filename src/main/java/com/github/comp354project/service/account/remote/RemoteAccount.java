package com.github.comp354project.service.account.remote;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"transactions"})
@DatabaseTable(tableName = "RemoteAccount")
public class RemoteAccount {
    @DatabaseField(columnName = "id", generatedId = true)
    private Integer ID;

    @DatabaseField(columnName = "bank_name")
    private String bankName;

    @DatabaseField(columnName = "type")
    private String type;

    @DatabaseField(columnName = "balance")
    private Double balance;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<RemoteTransaction> transactions;
}
