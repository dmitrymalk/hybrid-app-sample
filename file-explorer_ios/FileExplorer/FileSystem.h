//
//  FileManager.h
//  FileExplorer
//
//  Created by Dmitry Malkovich on 11/15/14.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LocalFile.h"
#import "FileSystemUtils.h"

//  NSSearchPathDirectory
//  Dont forget: "Application supports iTunes file sharing" to YES in Info.plist.
@interface FileSystem : EngineFileSystemUtils
+ (NSArray *) getDirectories;
+ (NSArray *) getFiles:(NSString *) path;
+ (BOOL) getDirectory:(NSSearchPathDirectory) directory addTo:(NSMutableArray *) result;
- (jboolean)findFilesInFolderWithJavaIoFile:(JavaIoFile *)folder
                           withJavaUtilList:(id<JavaUtilList>)list;
@end
