package com.sergio.mozpertest.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.sergio.mozpertest.di.Constants.BASE_URL
import com.sergio.mozpertest.domain.login.LoginManager
import com.sergio.mozpertest.domain.login.LoginManagerImpl
import com.sergio.mozpertest.model.local.AppDataBase
import com.sergio.mozpertest.model.local.EmployeeDao
import com.sergio.mozpertest.model.remote.BusinessService
import com.sergio.mozpertest.model.repository.BusinessRepository
import com.sergio.mozpertest.model.repository.BusinessRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): BusinessService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BusinessService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginManager(
        sharedPreferences: SharedPreferences
    ): LoginManager {
        return LoginManagerImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            "MozperPreferences",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideRepository(
        service: BusinessService,
        employeeDao: EmployeeDao
    ): BusinessRepository {
        return BusinessRepositoryImpl(service, employeeDao)
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "employee_db"
    ).build()

    @Singleton
    @Provides
    fun provideEmployeeDao(db: AppDataBase) = db.employeeDao()

}
