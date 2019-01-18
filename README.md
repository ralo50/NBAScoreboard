# NBA Scoreboard


NBA Scoreboard is a sports app for live scores, play-by-plays and detailed stats for every game, team and player in the NBA.


Soon available on the Play store.

<a href='https://github.com/ralo50/NBAScoreboard'><img alt='Soon on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height='80px'/></a>

## Joining the Beta
Want to live life on the bleeding edge and help me out with testing and further development?

You can pull your own branch and start working from there.
 
If you're interested in a life of peace and tranquility, stick with the standard releases.

Building NBA Scoreboard
===============

Basics
------

NBA Scoreboard uses [Gradle](http://gradle.org) to build the project and to maintain
dependencies.  However, you needn't install it yourself; the
"gradle wrapper" `gradlew`, mentioned below, will do that for you.

Building NBA Scoreboard
---------------

The following steps should help you (re)build NBA Scoreboard from the command line.

1. Checkout the NBA Scoreboard project source with the command:

        git clone https://github.com/ralo50/NBAScoreboard.git

2. Make sure you have the [Android SDK](https://developer.android.com/sdk/index.html) installed.
3. Ensure that the following packages are installed from the Android SDK manager:
    * Android SDK Build Tools (see buildToolsVersion in build.gradle)
    * SDK Platform (All API levels)
    * Android Support Repository
    * Google Repository
4. Create a local.properties file at the root of your source checkout and add an sdk.dir entry to it.  For example:

        sdk.dir=/Application/android-sdk-macosx

5. Using Java 8 

6. Execute Gradle:

        ./gradlew build


Setting up a development environment
------------------------------------

[Android Studio](https://developer.android.com/sdk/installing/studio.html) is the recommended development environment.

1. Install Android Studio.
2. Open Android Studio. On a new installation, the Quickstart panel will appear. If you have open projects, close them using "File > Close Project" to see the Quickstart panel.
3. From the Quickstart panel, choose "Configure" then "SDK Manager".
4. In the SDK Tools tab of the SDK Manager, make sure that the "Android Support Repository" is installed, and that the latest "Android SDK build-tools" are installed. Click "OK" to return to the Quickstart panel.
5. From the Quickstart panel, choose "Checkout from Version Control" then "git".
6. Paste the URL for the NBA Scoreboard project when prompted (https://github.com/ralo50/NBAScoreboard.git).
7. Android studio should detect the presence of a project file and ask you whether to open it. Click "yes".
9. Default config options should be good enough.
9. Project initialisation and build should proceed.



# Legal things
This app is not affiliated with or endorsed by the National Basketball Association.
Any trademarks used in the app are done so under "fair use" with the sole purpose of identifying the respective entities,
and remain the property of their respective owners.


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
