//
//  FileManager.m
//  FileExplorer
//
//  Created by Dmitry Malkovich on 11/15/14.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import "FileSystem.h"
#import "LocalFolder.h"
#import "java/io/File.h"
#import "java/util/ArrayList.h"

@implementation FileSystem

static NSString *loggerTag = @"FileSystem";

+ (NSArray *)getDirectories {
    NSMutableArray *result = [NSMutableArray new];
    [self getDirectory:NSApplicationDirectory addTo:result];
    [self getDirectory:NSDemoApplicationDirectory addTo:result];
    [self getDirectory:NSAdminApplicationDirectory addTo:result];
    [self getDirectory:NSLibraryDirectory addTo:result];
    [self getDirectory:NSDeveloperDirectory addTo:result];
    [self getDirectory:NSUserDirectory addTo:result];
    [self getDirectory:NSDocumentationDirectory addTo:result];
    [self getDirectory:NSDocumentDirectory addTo:result];
    [self getDirectory:NSCoreServiceDirectory addTo:result];
    [self getDirectory:NSAutosavedInformationDirectory addTo:result];
    [self getDirectory:NSDesktopDirectory addTo:result];
    [self getDirectory:NSCachesDirectory addTo:result];
    [self getDirectory:NSApplicationSupportDirectory addTo:result];
    [self getDirectory:NSDownloadsDirectory addTo:result];
    [self getDirectory:NSInputMethodsDirectory addTo:result];
    [self getDirectory:NSMoviesDirectory addTo:result];
    [self getDirectory:NSMusicDirectory addTo:result];
    [self getDirectory:NSPicturesDirectory addTo:result];
    [self getDirectory:NSPrinterDescriptionDirectory addTo:result];
    [self getDirectory:NSSharedPublicDirectory addTo:result];
    [self getDirectory:NSPreferencePanesDirectory addTo:result];
    // Not avaliable on IOS
    //[self getDirectory:NSApplicationScriptsDirectory addTo:result];
    [self getDirectory:NSItemReplacementDirectory addTo:result];
    [self getDirectory:NSAllApplicationsDirectory addTo:result];
    [self getDirectory:NSAllLibrariesDirectory addTo:result];
    // Not avaliable on IOS
    //[self getDirectory:NSTrashDirectory addTo:result];
    return result;
}

+ (NSArray *)getFiles:(NSString *) path {
    return nil;
}

+ (BOOL)getDirectory:(NSSearchPathDirectory)directory addTo:(NSMutableArray *)result{
    if (result != nil) {
        NSArray *paths = NSSearchPathForDirectoriesInDomains(directory, NSUserDomainMask, YES);
        for (NSString *path in paths) {
            if (path != nil) {
                JavaIoFile * file = [[JavaIoFile alloc] initWithNSString: path];
                EngineLocalFile *folder = [[EngineLocalFolder alloc] initWithJavaIoFile: file];
                [result addObject:folder];
            }
        }
        return YES;
    }
    return NO;
}

- (jboolean)findFilesInFolderWithJavaIoFile:(JavaIoFile *)folder withJavaUtilList:(id<JavaUtilList>)list {
    if (folder.isDirectory == YES)
    {
        for (id file in folder.listFiles)
        {
            if ([file isKindOfClass:[JavaIoFile class]])
            {
                JavaIoFile *javaFile = (JavaIoFile *) file;
                if (javaFile.isDirectory)
                {
                    EngineLocalFile *folder = [[EngineLocalFolder alloc] initWithJavaIoFile: file];
                    [list addWithId:folder];
                }
                else
                {
                    EngineLocalFile *localFile = [[EngineLocalFile alloc] initWithJavaIoFile: file];
                    [list addWithId:localFile];
                }
            }
        }
        return YES;
    }
    return NO;
}


@end
