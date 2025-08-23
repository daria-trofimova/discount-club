package com.example.discountclub.di

import com.example.discountclub.data.LoginRepository
import com.example.discountclub.data.PurchasesRepository
import com.example.discountclub.data.UserRepository
import com.example.discountclub.domain.repository.LoginRepositoryApi
import com.example.discountclub.domain.repository.PurchasesRepositoryApi
import com.example.discountclub.domain.repository.UserRepositoryApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindUserRepositoryApi(impl: UserRepository): UserRepositoryApi

    @Binds
    fun bindPurchasesRepositoryApi(impl: PurchasesRepository): PurchasesRepositoryApi

    @Binds
    fun bindLoginRepositoryApi(impl: LoginRepository): LoginRepositoryApi
}