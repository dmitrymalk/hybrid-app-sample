//
//  MasterViewController.h
//  FileExplorer
//
//  Created by Dmitry Malkovich on 3/1/15.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DetailViewController;

@interface MasterViewController : UITableViewController

@property (strong, nonatomic) DetailViewController *detailViewController;


@end

