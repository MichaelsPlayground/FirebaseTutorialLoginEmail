# Firebase tutorial: Login with email and password

append in build.gradle (module):
```plaintext
plugins {
    ...
    id 'com.google.gms.google-services'
}

dependencies {
    ...
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:30.1.0')
    // Declare the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth'
}
```

append in settings.gradle:
```plaintext
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.google.gms:google-services:4.3.10'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
```

error when building the app without having google-services.json in app directory
```plaintext
Execution failed for task ':app:processDebugGoogleServices'.
> File google-services.json is missing. The Google Services Plugin cannot function without it. 
```

To run this app you need a connection between your app and Google's Firebase service, so go to: 

https://firebase.google.com

login with your Google account and proceed to console.

Add a new project (in German "Project hinzufuegen")

There are 3 steps to create a new Firebase project.

Step 1: give a name for the project, I named it "FirebaseTutorial"

Below you see an automatic generated (unambiguously) internal name (here it is "fir-tutorial-73344, your name will differ).

Step 2: use Google Analytics within your project

I'm for sure that Analytics is a fantastic product I'm disabling it for this project.

Proceed with "Project erstellen" = "create project"

Step 3: After some seconds your project is ready to use, press ("Weiter" = "proceed")

We are now on the project overview page and the first step is to add an "App" to the project, 
meaning to add the platform (Android) to the project. Simply press the Android icon and proceed

You need to register your app ("App registrieren") by the package name and the SHA-1-hash of your configuration.

The way to get both data is to switch to Android Studio and copy and paste the data.

For the package name data: got to the top of your MainActivity.java class and copy the package name  
(my paket name is "de.androidcrypto.firebasetutorial") and paste it to the webpage. 

The second step is not neccessary at the moment but for later steps you will some crazy error messages if 
Firebase does not have the information. 

For the SHA-1 value ("SHA-1 Wert...") open the Gradle tab within Android Studio:

Press the "Execute Gradle Task button/icon", enter behind gradle "signingReport" and press return.

Below there are some information, copy the SHA1-values and paste them to the webpage. My value is 
"19:22:A4:D7:01:A8:3D:09:8F:04:93:E9:8E:21:92:2D:5A:5F:B0:54", your will differ.

Important note: the SHA-1 value is from the DEBUG configuration in my case. If you are going to 
publish your app on the PlayStore you need to give the SHA-1 value of the RELEASE configuration. 

Now press the "App registrieren" button to register the app.

The Firebase wizzard is creating a configuration file that you need to copy to the project in Android Studio.

Move the downloaded file "google-services.json" to the app folder of your app as shown in the screenshot:

One possible way is shown here: Change the view from "Android" to "Project" and drag the "google-services.json" 
file from your download folder to the app folder  

On the wizzard page press "Weiter" to proceed.

Step 3 of the wizzard shows how to append the Firebase SDK to the project (here done in the beginning).

On the wizzard page press "Weiter" to proceed.

Step 4 of the wizzard is "Nächste Schritte" = "next steps", proceed with the "Weiter zur Konsole" = 
"go to console" button.

We are back on our overview page and we need to activate "Authentication", so please press "Authentication" 
on the left sided menue.

There is a short introduction and a video with a short explanation on Youtube:

https://youtu.be/8sGY55yxicA

Press the "Los gehts" = "start" button

We start with the "Sign-in method" tab of the Authentication menue. The different types of possible authentication  
are named "providers", we are choosing "Email-Adresse/Passwort" = "email address with password" for 
this tutorial.

Activate the first option "E-Mailadresse/Passwort" = "email address / password" but  
NOT the second option "E-Mail-Link" ("email link"). Procced with "speichern" = "save".

The "Sign-in method"-tag shows that we have activated the authentication with an  
Email address and password ("Aktiviert" = "activated"). 

Now we are ready to use Firebase Authentication with email address and password. Below on the page you get a notice that 
there is a registration limit for new users, at the moment it is limited to register 
100 new email addresses per hour. The limit may change in the future or when using a 
Firebase payed plan, I'm using a free "Spark" plan for my tutorial.

As a last view let's move to the "Users" tab in Authentication submenu. At the moment 
there are no users because we haven't signed up any user but you could add a user 
manually by pressing the button "Nutzer hinzufuegen" = "add a new user".



data from signingReport
```plaintext
> Task :app:signingReport
Variant: debug
Config: debug
Store: /Users/michaelfehr/.android/debug.keystore
Alias: AndroidDebugKey
MD5: 4D:1E:D7:91:56:67:73:EA:54:73:BC:EC:D7:E5:9B:F5
SHA1: 19:22:A4:D7:01:A8:3D:09:8F:04:93:E9:8E:21:92:2D:5A:5F:B0:54
SHA-256: A7:A8:66:27:C7:76:6D:C3:3C:9E:3F:89:99:88:3E:A1:7B:ED:34:69:19:83:B6:EA:72:04:C9:13:8E:84:E0:90
Valid until: Samstag, 30. September 2051
```


German translations:
```plaintext
Project creation:
Project erstellen (Schritt 1 von 3) = create a project, step 1 of 3
Geben Sie zuerst einen Namen für Ihr Projekt ein = Give a name for your project first
Projektname = project name

Project erstellen (Schritt 2 von 2) = create a project, step 2 of 2
Google Analytics für Ihr Firebase-Projekt = Google Analytics for your Firebase project
... some descriptions
Google Analytics für dieses Project aktivieren / Empfohlen = activate Google Analytics for this project (recommended)
Projekt erstellen = create project

Ihr neues Projekt steht bereit = your project is ready to use
Weiter = proceed

Project overview
Fügen Sie Firebase zu Ihrer App hinzu, um zu beginnen = add Firebase to your platform


Firebase zu meiner Android-App hinzufügen = add firebase to your Android app
App registrieren = register your app
Android-Paketname = Android package name

2 Konfigurationsdatei herunterladen = download the configuration file
3 Firebase SDK hinzufuegen = add Firebase SDK to your app
4 Nächste Schritte = next steps
... some descriptions
Weiter zur Konsole = proceed to console

Entwickeln = Develop

Authentication
Nutzer verschiedener Authentifizierungsplattformen ohne serverseitigen Code authentifizieren und verwalten = 
authenticate user on different auth platforms
Los gehts = start the wizzard
Anbieter für Anmeldungen = provider for logins
Erste Schritte mit Firebase Auth: Erste Anmeldemethode hinzufügen = First step with Firebase Auth: add a login method
Native Anbieter = native provider
Zusätzliche Anbieter = additional provider
E-Mailadresse/Passwort"= email address / password
E-Mail-Link = email link
Aktiviert = activated

Nutzer hinzufuegen = add a new user
```

```plaintext
Registrierungskontingent verwalten
Wir begrenzen die Anzahl neuer E-Mail-Adressen/Passwörter und anonymer Registrierungen, die für Ihre Anwendung über dieselbe IP-Adresse möglich sind, um einen Missbrauch Ihres Projekts zu verhindern. Temporäre Änderungen an diesem Kontingent können Sie hier anfordern und planen.
Aktuelles Kontingent pro Stunde: 100
```

Mail when sending a password reset link
```plaintext
Reset your password for project-938397672907
Hello,
Follow this link to reset your project-938397672907 password for your edvmf@gmx.de account.
https://fir-tutorial-73344.firebaseapp.com/__/auth/action?mode=resetPassword&oobCode=RKlVUbOmVZG9bLJmxG24BECcKeo2k1fzYBU9vPnc6CUAAAGBSpN2dg&apiKey=AIzaSyAzT4-gG3ak33WUlk6b2aZQ4AV7XQfdwQE&lang=en
If you didn’t ask to reset your password, you can ignore this email.
Thanks,
Your project-938397672907 team
```


