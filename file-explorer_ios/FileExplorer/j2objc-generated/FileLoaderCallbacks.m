//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/dmitrymalkovich/Documents/Developer/Active/hybrid-app-sample/file-explorer_android/app/src/main/java/engine/FileLoaderCallbacks.java
//

#include "FileLoaderCallbacks.h"
#include "FileSystem.h"
#include "J2ObjC_source.h"
#include "LocalFolder.h"

@interface EngineFileLoaderCallbacks : NSObject
@end

@implementation EngineFileLoaderCallbacks

+ (const J2ObjcClassInfo *)__metadata {
  static const J2ObjcClassInfo _EngineFileLoaderCallbacks = { 1, "FileLoaderCallbacks", "engine", NULL, 0x201, 0, NULL, 0, NULL, 0, NULL};
  return &_EngineFileLoaderCallbacks;
}

@end

J2OBJC_INTERFACE_TYPE_LITERAL_SOURCE(EngineFileLoaderCallbacks)

@interface EngineFileLoaderCallbacks_FragmentLoaderCallbacks : NSObject
@end

@implementation EngineFileLoaderCallbacks_FragmentLoaderCallbacks

+ (const J2ObjcClassInfo *)__metadata {
  static const J2ObjcMethodInfo methods[] = {
    { "showFileListFragmentWithEngineFileSystem_PredefinedFoldersEnum:", "showFileListFragment", "V", 0x401, NULL },
    { "showFileListFragmentWithEngineLocalFolder:withBoolean:withBoolean:", "showFileListFragment", "V", 0x401, NULL },
  };
  static const J2ObjcClassInfo _EngineFileLoaderCallbacks_FragmentLoaderCallbacks = { 1, "FragmentLoaderCallbacks", "engine", "FileLoaderCallbacks", 0x209, 2, methods, 0, NULL, 0, NULL};
  return &_EngineFileLoaderCallbacks_FragmentLoaderCallbacks;
}

@end

J2OBJC_INTERFACE_TYPE_LITERAL_SOURCE(EngineFileLoaderCallbacks_FragmentLoaderCallbacks)