package com.tiooooo.fintrack.data.di

import com.tiooooo.fintrack.adaptor.AuthService
import org.koin.core.module.Module

expect fun platformModule(
    authService: AuthService?
): Module