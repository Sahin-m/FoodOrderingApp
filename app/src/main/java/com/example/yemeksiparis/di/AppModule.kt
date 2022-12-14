package com.example.yemeksiparis.di

import com.example.yemeksiparis.data.repo.SepetDaoRepository
import com.example.yemeksiparis.data.repo.YemeklerDaoRepository
import com.example.yemeksiparis.retrofit.ApiUtils
import com.example.yemeksiparis.retrofit.SepetYemeklerDao
import com.example.yemeksiparis.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao:YemeklerDao) : YemeklerDaoRepository{
        return YemeklerDaoRepository(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideSepetDaoRepository(sydao:SepetYemeklerDao) : SepetDaoRepository{
        return SepetDaoRepository(sydao)
    }

    @Provides
    @Singleton
    fun provideSepetYemeklerDao() : SepetYemeklerDao{
        return ApiUtils.getSepetYemeklerDao()
    }
}