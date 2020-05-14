import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AlertService } from '../services/alert.service';

@Component({
  selector: 'alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  message: any;

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
    this.subscription = this.alertService.getAlert()
      .subscribe(message => {
        switch ( message && message.type ) {
          case 'success':
              message.cssClass = 'alertSuccess';
              break;
          case 'error':
              message.cssClass = 'alertError';
              break;
        }
        this.message = message;
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
