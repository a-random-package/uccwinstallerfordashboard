Instructions:
=============


First off you have to export your skins as an apk and then place that apk in the assets folder of your project.

Then in the uccw.java file replace "your.package.name" with your main projects package name

and replace "your skins.apk" with the name of your skins apk. 

For the uccw.xml you can add any image preview you'd like, just place it in the res/drawable folder.

Also add these strings to your values/strings.xml file

'''xml
<string name="uccw">Jive UCCW Skins</string>
<string name="uccw2">Install the skins</string>
<string name="uccw3">The latest UCCW is required</string>
<string name="uccw4">Download it here</string>
'''

And don't forget to reference the uccw class in the manifest

<activity
    android:name="com.arandompackage.jive.uccw"
    android:launchMode="singleTask"
    android:noHistory="true"
    android:label="@string/app_name"
    android:parentActivityName="com.arandompackage.jive.MainActivity" >
    <meta-data
      android:name="android.support.PARENT_ACTIVITY"
      android:value=".MainActivity" />
</activity>






