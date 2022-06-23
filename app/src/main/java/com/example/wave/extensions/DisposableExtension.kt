package com.example.wave.extensions

import io.reactivex.disposables.Disposable

fun Disposable.disposeIfNotAlready() {
    if(!isDisposed) {
        dispose()
    }
}