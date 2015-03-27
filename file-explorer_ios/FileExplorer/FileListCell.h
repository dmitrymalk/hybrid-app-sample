//
//  FileListCell.h
//  FileExplorer
//
//  Created by Dmitry Malkovich on 1/2/15.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FileListCell : UITableViewCell

@property (nonatomic, retain) IBOutlet UILabel *name;
@property (nonatomic, retain) IBOutlet UIImageView *thumbnail;

@end
