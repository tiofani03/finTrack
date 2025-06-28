package com.tiooooo.fintrack.di

import com.tiooooo.fintrack.data.di.localModule
import com.tiooooo.fintrack.data.di.platformModule
import com.tiooooo.fintrack.data.di.repositoryModule
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import com.tiooooo.fintrack.pages.di.pagesModule
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin

fun initKoin(
  appDeclaration: KoinAppDeclaration = {},
) {
  startKoin {
    appDeclaration()
    modules(
      platformModule(),
      localModule,
      repositoryModule,
      *dataModules.toTypedArray(),
      dispatcherModule,
      *pagesModule.toTypedArray(),
      navigationModule,
      *featureModules.toTypedArray()
    )
  }
  val features = getKoin().getAll<FeatureModule>()
  features.forEach { it.registerRoutes() }
}


val dispatcherModule = module {
  factory { Dispatchers.Default }
}

fun initKoin() = initKoin {}