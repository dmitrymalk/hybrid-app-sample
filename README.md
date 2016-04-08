# Hybrid App Sample

Sample of hybrid mobile application with common java engine classes for iOS 5.0+ and Android 4.0+.

### Getting Started

* Download [J2Objc](https://code.google.com/p/j2objc/) release and extract it.
* Set up Android project:
  * Open Android Studio and pick "Import Project (Eclipse ADT, Gradle, etc.)" for file-explorer_android folder.
  * Change "j2objcHome" path in build.gradle.
  * Open "Gradle Tasks" and run 'j2objcTranslate', 'j2objcCompile' and 'j2objCopy' to translate native Java files to Objective-C files.
* Set up iOS project:
  * Open "FileExplorer.xcodeproj" in file-explorer_ios folder.
  * Set "J2OBJC_HOME" in Settings.xcconfig

<img width="40%" src="https://cloud.githubusercontent.com/assets/2931932/6877070/916de932-d4de-11e4-9133-ef3dc2c3328f.png" />
<img width="59%" src="https://cloud.githubusercontent.com/assets/2931932/6877071/9197608c-d4de-11e4-8547-99129017c137.png" />

## License

Copyright 2016 Dmitry Malkovich

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
