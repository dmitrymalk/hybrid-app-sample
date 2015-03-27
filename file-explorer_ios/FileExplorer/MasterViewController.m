//
//  MasterViewController.m
//  FileExplorer
//
//  Created by Dmitry Malkovich on 3/1/15.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import "MasterViewController.h"
#import "FileSystem.h"
#import "FileListCell.h"
#import "LocalFolder.h"
#import "java/util/ArrayList.h"

@interface MasterViewController ()

@property NSMutableArray *objects;
@end

@implementation MasterViewController

- (void)awakeFromNib {
    [super awakeFromNib];
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        self.clearsSelectionOnViewWillAppear = NO;
        self.preferredContentSize = CGSizeMake(320.0, 600.0);
    }
}

- (void)viewDidLoad {
    [super viewDidLoad];
    if (self.items == nil) {
        self.items = [FileSystem getDirectories];
    }
}

#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    FileListCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CellFileList" forIndexPath:indexPath];
    
    NSObject *object = [self.items objectAtIndex:[indexPath row]];
    if ([object isKindOfClass: [EngineLocalFolder class]]) {
        EngineLocalFolder * file = (EngineLocalFolder *) object;
        [cell name].text = [file getDisplayName];
        [cell thumbnail].image = [UIImage imageNamed:@"ic_folder_black_48dp.png"];
    }
    else if ([object isKindOfClass: [EngineLocalFile class]]) {
        EngineLocalFile * file = (EngineLocalFile *) object;
        if ([file getDisplayName] != nil) {
            [cell name].text = [file getDisplayName];
            [cell thumbnail].image = [UIImage imageNamed:@"ic_insert_drive_file_black_48dp.png"];
        }
    }

    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    EngineLocalFile * file = [self.items objectAtIndex:indexPath.row];
    if ([file isKindOfClass:[EngineLocalFolder class]])
    {
        EngineLocalFolder * folder = (EngineLocalFolder *) file;
        FileSystem * utils = [[FileSystem alloc] init];
        JavaUtilArrayList *list = [JavaUtilArrayList new];
        [utils findFilesInFolderWithJavaIoFile:[folder getFile] withJavaUtilList:list];
        NSMutableArray *mutableArray = [NSMutableArray new];
        for (int i = 0; i < list.size; i++)
        {
            [mutableArray addObject:[list getWithInt:i]];
        }
        MasterViewController *controller = (MasterViewController *)[self.storyboard instantiateViewControllerWithIdentifier:@"MasterViewController"];
        controller.title = [file getDisplayName];
        controller.items = mutableArray;
        [self.navigationController pushViewController:controller animated:YES];
    } else {
        UITableViewCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
        cell.selected = NO;
    }
}

@end
