-dontnote sun.misc.Unsafe
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class androidx.appcompat.widget.** { *; }

-keepattributes Signature, InnerClasses, EnclosingMethod
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.-KotlinExtensions
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-ignorewarnings

####################################
#-dontwarn com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar
#
#-dontwarn com.google.android.gms.**
#-dontnote **.ILicensingService
#-dontnote java.nio.file.Files, java.nio.file.Path
#-dontwarn com.google.api.client.googleapis.extensions.android.**
#-dontwarn com.google.api.client.extensions.android.**
#-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault
#
#-keepclassmembers class * {
#  @com.google.api.client.util.Key <fields>;
#}
#

#-keep class com.google.** {
#  public protected private *;
#}
-keep class * {
    public private *;
}

#-dontnote retrofit2.Platform
#-dontwarn retrofit2.Platform$Java8
#-keepattributes Signature
#-keepattributes Exceptions
#
#
#-dontwarn javax.servlet.**
#-dontwarn org.joda.time.**
#-dontwarn org.w3c.dom.**



#-keepclassmembers,allowshrinking,allowobfuscation class com.android.volley.NetworkDispatcher {
#    void processRequest();
#}
#-keepclassmembers,allowshrinking,allowobfuscation class com.android.volley.CacheDispatcher {
#    void processRequest();
#}
#-keep class com.google.android.gms.common.api.GoogleApiClient {
#    void connect();
#    void disconnect();
#}
#-keep public interface android.app.OnActivityPausedListener {*;}
#-dontwarn com.amazon.**
#


#-keep class retrofit.** { *; }
#-keep class com.squareup.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#-dontwarn com.squareup.okhttp.**



#-keep class com.google.android.gms.** { *; }
#-keep class com.google.android.gms.maps.** { *; }
#-keep interface com.google.android.gms.maps.** { *; }


#-keep class com.google.gson.** { *; }



#
#-keep class com.google.inject.** { *; }
#-keep class javax.inject.** { *; }
#-dontwarn android.net.**
#
#-keep class org.apache.** {*;}
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-dontwarn org.apache.http.**
#
#
#-keepclassmembers class * implements android.os.Parcelable {
#    static *** CREATOR;
#}
#
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    static final java.io.ObjectStreamField[] serialPersistentFields;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}


