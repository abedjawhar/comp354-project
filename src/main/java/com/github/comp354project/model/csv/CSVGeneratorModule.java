package com.github.comp354project.model.csv;

import com.github.comp354project.model.account.remote.RemoteAccount;
import com.github.comp354project.model.sqlite.IConnectionProvider;
import com.github.comp354project.utils.ProxyFactory;
import com.j256.ormlite.dao.Dao;
import dagger.Module;
import dagger.Provides;

@Module
public class CSVGeneratorModule {
    @Provides
    static ICSVGenerator provideCSVGenerator(CSVGenerator generator)  {
        return ProxyFactory.newInstance(generator, ICSVGenerator.class);
    }
}
