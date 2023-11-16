buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:3.5.4")
    }

    repositories{
        google()
        jcenter()
        //jcenter ahora es sólo lectura, pero lo necesito para el spot-dialog
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
}