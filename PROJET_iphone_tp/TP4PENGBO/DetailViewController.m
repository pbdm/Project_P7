//
//  DetailViewController.m
//  TP4PENGBO
//
//  Created by Amber on 11-12-9.
//  Copyright (c) 2011年 __MyCompanyName__. All rights reserved.
//

#import "DetailViewController.h"

@interface DetailViewController ()
- (void)configureView;
@end

@implementation DetailViewController

@synthesize buttonOK;
@synthesize detailItem = _detailItem;
@synthesize outletSlider;
@synthesize outletLabel;
@synthesize outletTextView;

@synthesize detailDescriptionLabel = _detailDescriptionLabel;
@synthesize textview;
@synthesize taille;
@synthesize fontname;
@synthesize pTaille;
- (void)dealloc
{
    [_detailItem release];
    [_detailDescriptionLabel release];
    [outletSlider release];
    [outletLabel release];
    [outletTextView release];
    [outletTextView release];
    [buttonOK release];
    [super dealloc];
}

#pragma mark - Managing the detail item

- (void)setDetailItem:(id)newDetailItem
{
    if (_detailItem != newDetailItem) {
        [_detailItem release]; 
        _detailItem = [newDetailItem retain]; 

        // Update the view.
        [self configureView];
    }
}

- (void)configureView
{
    // Update the user interface for the detail item.

    if (self.detailItem) {
        self.detailDescriptionLabel.text = [self.detailItem description];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    outletTextView.delegate=self;
    [self configureView];
}

- (void)viewDidUnload
{
    [self setOutletSlider:nil];
    [self setOutletLabel:nil];
    [self setOutletTextView:nil];
    [self setOutletTextView:nil];
    [self setButtonOK:nil];
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{   [buttonOK setHidden:YES];
    self.title=self.fontname;
    taille=(int)(outletSlider.value+0.5f);
	pTaille=[[NSString alloc] initWithFormat:@"%d pt",taille];
	[self.outletLabel setText:pTaille];
    NSUserDefaults *saveText=[NSUserDefaults standardUserDefaults];
	NSString *text = [saveText stringForKey:@"text"];
    if (text==nil)
        self.outletTextView.text=@"IPHONE IS DEAD:)";
    else
        self.outletTextView.text=text;
	self.outletTextView.font=[UIFont fontWithName:self.fontname size:taille];
    [super viewWillAppear:animated];
}

- (IBAction) changeValeur:(id)sender{
    taille=outletSlider.value;
	self.outletTextView.font=[UIFont fontWithName:self.fontname size:taille];
	pTaille=[[NSString alloc] initWithFormat:@"%d pt",taille];
	[self.outletLabel setText:pTaille];
}

- (void) textViewDidBeginEditing:(UITextView *)textEdit{
	[buttonOK setHidden:NO];
}

- (IBAction) endInput:(id)sender{
	[outletTextView resignFirstResponder];
	[buttonOK setHidden:YES];
	NSUserDefaults *saveText=[NSUserDefaults standardUserDefaults];
	[saveText setObject:outletTextView.text forKey:@"text"];

}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
	[super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
	[super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}



- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil fontname:(NSString *)fontName {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.fontname=fontName;
    }
    return self;
}
							

@end
