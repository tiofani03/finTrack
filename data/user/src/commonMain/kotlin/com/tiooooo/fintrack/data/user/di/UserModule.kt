package com.tiooooo.fintrack.data.user.di

import com.tiooooo.fintrack.data.user.api.repo.UserRepository
import com.tiooooo.fintrack.data.user.impl.UserRepositoryImpl
import org.koin.dsl.module

val dataUserModule = module {
  single<UserRepository> { UserRepositoryImpl() }
}