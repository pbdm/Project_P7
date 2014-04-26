//
//  MasterViewController.h
//  TP4PENGBO
//
//  Created by Amber on 11-12-9.
//  Copyright (c) 2011年 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DetailViewController;

@interface MasterViewController : UITableViewController

@property (strong, nonatomic) DetailViewController *detailViewController;
@property(readwrite,retain) NSArray *familynames;
@property(readwrite,retain) NSString * fontName;

@end
