//
//  DetailViewController.h
//  FileExplorer
//
//  Created by Dmitry Malkovich on 3/1/15.
//  Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController

@property (strong, nonatomic) id detailItem;
@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;

@end

