Instructions:
=============


First off you have to export your skins as an apk and then place that apk in the assets folder of your project.

Then in the uccw.java file replace "your.package.name" with your main projects package name.

and replace "your skins.apk" with the name of your skins apk.

Place the java file in your src folder and the xml file in the layout folder.

For the uccw.xml you can add any image preview you'd like, just place it in the res/drawable folder.

####Also add these strings to your values/strings.xml file:


    <string name="uccw">Name of our skins</string>
    <string name="uccw2">Install the skins</string>
    <string name="uccw3">The latest UCCW is required</string>
    <string name="uccw4">Download it here</string>


####And don't forget to reference the uccw class in the manifest:

    <activity
    android:name="your.package.name.uccw"
    android:launchMode="singleTask"
    android:noHistory="true"
    android:label="@string/app_name"
    android:parentActivityName="your.package.name.MainActivity" >
    <meta-data
      android:name="android.support.PARENT_ACTIVITY"
      android:value=".MainActivity" />
    </activity>






