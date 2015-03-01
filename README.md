# Hybrid App Sample

Sample of hybrid mobile application with common java engine classes for iOS 5.0+ and Android 4.0+.

### Getting Started

* Download [J2Objc](https://code.google.com/p/j2objc/) release and extract it.
* Set up Android project:
  * Open Android Studio and pick "Import Project (Eclipse ADT, Gradle, etc.)" for file-explorer_android folder.
  * Change "j2objcHome" path.
  * Open "Gradle Tasks" and run 'j2objcTranslate', 'j2objcCompile' and 'j2objCopy' to translate native Java files to Objective-C files.
* Set up iOS project:
  * Open "FileExplorer.xcodeproj" in file-explorer_ios folder.
  * Set "J2OBJC_HOME" in Settings.xcconfig
