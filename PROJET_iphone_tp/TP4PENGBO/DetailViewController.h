//
//  DetailViewController.h
//  TP4PENGBO
//
//  Created by Amber on 11-12-9.
//  Copyright (c) 2011年 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController

@property (retain, nonatomic) IBOutlet UIButton *buttonOK;
@property (strong, nonatomic) id detailItem;
@property (retain, nonatomic) IBOutlet UISlider *outletSlider;
@property (retain, nonatomic) IBOutlet UILabel *outletLabel;
@property (retain, nonatomic) IBOutlet UITextView *outletTextView;
- (IBAction)changeValeur:(id)sender;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil fontname:(NSString *)fontName;
@property (strong, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@property (nonatomic, retain) IBOutlet UITextView *textview;
@property (nonatomic, assign) NSString *fontname;
@property  (nonatomic,assign) int taille;
@property  (nonatomic,assign) NSString * pTaille;

- (IBAction)endInput:(id)sender;

@end
