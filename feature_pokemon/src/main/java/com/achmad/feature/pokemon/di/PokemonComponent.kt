package com.achmad.feature.pokemon.di

import android.content.Context
import com.achmad.baseandroid.di.FeaturePokemonDependencies
import com.achmad.feature.pokemon.PokemonListActivity
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
@Component(dependencies = [FeaturePokemonDependencies::class])
interface PokemonComponent {
    fun inject(target: PokemonListActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(dependencies: FeaturePokemonDependencies): Builder
        fun build(): PokemonComponent
    }
}

fun buildDaggerPokemonComponent(context: Context): PokemonComponent {
    return DaggerPokemonComponent.builder()
        .context(context)
        .dependencies(
            EntryPointAccessors.fromApplication(
                context,
                FeaturePokemonDependencies::class.java
            )
        )
        .build()
}
