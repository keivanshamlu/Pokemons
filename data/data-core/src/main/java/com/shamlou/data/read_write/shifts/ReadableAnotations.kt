package com.shamlou.data.read_write.shifts

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalReadable

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteReadable