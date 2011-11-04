Introduction
------------

This bundle extends Sakai OAE to integrate with the [Kaltura Video
Platform](http://corp.kaltura.com/).  With this bundle loaded, media
files are sent to Kaltura as they are uploaded, converted into formats
suitable for streaming delivery, and delivered from within Sakai OAE
using the streaming media player provided by Kaltura.


Getting started
---------------

### Building and installing

Building the bundle just requires checking out its source code and
building it with Maven.  You can grab a copy of the code with:

    git clone git://github.com/nyuatlas/kaltura-bundle.git

and build using the following commands:

    cd kaltura-bundle
    mvn clean install

This will produce a jar file in your ``target`` directory named
``net.unicon.kaltura-[VERSION].jar``, which you can install via the
Sling console.  Alternatively, you can have Maven install it for you,
using:

    mvn -Predeploy clean install


### Configuration

#### Nakamura

When you start Nakamura you will need to configure the Kaltura bundle
with the partner settings from your Kaltura instance.  You can do this
by browsing to the Sling web console:

  http://localhost:8080/system/console/configMgr

and clicking the "edit" button on the entry for "Kaltura Service".
You will be prompted for the following fields:

  * Partner Id -- The ID of your account in Kaltura
  * Secret -- Your Kaltura "user secret"
  * Admin Secret -- Your kaltura "admin secret"
  * Endpoint, CDN -- The URL of your Kaltura installation

The first three of these values can be found by logging in to the
Kaltura Management Console (KMC) and clicking ``Settings`` then
``Integration Settings``.

#### 3akai-ux

Finally, you need to configure 3akai-ux to use the right Kaltura
player for your partner ID.  To do this, edit
``dev/configuration/config_custom.js`` and locate the block down the
bottom that looks like:

    /**
     * Kaltura Settings
     */
    config.kaltura = {
        serverURL:  "http://my.kaltura.server/", // Kaltura Server URL
        partnerId:  100, // INSERT YOUR PARTNER ID HERE
        playerId: 100 // INSERT YOUR PLAYER ID (UICONF_ID - from Kaltura Studio tab)
    };

you will need to change the serverURL and partnerId values to match
the settings you used above.  You can find the player ID of your
player by logging into the Kaltura Management Console and clicking the
"Studio" tab.



Acknowledgements
----------------

This bundle was developed by [Unicon, Inc.](http://www.unicon.net/)
for [New York University](http://www.nyu.edu/).

Unicon's original source tree is available on Github at
[https://github.com/Unicon/Kaltura-OAE](https://github.com/Unicon/Kaltura-OAE).
