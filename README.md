# GenericViewBinder

[![](https://jitpack.io/v/Miladhsp/GenericViewBinder.svg)](https://jitpack.io/#Miladhsp/GenericViewBinder)
Android library for working with View-Binding


# Configuration

#### build.gradle(:app|Module)
```gradle
android {
    buildFeatures {
        viewBinding true
    }
}

dependencies {
	implementation 'com.github.Miladhsp:GenericViewBinder:Tag'
}
```

#### build.gradle(:Project)
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
#### settings.gradle 
```diff
if you dont have 'all project in build.gradle(:Project)' 
add this to settings.gradle 

+ maven { url 'https://jitpack.io' }
```
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
rootProject.name = "My Application"
include ':app'

```

#### AndroidManifest.xml

```diff
In the Android Manifest file, declare the following.

<application    
+ android:name="ir.mich.genericviewbinder.App"
></application>
```

```xml
   
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.example" >

    <application
        android:name="ir.mich.genericviewbinder.App"
        
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.example">
        <activity
            android:name=".MainActivity"
            android:exported="true" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
   
```

# Example
#### MainActivity
```java
package com.example.viewbindingexample;

import android.annotation.SuppressLint;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.ActivityBinder;
import ir.mich.genericviewbinder.App;


public class MainActivity extends ActivityBinder<ActivityMainBinding> {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate() {
        binding.txt.setText("hello");
    }

}
```

#### activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/txt"
        
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```




